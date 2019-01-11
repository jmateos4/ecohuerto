import { Router } from 'express'
import { middleware as query } from 'querymen'
import { middleware as body } from 'bodymen'
import { create, index, show, update, destroy } from './controller'
import { schema } from './model'
export Espacio, { schema } from './model'

const router = new Router()
const { nombre, plantaciones, dimensiones } = schema.tree

/**
 * @api {post} /espacios Create espacio
 * @apiName CreateEspacio
 * @apiGroup Espacio
 * @apiParam nombre Espacio's nombre.
 * @apiParam plantaciones Espacio's plantaciones.
 * @apiParam dimensiones Espacio's dimensiones.
 * @apiSuccess {Object} espacio Espacio's data.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 404 Espacio not found.
 */
router.post('/',
  body({ nombre, plantaciones, dimensiones }),
  create)

/**
 * @api {get} /espacios Retrieve espacios
 * @apiName RetrieveEspacios
 * @apiGroup Espacio
 * @apiUse listParams
 * @apiSuccess {Number} count Total amount of espacios.
 * @apiSuccess {Object[]} rows List of espacios.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 */
router.get('/',
  query(),
  index)

/**
 * @api {get} /espacios/:id Retrieve espacio
 * @apiName RetrieveEspacio
 * @apiGroup Espacio
 * @apiSuccess {Object} espacio Espacio's data.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 404 Espacio not found.
 */
router.get('/:id',
  show)

/**
 * @api {put} /espacios/:id Update espacio
 * @apiName UpdateEspacio
 * @apiGroup Espacio
 * @apiParam nombre Espacio's nombre.
 * @apiParam plantaciones Espacio's plantaciones.
 * @apiParam dimensiones Espacio's dimensiones.
 * @apiSuccess {Object} espacio Espacio's data.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 404 Espacio not found.
 */
router.put('/:id',
  body({ nombre, plantaciones, dimensiones }),
  update)

/**
 * @api {delete} /espacios/:id Delete espacio
 * @apiName DeleteEspacio
 * @apiGroup Espacio
 * @apiSuccess (Success 204) 204 No Content.
 * @apiError 404 Espacio not found.
 */
router.delete('/:id',
  destroy)

export default router
