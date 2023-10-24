// Acá va a estar el estado, un objeto con valores que podemos leer. Vamos a definir que datos vamos a poder leer

import { configureStore } from "@reduxjs/toolkit"
import productReducer from "./features/productSlice"

export const store = configureStore({
  reducer: {
    productReducer
  }
})

export type RootState = ReturnType<typeof store.getState>
export type AppDispatch = typeof store.dispatch