export function Button({text}) {
  return (
    <div className='bg-slate-700 rounded hover:bg-slate-600 p-2 px-4 cursor-pointer active:text-gray-300 active:font-normal select-none'>
      <p>{text}</p>
    </div>
  ) 
}