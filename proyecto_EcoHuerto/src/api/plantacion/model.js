import mongoose, { Schema } from 'mongoose'

const plantacionSchema = new Schema({
  nombre: {
    type: String
  },
  tipo: {
    type: String
  },
  riegoAutomatico: {
    type: String
  }
}, {
  timestamps: true,
  toJSON: {
    virtuals: true,
    transform: (obj, ret) => { delete ret._id }
  }
})

plantacionSchema.methods = {
  view (full) {
    const view = {
      // simple view
      id: this.id,
      nombre: this.nombre,
      tipo: this.tipo,
      riegoAutomatico: this.riegoAutomatico,
      createdAt: this.createdAt,
      updatedAt: this.updatedAt
    }

    return full ? {
      ...view
      // add properties for a full view
    } : view
  }
}

const model = mongoose.model('Plantacion', plantacionSchema)

export const schema = model.schema
export default model
