import { Button } from "./ui/button";
import { Field } from "./ui/field";
import { Input } from "./ui/input";
import { Card } from "./ui/card";
import { Search } from "lucide-react"

function SearchArea () {
    return (
        <Card className="w-full flex flex-col bg-dark border-none p-0">
            {/* Search bar */}

            <div>
                <h2 className="text-xl font-bold"> Olá, Faça seu login</h2>
                <p>Domingo, 08 de Março</p>
            </div>
            <Field orientation="horizontal" className=" flex flex-row justify-between items-center">
                <Button variant="secondary" className="flex-1 flex-row justify-start">
                    <input className="flex-1" type="search" placeholder="Buscar Serviços"/>
                </Button>
                <Button variant="default">
                    <Search />
                </Button>
            </Field>
        </Card>
    )
}

export default SearchArea;