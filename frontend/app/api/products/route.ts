import { products } from "../../../db/products.json";

export async function GET(request: Request): Promise<Response> {
  return Response.json(products);
}