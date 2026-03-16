import Image from "next/image";
import { Button } from "@/components/ui/button";
import Header from "../components/header";
import SearchArea from "../components/search";
import Links from "../components/links";
import Recomendados from "../components/recomendados";
import { ScrollArea , ScrollBar  } from "../components/ui/scroll-area";

export default function Home() {
  return (
    <div className="flex min-h-screen items-center justify-center dark:bg-black font-sans ">
      <main className="flex min-h-screen w-full flex-col items-center justify-between bg-zinc-50 dark:bg-black sm:items-start border">
        <section className="w-full"> {/* menu area */}  
          <Header /> {/* header area */}

          <section className="flex items-center p-5 lg:hidden"> {/* search area  */}
            <SearchArea />
          </section>
          <div className="lg:hidden">
            <Links />
          </div>
          <div className="lg:hidden">
            <Recomendados />
          </div>

          {/* Telas maiores */}

          <div className="hidden lg:block">
            {/* Serch and Recommedation */}
            <section className="flex flex-row justify-center gap-5">
              <div className="w-1/3 flex justify-center items-center p-10 ">
                <SearchArea />
              </div>
              <div className="w-2/4 ">
                <Recomendados />
              </div>
            </section>
            <section className=""></section>
          </div>

          <ScrollArea>
            <div></div>
            <ScrollBar orientation="horizontal" />
          </ScrollArea>
          
        </section>
      </main>
    </div>
  );
}
