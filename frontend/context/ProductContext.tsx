import { Product, ProductState } from "@/interfaces/interfaces";
import { useReducer, createContext, useState, PropsWithChildren } from "react";
import { productReducer } from "./ProductReducer";
import { getAllProductsRequest} from "@/services/productsAPI";
interface ProductProviderProps {
  children: JSX.Element | JSX.Element[]
}

const INITIAL_STATE: ProductState = {
  productList: []
}

// Va a alojar la información
export const ProductContext = createContext<Product[]>({productList: []});

export function ProductProvider({children}: ProductProviderProps) {
  const [productList, setProductList] = useState<Product[]>([]);

  const fetchProducts = async () => {
    const productList = await getAllProductsRequest();
    setProductList(productList);
  }

  const ProductProviderFunctions = {
    productList,
    fetchProducts
  }

  return (
    <ProductContext.Provider value={{}}>
      {children}
    </ProductContext.Provider>
  )
}