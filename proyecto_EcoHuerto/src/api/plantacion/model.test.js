import { Plantacion } from '.'

let plantacion

beforeEach(async () => {
  plantacion = await Plantacion.create({ nombre: 'test', tipo: 'test', riegoAutomatico: 'test' })
})

describe('view', () => {
  it('returns simple view', () => {
    const view = plantacion.view()
    expect(typeof view).toBe('object')
    expect(view.id).toBe(plantacion.id)
    expect(view.nombre).toBe(plantacion.nombre)
    expect(view.tipo).toBe(plantacion.tipo)
    expect(view.riegoAutomatico).toBe(plantacion.riegoAutomatico)
    expect(view.createdAt).toBeTruthy()
    expect(view.updatedAt).toBeTruthy()
  })

  it('returns full view', () => {
    const view = plantacion.view(true)
    expect(typeof view).toBe('object')
    expect(view.id).toBe(plantacion.id)
    expect(view.nombre).toBe(plantacion.nombre)
    expect(view.tipo).toBe(plantacion.tipo)
    expect(view.riegoAutomatico).toBe(plantacion.riegoAutomatico)
    expect(view.createdAt).toBeTruthy()
    expect(view.updatedAt).toBeTruthy()
  })
})
