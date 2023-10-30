"use client"

import { ProductProvider } from '@/context/ProductContext'
import React from 'react'

const layout = ({children}) => {
  return (
    <ProductProvider>
      {children}
    </ProductProvider>
  )
}

export default layout