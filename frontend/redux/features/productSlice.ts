// nombre+Slic

import products from "@/db/products.json"
import { createSlice } from '@reduxjs/toolkit'


/**
 * Usando el useState normal:
 * const [state, setState] = useState(initialValue);
 * state -> productList dentro de initialState
 * initialValue -> [] de productList: []
 */

const initialState = {
  products: products
}

export const productSlice = createSlice({
  name: "products",
  initialState,
  reducers: {

  }
})

export default productSlice.reducer;