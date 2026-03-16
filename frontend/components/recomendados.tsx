import { Card, CardTitle, CardContent } from "./ui/card";

export default function Recomendados () {
    return (
        <Card className="mt-5 sm:mt-0">
            <CardTitle>Serviços - Mais Agendados</CardTitle>
            <CardContent className="flex flex-row justify-between ">
                <div className="w-40 h-40 bg-blue-500 flex items-center justify-center">
                    <p>Corte de cabelo</p>
                </div>
                <div className="w-40 h-40 bg-red-500 flex items-center justify-center">
                    <p>Barba</p>
                </div>
                <div className="w-40 h-40 bg-purple-500 flex items-center justify-center">
                    <p>Sombrancelha</p>
                </div>
            </CardContent>
        </Card>
    )
}