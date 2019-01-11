import mongoose, { Schema } from 'mongoose'

const huertoSchema = new Schema({
  nombre: {
    type: String
  },
  espacio: {
    type: String
  },
  inventario: {
    type: String
  }
}, {
  timestamps: true,
  toJSON: {
    virtuals: true,
    transform: (obj, ret) => { delete ret._id }
  }
})

huertoSchema.methods = {
  view (full) {
    const view = {
      // simple view
      id: this.id,
      nombre: this.nombre,
      espacio: this.espacio,
      inventario: this.inventario,
      createdAt: this.createdAt,
      updatedAt: this.updatedAt
    }

    return full ? {
      ...view
      // add properties for a full view
    } : view
  }
}

const model = mongoose.model('Huerto', huertoSchema)

export const schema = model.schema
export default model
