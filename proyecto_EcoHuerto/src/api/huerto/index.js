import { Router } from 'express'
import { middleware as query } from 'querymen'
import { middleware as body } from 'bodymen'
import { create, index, show, update, destroy } from './controller'
import { schema } from './model'
export Huerto, { schema } from './model'

const router = new Router()
const { nombre, espacio, inventario } = schema.tree

/**
 * @api {post} /huertos Create huerto
 * @apiName CreateHuerto
 * @apiGroup Huerto
 * @apiParam nombre Huerto's nombre.
 * @apiParam espacio Huerto's espacio.
 * @apiParam inventario Huerto's inventario.
 * @apiSuccess {Object} huerto Huerto's data.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 404 Huerto not found.
 */
router.post('/',
  body({ nombre, espacio, inventario }),
  create)

/**
 * @api {get} /huertos Retrieve huertos
 * @apiName RetrieveHuertos
 * @apiGroup Huerto
 * @apiUse listParams
 * @apiSuccess {Number} count Total amount of huertos.
 * @apiSuccess {Object[]} rows List of huertos.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 */
router.get('/',
  query(),
  index)

/**
 * @api {get} /huertos/:id Retrieve huerto
 * @apiName RetrieveHuerto
 * @apiGroup Huerto
 * @apiSuccess {Object} huerto Huerto's data.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 404 Huerto not found.
 */
router.get('/:id',
  show)

/**
 * @api {put} /huertos/:id Update huerto
 * @apiName UpdateHuerto
 * @apiGroup Huerto
 * @apiParam nombre Huerto's nombre.
 * @apiParam espacio Huerto's espacio.
 * @apiParam inventario Huerto's inventario.
 * @apiSuccess {Object} huerto Huerto's data.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 404 Huerto not found.
 */
router.put('/:id',
  body({ nombre, espacio, inventario }),
  update)

/**
 * @api {delete} /huertos/:id Delete huerto
 * @apiName DeleteHuerto
 * @apiGroup Huerto
 * @apiSuccess (Success 204) 204 No Content.
 * @apiError 404 Huerto not found.
 */
router.delete('/:id',
  destroy)

export default router
