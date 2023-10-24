import Sidebar from "@/components/Sidebar";
import { Providers } from "@/redux/providers";


export default function Layout({children}: {children: React.ReactNode}) {
  return (
    <Providers> 
      <div className="h-[calc(100vh-90px)] flex flex-row justify-start">
        <div className="overflow-y-auto w-96">
          <Sidebar />
        </div>
        <div className="bg-slate-900 ml-2 w-full rounded border border-slate-700 p-5 overflow-auto">
          {children}
        </div>
      </div>
    </Providers>
  );
}

