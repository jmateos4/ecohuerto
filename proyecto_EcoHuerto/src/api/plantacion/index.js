import { Router } from 'express'
import { middleware as query } from 'querymen'
import { middleware as body } from 'bodymen'
import { create, index, show, update, destroy } from './controller'
import { schema } from './model'
export Plantacion, { schema } from './model'

const router = new Router()
const { nombre, tipo, riegoAutomatico } = schema.tree

/**
 * @api {post} /plataciones Create plantacion
 * @apiName CreatePlantacion
 * @apiGroup Plantacion
 * @apiParam nombre Plantacion's nombre.
 * @apiParam tipo Plantacion's tipo.
 * @apiParam riegoAutomatico Plantacion's riegoAutomatico.
 * @apiSuccess {Object} plantacion Plantacion's data.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 404 Plantacion not found.
 */
router.post('/',
  body({ nombre, tipo, riegoAutomatico }),
  create)

/**
 * @api {get} /plataciones Retrieve plantacions
 * @apiName RetrievePlantacions
 * @apiGroup Plantacion
 * @apiUse listParams
 * @apiSuccess {Number} count Total amount of plantacions.
 * @apiSuccess {Object[]} rows List of plantacions.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 */
router.get('/',
  query(),
  index)

/**
 * @api {get} /plataciones/:id Retrieve plantacion
 * @apiName RetrievePlantacion
 * @apiGroup Plantacion
 * @apiSuccess {Object} plantacion Plantacion's data.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 404 Plantacion not found.
 */
router.get('/:id',
  show)

/**
 * @api {put} /plataciones/:id Update plantacion
 * @apiName UpdatePlantacion
 * @apiGroup Plantacion
 * @apiParam nombre Plantacion's nombre.
 * @apiParam tipo Plantacion's tipo.
 * @apiParam riegoAutomatico Plantacion's riegoAutomatico.
 * @apiSuccess {Object} plantacion Plantacion's data.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 404 Plantacion not found.
 */
router.put('/:id',
  body({ nombre, tipo, riegoAutomatico }),
  update)

/**
 * @api {delete} /plataciones/:id Delete plantacion
 * @apiName DeletePlantacion
 * @apiGroup Plantacion
 * @apiSuccess (Success 204) 204 No Content.
 * @apiError 404 Plantacion not found.
 */
router.delete('/:id',
  destroy)

export default router
