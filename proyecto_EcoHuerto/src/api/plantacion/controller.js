import { success, notFound } from '../../services/response/'
import { Plantacion } from '.'

export const create = ({ bodymen: { body } }, res, next) =>
  Plantacion.create(body)
    .then((plantacion) => plantacion.view(true))
    .then(success(res, 201))
    .catch(next)

export const index = ({ querymen: { query, select, cursor } }, res, next) =>
  Plantacion.count(query)
    .then(count => Plantacion.find(query, select, cursor)
      .then((plantacions) => ({
        count,
        rows: plantacions.map((plantacion) => plantacion.view())
      }))
    )
    .then(success(res))
    .catch(next)

export const show = ({ params }, res, next) =>
  Plantacion.findById(params.id)
    .then(notFound(res))
    .then((plantacion) => plantacion ? plantacion.view() : null)
    .then(success(res))
    .catch(next)

export const update = ({ bodymen: { body }, params }, res, next) =>
  Plantacion.findById(params.id)
    .then(notFound(res))
    .then((plantacion) => plantacion ? Object.assign(plantacion, body).save() : null)
    .then((plantacion) => plantacion ? plantacion.view(true) : null)
    .then(success(res))
    .catch(next)

export const destroy = ({ params }, res, next) =>
  Plantacion.findById(params.id)
    .then(notFound(res))
    .then((plantacion) => plantacion ? plantacion.remove() : null)
    .then(success(res, 204))
    .catch(next)
