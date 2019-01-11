import { Router } from 'express'
import { middleware as query } from 'querymen'
import { middleware as body } from 'bodymen'
import { create, index, show, update, destroy } from './controller'
import { schema } from './model'
export Meteo, { schema } from './model'

const router = new Router()
const { temperatura, probabilidad_lluvia, viento, humedad } = schema.tree

/**
 * @api {post} /centroMeteo Create meteo
 * @apiName CreateMeteo
 * @apiGroup Meteo
 * @apiParam temperatura Meteo's temperatura.
 * @apiParam probabilidad_lluvia Meteo's probabilidad_lluvia.
 * @apiParam viento Meteo's viento.
 * @apiParam humedad Meteo's humedad.
 * @apiSuccess {Object} meteo Meteo's data.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 404 Meteo not found.
 */
router.post('/',
  body({ temperatura, probabilidad_lluvia, viento, humedad }),
  create)

/**
 * @api {get} /centroMeteo Retrieve meteos
 * @apiName RetrieveMeteos
 * @apiGroup Meteo
 * @apiUse listParams
 * @apiSuccess {Number} count Total amount of meteos.
 * @apiSuccess {Object[]} rows List of meteos.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 */
router.get('/',
  query(),
  index)

/**
 * @api {get} /centroMeteo/:id Retrieve meteo
 * @apiName RetrieveMeteo
 * @apiGroup Meteo
 * @apiSuccess {Object} meteo Meteo's data.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 404 Meteo not found.
 */
router.get('/:id',
  show)

/**
 * @api {put} /centroMeteo/:id Update meteo
 * @apiName UpdateMeteo
 * @apiGroup Meteo
 * @apiParam temperatura Meteo's temperatura.
 * @apiParam probabilidad_lluvia Meteo's probabilidad_lluvia.
 * @apiParam viento Meteo's viento.
 * @apiParam humedad Meteo's humedad.
 * @apiSuccess {Object} meteo Meteo's data.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 404 Meteo not found.
 */
router.put('/:id',
  body({ temperatura, probabilidad_lluvia, viento, humedad }),
  update)

/**
 * @api {delete} /centroMeteo/:id Delete meteo
 * @apiName DeleteMeteo
 * @apiGroup Meteo
 * @apiSuccess (Success 204) 204 No Content.
 * @apiError 404 Meteo not found.
 */
router.delete('/:id',
  destroy)

export default router
