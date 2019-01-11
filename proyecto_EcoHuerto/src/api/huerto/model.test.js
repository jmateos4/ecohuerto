import { Huerto } from '.'

let huerto

beforeEach(async () => {
  huerto = await Huerto.create({ nombre: 'test', espacio: 'test', inventario: 'test' })
})

describe('view', () => {
  it('returns simple view', () => {
    const view = huerto.view()
    expect(typeof view).toBe('object')
    expect(view.id).toBe(huerto.id)
    expect(view.nombre).toBe(huerto.nombre)
    expect(view.espacio).toBe(huerto.espacio)
    expect(view.inventario).toBe(huerto.inventario)
    expect(view.createdAt).toBeTruthy()
    expect(view.updatedAt).toBeTruthy()
  })

  it('returns full view', () => {
    const view = huerto.view(true)
    expect(typeof view).toBe('object')
    expect(view.id).toBe(huerto.id)
    expect(view.nombre).toBe(huerto.nombre)
    expect(view.espacio).toBe(huerto.espacio)
    expect(view.inventario).toBe(huerto.inventario)
    expect(view.createdAt).toBeTruthy()
    expect(view.updatedAt).toBeTruthy()
  })
})
