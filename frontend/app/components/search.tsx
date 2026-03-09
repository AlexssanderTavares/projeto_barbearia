import { Button } from "./ui/button";
import { Field } from "./ui/field";
import { Input } from "./ui/input";
import { Card } from "./ui/card";
import { Search } from "lucide-react"

function SearchArea () {
    return (
        <Card className="w-full flex flex-col bg-dark border-none p-0">
            {/* Search bar */}
            <h1>Domingo, 08 de Março</h1>
            <Field orientation="horizontal" className=" flex flex-row justify-between items-center">
                <Button variant="secondary" className="flex-1 flex-row justify-start">
                    <input className="flex-1" type="search" placeholder="Buscar Serviços"/>
                </Button>
                <Button variant="default">
                    <Search />
                </Button>
            </Field>

            {/* <Card className="flex flex-row border-none w-full p-0 bg-dark justify-between">
                <Button variant="secondary"><Search /> Navalha</Button>
                <Button variant="secondary"><Search /> Bigode</Button>
                <Button variant="secondary"><Search /> Cabelo</Button>
                <Button variant="secondary"><Search /> Navalha</Button>
                <Button variant="secondary"><Search /> Bigode</Button>
                <Button variant="secondary"><Search /> Cabelo</Button>
            </Card> */}
        </Card>
    )
}

export default SearchArea;