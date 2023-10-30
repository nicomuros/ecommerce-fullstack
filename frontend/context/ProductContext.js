import { getProductListRequest, getProductDetailRequest } from "@/services/ProductsAPI";
import { createContext, useContext, useState } from "react";

const ProductContext = createContext();

export const useProduct = () => {

  const context = useContext(ProductContext);
  
  if (!context){
    console.log("ERROR: Se intenta usar el hook useProductContext por fuera del proveedor ProductProvider");
  }

  return context;
}

export const ProductProvider = ({children}) => {
  const [productList, setProductList] = useState([]);
  const [productDetail, setProductDetail] = useState({});

  const getProductList = async () => {
    try {
      const response = await getProductListRequest();
      setProductList(response.data);
    } catch(e) {console.log(e)}
  }

  const getProductDetail = async (id) => {
    try {
      const response = await getProductDetailRequest(id);
      setProductDetail(response.data);
    } catch(e) {console.log(e)}
  }

  const addProduct = (product) => {
    setProductList([...productList, product]);
    return true;
  } 

  const modifyProduct = (product) => {
    const index = productList.findIndex(p => p.id === product.id);
    const newList = [...productList];
    newList[index] = product;
    setProductList(newList);
    return true;
  }

  const deleteProduct = (id) => {
    const newList = productList.filter(p => p.id !== id);
    setProductList(newList);
    return true;
  }

  return(
    <ProductContext.Provider value={{
      productList,
      getProductList,
      productDetail,
      getProductDetail,
      modifyProduct,
      deleteProduct
    }}>
      {children}
    </ProductContext.Provider>
  )
}