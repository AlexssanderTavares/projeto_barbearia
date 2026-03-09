import { Card, CardContent, CardTitle } from "./ui/card";
import { Button } from "./ui/button";
import { MenuIcon, FileSpreadsheet, Ghost } from "lucide-react";

function Header () {
    return (
        <div>
            <Card className="h-15 flex flex-row items-center rounded-none border-x-0 border-t-0">
                <CardContent className="w-full flex flex-row justify-between items-center">
                    
                    {/* Menu Hamburguer */}
                    <Button variant="secondary">
                        <MenuIcon />
                    </Button>

                    {/* Logo */}
                    <CardTitle>
                        <h1><span className="text-primary">Brutus</span> BarberClub</h1>    
                    </CardTitle>  

                    {/* Agendamentos */}
                    <Button variant="ghost">
                        <FileSpreadsheet />
                    </Button>
                </CardContent>
            </Card>
        </div>
    )
}

export default Header;