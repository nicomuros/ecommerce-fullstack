// Componente contenedor, que contendrá todos los componentes que vamos a estar creando
'use client'

import { Provider } from 'react-redux'
import { store } from "./store"

interface Props { children: React.ReactNode}

export function Providers({children}: Props){
  return (<Provider store={store}>
    { children }
  </Provider>);

}