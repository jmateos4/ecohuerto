import { Router } from 'express'
import { middleware as query } from 'querymen'
import { middleware as body } from 'bodymen'
import { create, index, show, update, destroy } from './controller'
import { schema } from './model'
export Inventario, { schema } from './model'

const router = new Router()
const { nombre, tipo, cantidad, peso } = schema.tree

/**
 * @api {post} /inventarios Create inventario
 * @apiName CreateInventario
 * @apiGroup Inventario
 * @apiParam nombre Inventario's nombre.
 * @apiParam tipo Inventario's tipo.
 * @apiParam cantidad Inventario's cantidad.
 * @apiParam peso Inventario's peso.
 * @apiSuccess {Object} inventario Inventario's data.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 404 Inventario not found.
 */
router.post('/',
  body({ nombre, tipo, cantidad, peso }),
  create)

/**
 * @api {get} /inventarios Retrieve inventarios
 * @apiName RetrieveInventarios
 * @apiGroup Inventario
 * @apiUse listParams
 * @apiSuccess {Number} count Total amount of inventarios.
 * @apiSuccess {Object[]} rows List of inventarios.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 */
router.get('/',
  query(),
  index)

/**
 * @api {get} /inventarios/:id Retrieve inventario
 * @apiName RetrieveInventario
 * @apiGroup Inventario
 * @apiSuccess {Object} inventario Inventario's data.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 404 Inventario not found.
 */
router.get('/:id',
  show)

/**
 * @api {put} /inventarios/:id Update inventario
 * @apiName UpdateInventario
 * @apiGroup Inventario
 * @apiParam nombre Inventario's nombre.
 * @apiParam tipo Inventario's tipo.
 * @apiParam cantidad Inventario's cantidad.
 * @apiParam peso Inventario's peso.
 * @apiSuccess {Object} inventario Inventario's data.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 404 Inventario not found.
 */
router.put('/:id',
  body({ nombre, tipo, cantidad, peso }),
  update)

/**
 * @api {delete} /inventarios/:id Delete inventario
 * @apiName DeleteInventario
 * @apiGroup Inventario
 * @apiSuccess (Success 204) 204 No Content.
 * @apiError 404 Inventario not found.
 */
router.delete('/:id',
  destroy)

export default router
