import { ProductProvider } from "@/context/ProductContext";

interface Layout {
  children: JSX.Element | JSX.Element[]
}

export default function Layout({children}: Layout) {
  return (
    <ProductProvider>
      {children}
    </ProductProvider>
  );
}