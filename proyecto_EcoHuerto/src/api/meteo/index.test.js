import request from 'supertest'
import { apiRoot } from '../../config'
import express from '../../services/express'
import routes, { Meteo } from '.'

const app = () => express(apiRoot, routes)

let meteo

beforeEach(async () => {
  meteo = await Meteo.create({})
})

test('POST /centroMeteo 201', async () => {
  const { status, body } = await request(app())
    .post(`${apiRoot}`)
    .send({ temperatura: 'test', probabilidad_lluvia: 'test', viento: 'test', humedad: 'test' })
  expect(status).toBe(201)
  expect(typeof body).toEqual('object')
  expect(body.temperatura).toEqual('test')
  expect(body.probabilidad_lluvia).toEqual('test')
  expect(body.viento).toEqual('test')
  expect(body.humedad).toEqual('test')
})

test('GET /centroMeteo 200', async () => {
  const { status, body } = await request(app())
    .get(`${apiRoot}`)
  expect(status).toBe(200)
  expect(Array.isArray(body.rows)).toBe(true)
  expect(Number.isNaN(body.count)).toBe(false)
})

test('GET /centroMeteo/:id 200', async () => {
  const { status, body } = await request(app())
    .get(`${apiRoot}/${meteo.id}`)
  expect(status).toBe(200)
  expect(typeof body).toEqual('object')
  expect(body.id).toEqual(meteo.id)
})

test('GET /centroMeteo/:id 404', async () => {
  const { status } = await request(app())
    .get(apiRoot + '/123456789098765432123456')
  expect(status).toBe(404)
})

test('PUT /centroMeteo/:id 200', async () => {
  const { status, body } = await request(app())
    .put(`${apiRoot}/${meteo.id}`)
    .send({ temperatura: 'test', probabilidad_lluvia: 'test', viento: 'test', humedad: 'test' })
  expect(status).toBe(200)
  expect(typeof body).toEqual('object')
  expect(body.id).toEqual(meteo.id)
  expect(body.temperatura).toEqual('test')
  expect(body.probabilidad_lluvia).toEqual('test')
  expect(body.viento).toEqual('test')
  expect(body.humedad).toEqual('test')
})

test('PUT /centroMeteo/:id 404', async () => {
  const { status } = await request(app())
    .put(apiRoot + '/123456789098765432123456')
    .send({ temperatura: 'test', probabilidad_lluvia: 'test', viento: 'test', humedad: 'test' })
  expect(status).toBe(404)
})

test('DELETE /centroMeteo/:id 204', async () => {
  const { status } = await request(app())
    .delete(`${apiRoot}/${meteo.id}`)
  expect(status).toBe(204)
})

test('DELETE /centroMeteo/:id 404', async () => {
  const { status } = await request(app())
    .delete(apiRoot + '/123456789098765432123456')
  expect(status).toBe(404)
})
