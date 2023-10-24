import products from "@/data/products.json"
export async function getAllProductsRequest(){
  return products.products
}