import { Meteo } from '.'

let meteo

beforeEach(async () => {
  meteo = await Meteo.create({ temperatura: 'test', probabilidad_lluvia: 'test', viento: 'test', humedad: 'test' })
})

describe('view', () => {
  it('returns simple view', () => {
    const view = meteo.view()
    expect(typeof view).toBe('object')
    expect(view.id).toBe(meteo.id)
    expect(view.temperatura).toBe(meteo.temperatura)
    expect(view.probabilidad_lluvia).toBe(meteo.probabilidad_lluvia)
    expect(view.viento).toBe(meteo.viento)
    expect(view.humedad).toBe(meteo.humedad)
    expect(view.createdAt).toBeTruthy()
    expect(view.updatedAt).toBeTruthy()
  })

  it('returns full view', () => {
    const view = meteo.view(true)
    expect(typeof view).toBe('object')
    expect(view.id).toBe(meteo.id)
    expect(view.temperatura).toBe(meteo.temperatura)
    expect(view.probabilidad_lluvia).toBe(meteo.probabilidad_lluvia)
    expect(view.viento).toBe(meteo.viento)
    expect(view.humedad).toBe(meteo.humedad)
    expect(view.createdAt).toBeTruthy()
    expect(view.updatedAt).toBeTruthy()
  })
})
