import { Button } from "./ui/button";
import { MenuIcon, FileSpreadsheet, Scissors, Ghost } from "lucide-react";


export default function Links () {
    return (
        <section className="flex flex-row justify-around  items-center sm:bg-green-600 md:bg-red-500 lg:blue-800 xl:bg-purple-400 ">
            <Button className="flex flex-row" variant="secondary">
                <Scissors /> Cabelo
            </Button >
            <Button variant="secondary">
                <FileSpreadsheet /> Sombrancelha
            </Button>
            <Button variant="secondary">
                <FileSpreadsheet /> Barba
            </Button>
        </section>
    )
}