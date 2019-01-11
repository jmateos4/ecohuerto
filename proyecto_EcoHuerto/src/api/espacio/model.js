import mongoose, { Schema } from 'mongoose'

const espacioSchema = new Schema({
  nombre: {
    type: String
  },
  plantaciones: {
    type: String
  },
  dimensiones: {
    type: String
  }
}, {
  timestamps: true,
  toJSON: {
    virtuals: true,
    transform: (obj, ret) => { delete ret._id }
  }
})

espacioSchema.methods = {
  view (full) {
    const view = {
      // simple view
      id: this.id,
      nombre: this.nombre,
      plantaciones: this.plantaciones,
      dimensiones: this.dimensiones,
      createdAt: this.createdAt,
      updatedAt: this.updatedAt
    }

    return full ? {
      ...view
      // add properties for a full view
    } : view
  }
}

const model = mongoose.model('Espacio', espacioSchema)

export const schema = model.schema
export default model
