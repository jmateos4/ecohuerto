import mongoose, { Schema } from 'mongoose'

const inventarioSchema = new Schema({
  nombre: {
    type: String
  },
  tipo: {
    type: String
  },
  cantidad: {
    type: String
  },
  peso: {
    type: String
  }
}, {
  timestamps: true,
  toJSON: {
    virtuals: true,
    transform: (obj, ret) => { delete ret._id }
  }
})

inventarioSchema.methods = {
  view (full) {
    const view = {
      // simple view
      id: this.id,
      nombre: this.nombre,
      tipo: this.tipo,
      cantidad: this.cantidad,
      peso: this.peso,
      createdAt: this.createdAt,
      updatedAt: this.updatedAt
    }

    return full ? {
      ...view
      // add properties for a full view
    } : view
  }
}

const model = mongoose.model('Inventario', inventarioSchema)

export const schema = model.schema
export default model
