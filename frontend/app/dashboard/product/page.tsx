'use client';

import Image from 'next/image';
import { ToggleButton } from '@/components'
import React, { useEffect, useState } from 'react';
import axios from "axios";
import { Product } from '@/interfaces/interfaces';
import useProductContext from '@/context/useProductsContext';

function ProductCard(product: Product) {
  return (
    <>
      <div className='grid grid-cols-12 my-5 items-center'>
        <div className="w-[70px] h-[70px] m-2">
          <Image src={product.imageUrl} alt='product' width={60} height={60} />
        </div>
        <p className='col-span-2 m-2'>{product.name}</p>
        <p className='col-span-6 m-2' >{product.description}</p>
        <p className="m-2 before:content-['$'] text-center">{product.price}</p>
        <div className="text-center">< ToggleButton status={product.available} /></div>
      </div>
    <div className="w-full border border-t-0 border-slate-900"></div>
    </>
  );
}

export default function Page() {
  
  const {productList} = useProductContext()

  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    async function getProducts(): Promise<Product[] | null> {
      // fetch product list data from API
      const response = await axios.get('/api/products');
      const data = await response.data;
      // set product list state
      setProductList(data);
      setIsLoading(false);
      return data;
    }
    getProducts();
  }, []);

  if (isLoading) return <p>Cargando</p>
  if (!productList) return <p>No hay datos disponibles</p>

  return (
    <div className='container mx-auto'>
      <div className='flex justify-between align-center py-2 px-10 items-center'>
        <h1 className='font-bold text-2xl align-middle'>Productos</h1>
        <div className='bg-slate-700 rounded hover:bg-slate-600 p-2 px-4 cursor-pointer active:text-gray-300 active:font-normal select-none'>
          <p>Agregar producto</p>
        </div>
      </div>
      <div id='product-box' className='m-6 p-4 bg-slate-800 rounded text-sm'>
        <div id='titles' className='grid grid-cols-12'>
          <h3 className="m-2">Imagen</h3>
          <h3 className="col-span-2 m-2">Título</h3>
          <h3 className='col-span-6 m-2'>Descripción</h3>
          <h3 className="m-2 text-center ">Precio</h3>
          <h3 className="m-2 text-center">Disponible</h3>
          <h3 className="m-2 text-center">Acciones</h3>
        </div>
        <div className="w-full border border-t-0 border-x-0 pt-2 border-slate-900"></div>
        {productList?.map((p: Product) => (
            <ProductCard key={p.id} {...p} />
          ))}
      </div>
    </div>
  );
}
