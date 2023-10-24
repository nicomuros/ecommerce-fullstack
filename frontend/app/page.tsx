import Image from 'next/image';

function Card() {
  return (
    <div className='rounded-sm bg-gray-900 text-white max-w-xs border border-gray-700 shadow-md shadow-gray-800 hover:shadow-lg hover:shadow-gray-800'>
      <img src='https://i.blogs.es/e32e91/trucos-enfocar-fotografia-paisaje-01/1366_2000.jpg' />
      <div id='text-wrapper' className='ml-6 mr-6 mt-4'>
        <h3 className='font-bold'>The Coldest Sunset</h3>
        <p className='mt-2'>
          
        </p>
      </div>
      <div id='hashtags-wrapper' className='flex flex-row gap-2 m-6 mt-3'>
        <p className="bg-gray-600 px-2 py-0.5 text-sm font-light rounded-full before:content-['#']">
          photography
        </p>
        <p className="bg-gray-600 px-2 py-0.5 text-sm font-light rounded-full before:content-['#']">
          travel
        </p>
        <p className="bg-gray-600 px-2 py-0.5 text-sm font-light rounded-full before:content-['#']">
          winter
        </p>
      </div>
    </div>
  );
}

export default function Home() {
  return (
    <main className='container mx-auto flex justify-center'>
      <div id="cards-container" className="grid md:grid-cols-3 xl:grid-cols-4 gap-4 mt-20">
        <Card/>
        <Card/>
        <Card/>
        <Card/>
      </div>
    </main>
  );
}
