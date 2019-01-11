import { success, notFound } from '../../services/response/'
import { Espacio } from '.'

export const create = ({ bodymen: { body } }, res, next) =>
  Espacio.create(body)
    .then((espacio) => espacio.view(true))
    .then(success(res, 201))
    .catch(next)

export const index = ({ querymen: { query, select, cursor } }, res, next) =>
  Espacio.count(query)
    .then(count => Espacio.find(query, select, cursor)
      .then((espacios) => ({
        count,
        rows: espacios.map((espacio) => espacio.view())
      }))
    )
    .then(success(res))
    .catch(next)

export const show = ({ params }, res, next) =>
  Espacio.findById(params.id)
    .then(notFound(res))
    .then((espacio) => espacio ? espacio.view() : null)
    .then(success(res))
    .catch(next)

export const update = ({ bodymen: { body }, params }, res, next) =>
  Espacio.findById(params.id)
    .then(notFound(res))
    .then((espacio) => espacio ? Object.assign(espacio, body).save() : null)
    .then((espacio) => espacio ? espacio.view(true) : null)
    .then(success(res))
    .catch(next)

export const destroy = ({ params }, res, next) =>
  Espacio.findById(params.id)
    .then(notFound(res))
    .then((espacio) => espacio ? espacio.remove() : null)
    .then(success(res, 204))
    .catch(next)
