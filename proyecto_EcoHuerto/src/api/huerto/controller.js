import { success, notFound } from '../../services/response/'
import { Huerto } from '.'

export const create = ({ bodymen: { body } }, res, next) =>
  Huerto.create(body)
    .then((huerto) => huerto.view(true))
    .then(success(res, 201))
    .catch(next)

export const index = ({ querymen: { query, select, cursor } }, res, next) =>
  Huerto.count(query)
    .then(count => Huerto.find(query, select, cursor)
      .then((huertos) => ({
        count,
        rows: huertos.map((huerto) => huerto.view())
      }))
    )
    .then(success(res))
    .catch(next)

export const show = ({ params }, res, next) =>
  Huerto.findById(params.id)
    .then(notFound(res))
    .then((huerto) => huerto ? huerto.view() : null)
    .then(success(res))
    .catch(next)

export const update = ({ bodymen: { body }, params }, res, next) =>
  Huerto.findById(params.id)
    .then(notFound(res))
    .then((huerto) => huerto ? Object.assign(huerto, body).save() : null)
    .then((huerto) => huerto ? huerto.view(true) : null)
    .then(success(res))
    .catch(next)

export const destroy = ({ params }, res, next) =>
  Huerto.findById(params.id)
    .then(notFound(res))
    .then((huerto) => huerto ? huerto.remove() : null)
    .then(success(res, 204))
    .catch(next)
