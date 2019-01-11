import request from 'supertest'
import { apiRoot } from '../../config'
import express from '../../services/express'
import routes, { Inventario } from '.'

const app = () => express(apiRoot, routes)

let inventario

beforeEach(async () => {
  inventario = await Inventario.create({})
})

test('POST /inventarios 201', async () => {
  const { status, body } = await request(app())
    .post(`${apiRoot}`)
    .send({ nombre: 'test', tipo: 'test', cantidad: 'test', peso: 'test' })
  expect(status).toBe(201)
  expect(typeof body).toEqual('object')
  expect(body.nombre).toEqual('test')
  expect(body.tipo).toEqual('test')
  expect(body.cantidad).toEqual('test')
  expect(body.peso).toEqual('test')
})

test('GET /inventarios 200', async () => {
  const { status, body } = await request(app())
    .get(`${apiRoot}`)
  expect(status).toBe(200)
  expect(Array.isArray(body.rows)).toBe(true)
  expect(Number.isNaN(body.count)).toBe(false)
})

test('GET /inventarios/:id 200', async () => {
  const { status, body } = await request(app())
    .get(`${apiRoot}/${inventario.id}`)
  expect(status).toBe(200)
  expect(typeof body).toEqual('object')
  expect(body.id).toEqual(inventario.id)
})

test('GET /inventarios/:id 404', async () => {
  const { status } = await request(app())
    .get(apiRoot + '/123456789098765432123456')
  expect(status).toBe(404)
})

test('PUT /inventarios/:id 200', async () => {
  const { status, body } = await request(app())
    .put(`${apiRoot}/${inventario.id}`)
    .send({ nombre: 'test', tipo: 'test', cantidad: 'test', peso: 'test' })
  expect(status).toBe(200)
  expect(typeof body).toEqual('object')
  expect(body.id).toEqual(inventario.id)
  expect(body.nombre).toEqual('test')
  expect(body.tipo).toEqual('test')
  expect(body.cantidad).toEqual('test')
  expect(body.peso).toEqual('test')
})

test('PUT /inventarios/:id 404', async () => {
  const { status } = await request(app())
    .put(apiRoot + '/123456789098765432123456')
    .send({ nombre: 'test', tipo: 'test', cantidad: 'test', peso: 'test' })
  expect(status).toBe(404)
})

test('DELETE /inventarios/:id 204', async () => {
  const { status } = await request(app())
    .delete(`${apiRoot}/${inventario.id}`)
  expect(status).toBe(204)
})

test('DELETE /inventarios/:id 404', async () => {
  const { status } = await request(app())
    .delete(apiRoot + '/123456789098765432123456')
  expect(status).toBe(404)
})
