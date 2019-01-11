import request from 'supertest'
import { apiRoot } from '../../config'
import express from '../../services/express'
import routes, { Espacio } from '.'

const app = () => express(apiRoot, routes)

let espacio

beforeEach(async () => {
  espacio = await Espacio.create({})
})

test('POST /espacios 201', async () => {
  const { status, body } = await request(app())
    .post(`${apiRoot}`)
    .send({ nombre: 'test', plantaciones: 'test', dimensiones: 'test' })
  expect(status).toBe(201)
  expect(typeof body).toEqual('object')
  expect(body.nombre).toEqual('test')
  expect(body.plantaciones).toEqual('test')
  expect(body.dimensiones).toEqual('test')
})

test('GET /espacios 200', async () => {
  const { status, body } = await request(app())
    .get(`${apiRoot}`)
  expect(status).toBe(200)
  expect(Array.isArray(body.rows)).toBe(true)
  expect(Number.isNaN(body.count)).toBe(false)
})

test('GET /espacios/:id 200', async () => {
  const { status, body } = await request(app())
    .get(`${apiRoot}/${espacio.id}`)
  expect(status).toBe(200)
  expect(typeof body).toEqual('object')
  expect(body.id).toEqual(espacio.id)
})

test('GET /espacios/:id 404', async () => {
  const { status } = await request(app())
    .get(apiRoot + '/123456789098765432123456')
  expect(status).toBe(404)
})

test('PUT /espacios/:id 200', async () => {
  const { status, body } = await request(app())
    .put(`${apiRoot}/${espacio.id}`)
    .send({ nombre: 'test', plantaciones: 'test', dimensiones: 'test' })
  expect(status).toBe(200)
  expect(typeof body).toEqual('object')
  expect(body.id).toEqual(espacio.id)
  expect(body.nombre).toEqual('test')
  expect(body.plantaciones).toEqual('test')
  expect(body.dimensiones).toEqual('test')
})

test('PUT /espacios/:id 404', async () => {
  const { status } = await request(app())
    .put(apiRoot + '/123456789098765432123456')
    .send({ nombre: 'test', plantaciones: 'test', dimensiones: 'test' })
  expect(status).toBe(404)
})

test('DELETE /espacios/:id 204', async () => {
  const { status } = await request(app())
    .delete(`${apiRoot}/${espacio.id}`)
  expect(status).toBe(204)
})

test('DELETE /espacios/:id 404', async () => {
  const { status } = await request(app())
    .delete(apiRoot + '/123456789098765432123456')
  expect(status).toBe(404)
})
