'use client';
import { Button } from '@/components/ui/Button';
import { useFormik } from 'formik';
import * as Yup from 'yup';

export default function Page() {

  const formik = useFormik({
    initialValues: {
      name: '',
      description: '',
      price: 0,
      imageUrl: '',
      available: false,
    },
    validationSchema: Yup.object({
      name: Yup.string().required('Required'),
      description: Yup.string().required('Required'),
      price: Yup.number().required('Required'),
      imageUrl: Yup.string().required('Required'),
      available: Yup.boolean().required('Required'),
    }),
    onSubmit: (values) => {
      console.log(values);
    },
  });

  return (
    <div className='text-center'>
      <h1 className='text-2xl font-bold mb-5'>Agregar nuevo producto</h1>
      <form
        onSubmit={formik.handleSubmit}
        className='flex flex-col items-center gap-4'
      >
        <div className='flex flex-col lg:flex-row justify-start w-full lg:w-3/4 mb-4 gap-4'>
          <label htmlFor='name' className='my-2 lg:mb-0 lg:w-1/4 text-right'>
            Nombre del Producto:
          </label>
          <input
            type='text'
            id='name'
            name='name'
            onChange={formik.handleChange}
            value={formik.values.name || ''}
            className='w-full lg:w-1/2 rounded p-2 border border-gray-300 text-slate-800'
          />
          {formik.touched.name && formik.errors.name ? (
            <div className='text-red-600'>{formik.errors.name}</div>
          ) : null}
        </div>

        <div className='flex flex-col lg:flex-row justify-start w-full lg:w-3/4 mb-4 gap-4'>
          <label
            htmlFor='description'
            className='my-2 lg:mb-0 lg:w-1/4 text-right'
          >
            Descripción:
          </label>
          <textarea
            id='description'
            name='description'
            onChange={formik.handleChange}
            value={formik.values.description || ''}
            className='w-full lg:w-1/2 rounded p-2 border border-gray-300 text-slate-800'
          />
          {formik.touched.description && formik.errors.description ? (
            <div className='text-red-600'>{formik.errors.description}</div>
          ) : null}
        </div>

        <div className='flex flex-col lg:flex-row justify-start w-full lg:w-3/4 mb-4 gap-4'>
          <label htmlFor='price' className='my-2 lg:mb-0 lg:w-1/4 text-right'>
            Precio:
          </label>
          <input
            type='number'
            id='price'
            name='price'
            onChange={formik.handleChange}
            value={formik.values.price || 0}
            className='w-1/2 lg:w-2/12 rounded p-2 border border-gray-300 text-slate-800'
          />
          {formik.touched.price && formik.errors.price ? (
            <div className='text-red-600'>{formik.errors.price}</div>
          ) : null}
        </div>
        
        <div className='flex flex-col lg:flex-row justify-start w-full lg:w-3/4 mb-4 gap-4'>
          <label htmlFor='imgData' className='my-2 lg:mb-0 lg:w-1/4 text-right'>
            URL de la imagen:
          </label>
          <input
            type='text'
            id='imageUrl'
            name='imageUrl'
            onChange={formik.handleChange}
            value={formik.values.imageUrl || ''}
            className='w-full lg:w-1/2 rounded p-2 border border-gray-300 text-slate-800'
          />
          {formik.touched.imageUrl && formik.errors.imageUrl ? (
            <div className='text-red-600'>{formik.errors.imageUrl}</div>
          ) : null}
        </div>
        <div className='flex flex-col lg:flex-row justify-start w-full lg:w-3/4 mb-4 gap-4'>
          <label
            htmlFor='available'
            className=' lg:mb-0 lg:w-1/4 text-right'
          >
            Disponible:
          </label>
          <input
            type='checkbox'
            id='available'
            name='available'
            onChange={formik.handleChange}
            checked={formik.values.available || false}
            className="w-6 h-6"
          />
        </div>

        {formik.touched.available && formik.errors.available ? (
          <div className='text-red-600'>{formik.errors.available}</div>
        ) : null}
        <button type='submit'>
          <Button text="Agregar producto" onClick={formik.handleSubmit}/>
        </button>
      </form>
    </div>
  );
}
