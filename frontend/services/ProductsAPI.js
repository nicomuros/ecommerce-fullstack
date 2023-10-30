import { products } from "@/data/products";

export const getProductListRequest = async () => {
  return {
    status: 200,
    data: products
  }
}

export const getProductDetailRequest = async (id) => {
  const product = products.find(product => product.id == id);
  return {
    status: 200,
    data: product
  }
}