import { Espacio } from '.'

let espacio

beforeEach(async () => {
  espacio = await Espacio.create({ nombre: 'test', plantaciones: 'test', dimensiones: 'test' })
})

describe('view', () => {
  it('returns simple view', () => {
    const view = espacio.view()
    expect(typeof view).toBe('object')
    expect(view.id).toBe(espacio.id)
    expect(view.nombre).toBe(espacio.nombre)
    expect(view.plantaciones).toBe(espacio.plantaciones)
    expect(view.dimensiones).toBe(espacio.dimensiones)
    expect(view.createdAt).toBeTruthy()
    expect(view.updatedAt).toBeTruthy()
  })

  it('returns full view', () => {
    const view = espacio.view(true)
    expect(typeof view).toBe('object')
    expect(view.id).toBe(espacio.id)
    expect(view.nombre).toBe(espacio.nombre)
    expect(view.plantaciones).toBe(espacio.plantaciones)
    expect(view.dimensiones).toBe(espacio.dimensiones)
    expect(view.createdAt).toBeTruthy()
    expect(view.updatedAt).toBeTruthy()
  })
})
