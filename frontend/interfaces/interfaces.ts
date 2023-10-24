export interface Product {
  id: number,
  name: string,
  description: string,
  price: number,
  imageUrl: string,
  available: boolean
}

export interface ProductState{
  productList: Product[]
}