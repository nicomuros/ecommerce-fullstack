'use client';

import Image from 'next/image';
import { ToggleButton } from '@/components'
import React, { useEffect, useState } from 'react';
import { useProduct } from '@/context/ProductContext';
import { Button } from '@/components/ui/Button';
import Link from 'next/link';
import Swal from 'sweetalert2';

function ProductCard({product}) {
  const { deleteProduct } = useProduct();

  const handleDelete = () => {
    Swal.fire({
      title: '¿Estás seguro que deseas borrar el producto?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Borrar',
      cancelButtonText: 'Cancelar',

      // Si se confirma la acción, se muestra un mensaje de éxito y se limpia el carrito
    }).then((result) => {
      if (result.isConfirmed) {
        deleteProduct(product.id) &&
        Swal.fire('Eliminado', 'El producto se eliminó', 'success');
        return;
      }
    })
  }
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
        <div className="d-flex flex-col">
          <Link href={`/dashboard/product/modify/${product.id}`}> <a className="m-2">Editar</a></Link>
          <p className="m-2" onClick={handleDelete}>Eliminar</p>
        </div>
      </div>
    <div className="w-full border border-t-0 border-slate-900"></div>
    </>
  );
}

export default function Page() {
  
  const {productList, getProductList} = useProduct();
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    // set product list state
    getProductList();
    setIsLoading(false);   
  }, []);

  if (isLoading) return <p>Cargando</p>
  if (!productList) return <p>No hay datos disponibles</p>

  return (
    <div className='container mx-auto'>
      <div className='flex justify-between align-center py-2 px-10 items-center'>
        <h1 className='font-bold text-2xl align-middle'>Productos</h1>
        <Link href="/dashboard/product/add">
          <Button text="Agregar Producto" />
        </Link>
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
        {productList?.map((p) => (
            <ProductCard key={p.id} product={p} />
          ))}
      </div>
    </div>
  );
}