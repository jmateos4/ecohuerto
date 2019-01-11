import mongoose, { Schema } from 'mongoose'

const meteoSchema = new Schema({
  temperatura: {
    type: String
  },
  probabilidad_lluvia: {
    type: String
  },
  viento: {
    type: String
  },
  humedad: {
    type: String
  }
}, {
  timestamps: true,
  toJSON: {
    virtuals: true,
    transform: (obj, ret) => { delete ret._id }
  }
})

meteoSchema.methods = {
  view (full) {
    const view = {
      // simple view
      id: this.id,
      temperatura: this.temperatura,
      probabilidad_lluvia: this.probabilidad_lluvia,
      viento: this.viento,
      humedad: this.humedad,
      createdAt: this.createdAt,
      updatedAt: this.updatedAt
    }

    return full ? {
      ...view
      // add properties for a full view
    } : view
  }
}

const model = mongoose.model('Meteo', meteoSchema)

export const schema = model.schema
export default model
