import { Inventario } from '.'

let inventario

beforeEach(async () => {
  inventario = await Inventario.create({ nombre: 'test', tipo: 'test', cantidad: 'test', peso: 'test' })
})

describe('view', () => {
  it('returns simple view', () => {
    const view = inventario.view()
    expect(typeof view).toBe('object')
    expect(view.id).toBe(inventario.id)
    expect(view.nombre).toBe(inventario.nombre)
    expect(view.tipo).toBe(inventario.tipo)
    expect(view.cantidad).toBe(inventario.cantidad)
    expect(view.peso).toBe(inventario.peso)
    expect(view.createdAt).toBeTruthy()
    expect(view.updatedAt).toBeTruthy()
  })

  it('returns full view', () => {
    const view = inventario.view(true)
    expect(typeof view).toBe('object')
    expect(view.id).toBe(inventario.id)
    expect(view.nombre).toBe(inventario.nombre)
    expect(view.tipo).toBe(inventario.tipo)
    expect(view.cantidad).toBe(inventario.cantidad)
    expect(view.peso).toBe(inventario.peso)
    expect(view.createdAt).toBeTruthy()
    expect(view.updatedAt).toBeTruthy()
  })
})
