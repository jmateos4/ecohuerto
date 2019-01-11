import request from 'supertest'
import { apiRoot } from '../../config'
import express from '../../services/express'
import routes, { Plantacion } from '.'

const app = () => express(apiRoot, routes)

let plantacion

beforeEach(async () => {
  plantacion = await Plantacion.create({})
})

test('POST /plataciones 201', async () => {
  const { status, body } = await request(app())
    .post(`${apiRoot}`)
    .send({ nombre: 'test', tipo: 'test', riegoAutomatico: 'test' })
  expect(status).toBe(201)
  expect(typeof body).toEqual('object')
  expect(body.nombre).toEqual('test')
  expect(body.tipo).toEqual('test')
  expect(body.riegoAutomatico).toEqual('test')
})

test('GET /plataciones 200', async () => {
  const { status, body } = await request(app())
    .get(`${apiRoot}`)
  expect(status).toBe(200)
  expect(Array.isArray(body.rows)).toBe(true)
  expect(Number.isNaN(body.count)).toBe(false)
})

test('GET /plataciones/:id 200', async () => {
  const { status, body } = await request(app())
    .get(`${apiRoot}/${plantacion.id}`)
  expect(status).toBe(200)
  expect(typeof body).toEqual('object')
  expect(body.id).toEqual(plantacion.id)
})

test('GET /plataciones/:id 404', async () => {
  const { status } = await request(app())
    .get(apiRoot + '/123456789098765432123456')
  expect(status).toBe(404)
})

test('PUT /plataciones/:id 200', async () => {
  const { status, body } = await request(app())
    .put(`${apiRoot}/${plantacion.id}`)
    .send({ nombre: 'test', tipo: 'test', riegoAutomatico: 'test' })
  expect(status).toBe(200)
  expect(typeof body).toEqual('object')
  expect(body.id).toEqual(plantacion.id)
  expect(body.nombre).toEqual('test')
  expect(body.tipo).toEqual('test')
  expect(body.riegoAutomatico).toEqual('test')
})

test('PUT /plataciones/:id 404', async () => {
  const { status } = await request(app())
    .put(apiRoot + '/123456789098765432123456')
    .send({ nombre: 'test', tipo: 'test', riegoAutomatico: 'test' })
  expect(status).toBe(404)
})

test('DELETE /plataciones/:id 204', async () => {
  const { status } = await request(app())
    .delete(`${apiRoot}/${plantacion.id}`)
  expect(status).toBe(204)
})

test('DELETE /plataciones/:id 404', async () => {
  const { status } = await request(app())
    .delete(apiRoot + '/123456789098765432123456')
  expect(status).toBe(404)
})
