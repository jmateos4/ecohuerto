import { Router } from 'express'
import user from './user'
import auth from './auth'
import huerto from './huerto'
import espacio from './espacio'
import plantacion from './plantacion'
import meteo from './meteo'
import inventario from './inventario'

const router = new Router()

/**
 * @apiDefine master Master access only
 * You must pass `access_token` parameter or a Bearer Token authorization header
 * to access this endpoint.
 */
/**
 * @apiDefine admin Admin access only
 * You must pass `access_token` parameter or a Bearer Token authorization header
 * to access this endpoint.
 */
/**
 * @apiDefine user User access only
 * You must pass `access_token` parameter or a Bearer Token authorization header
 * to access this endpoint.
 */
/**
 * @apiDefine listParams
 * @apiParam {String} [q] Query to search.
 * @apiParam {Number{1..30}} [page=1] Page number.
 * @apiParam {Number{1..100}} [limit=30] Amount of returned items.
 * @apiParam {String[]} [sort=-createdAt] Order of returned items.
 * @apiParam {String[]} [fields] Fields to be returned.
 */
router.use('/users', user)
router.use('/auth', auth)
router.use('/huertos', huerto)
router.use('/espacios', espacio)
router.use('/plataciones', plantacion)
router.use('/centroMeteo', meteo)
router.use('/inventarios', inventario)

export default router
