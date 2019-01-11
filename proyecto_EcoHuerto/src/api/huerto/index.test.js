import request from 'supertest'
import { apiRoot } from '../../config'
import express from '../../services/express'
import routes, { Huerto } from '.'

const app = () => express(apiRoot, routes)

let huerto

beforeEach(async () => {
  huerto = await Huerto.create({})
})

test('POST /huertos 201', async () => {
  const { status, body } = await request(app())
    .post(`${apiRoot}`)
    .send({ nombre: 'test', espacio: 'test', inventario: 'test' })
  expect(status).toBe(201)
  expect(typeof body).toEqual('object')
  expect(body.nombre).toEqual('test')
  expect(body.espacio).toEqual('test')
  expect(body.inventario).toEqual('test')
})

test('GET /huertos 200', async () => {
  const { status, body } = await request(app())
    .get(`${apiRoot}`)
  expect(status).toBe(200)
  expect(Array.isArray(body.rows)).toBe(true)
  expect(Number.isNaN(body.count)).toBe(false)
})

test('GET /huertos/:id 200', async () => {
  const { status, body } = await request(app())
    .get(`${apiRoot}/${huerto.id}`)
  expect(status).toBe(200)
  expect(typeof body).toEqual('object')
  expect(body.id).toEqual(huerto.id)
})

test('GET /huertos/:id 404', async () => {
  const { status } = await request(app())
    .get(apiRoot + '/123456789098765432123456')
  expect(status).toBe(404)
})

test('PUT /huertos/:id 200', async () => {
  const { status, body } = await request(app())
    .put(`${apiRoot}/${huerto.id}`)
    .send({ nombre: 'test', espacio: 'test', inventario: 'test' })
  expect(status).toBe(200)
  expect(typeof body).toEqual('object')
  expect(body.id).toEqual(huerto.id)
  expect(body.nombre).toEqual('test')
  expect(body.espacio).toEqual('test')
  expect(body.inventario).toEqual('test')
})

test('PUT /huertos/:id 404', async () => {
  const { status } = await request(app())
    .put(apiRoot + '/123456789098765432123456')
    .send({ nombre: 'test', espacio: 'test', inventario: 'test' })
  expect(status).toBe(404)
})

test('DELETE /huertos/:id 204', async () => {
  const { status } = await request(app())
    .delete(`${apiRoot}/${huerto.id}`)
  expect(status).toBe(204)
})

test('DELETE /huertos/:id 404', async () => {
  const { status } = await request(app())
    .delete(apiRoot + '/123456789098765432123456')
  expect(status).toBe(404)
})
