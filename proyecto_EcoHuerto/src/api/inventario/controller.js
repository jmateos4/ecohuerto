import { success, notFound } from '../../services/response/'
import { Inventario } from '.'

export const create = ({ bodymen: { body } }, res, next) =>
  Inventario.create(body)
    .then((inventario) => inventario.view(true))
    .then(success(res, 201))
    .catch(next)

export const index = ({ querymen: { query, select, cursor } }, res, next) =>
  Inventario.count(query)
    .then(count => Inventario.find(query, select, cursor)
      .then((inventarios) => ({
        count,
        rows: inventarios.map((inventario) => inventario.view())
      }))
    )
    .then(success(res))
    .catch(next)

export const show = ({ params }, res, next) =>
  Inventario.findById(params.id)
    .then(notFound(res))
    .then((inventario) => inventario ? inventario.view() : null)
    .then(success(res))
    .catch(next)

export const update = ({ bodymen: { body }, params }, res, next) =>
  Inventario.findById(params.id)
    .then(notFound(res))
    .then((inventario) => inventario ? Object.assign(inventario, body).save() : null)
    .then((inventario) => inventario ? inventario.view(true) : null)
    .then(success(res))
    .catch(next)

export const destroy = ({ params }, res, next) =>
  Inventario.findById(params.id)
    .then(notFound(res))
    .then((inventario) => inventario ? inventario.remove() : null)
    .then(success(res, 204))
    .catch(next)
