import { success, notFound } from '../../services/response/'
import { Meteo } from '.'

export const create = ({ bodymen: { body } }, res, next) =>
  Meteo.create(body)
    .then((meteo) => meteo.view(true))
    .then(success(res, 201))
    .catch(next)

export const index = ({ querymen: { query, select, cursor } }, res, next) =>
  Meteo.count(query)
    .then(count => Meteo.find(query, select, cursor)
      .then((meteos) => ({
        count,
        rows: meteos.map((meteo) => meteo.view())
      }))
    )
    .then(success(res))
    .catch(next)

export const show = ({ params }, res, next) =>
  Meteo.findById(params.id)
    .then(notFound(res))
    .then((meteo) => meteo ? meteo.view() : null)
    .then(success(res))
    .catch(next)

export const update = ({ bodymen: { body }, params }, res, next) =>
  Meteo.findById(params.id)
    .then(notFound(res))
    .then((meteo) => meteo ? Object.assign(meteo, body).save() : null)
    .then((meteo) => meteo ? meteo.view(true) : null)
    .then(success(res))
    .catch(next)

export const destroy = ({ params }, res, next) =>
  Meteo.findById(params.id)
    .then(notFound(res))
    .then((meteo) => meteo ? meteo.remove() : null)
    .then(success(res, 204))
    .catch(next)
