import { ProductContext } from '@/context/ProductContext'
import { useContext } from 'react'

const useProductContext = () => {
  const context = useContext(ProductContext)
  
  if (!context) {
    throw new Error('useProductsContext debe ser usado dentro de ProductsProvider')
  }
  return (context);
}

export default useProductContext