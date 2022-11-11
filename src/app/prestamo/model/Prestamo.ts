import { Clientes } from "src/app/clientes/model/Clientes";
import { Game } from "src/app/game/model/Game";

export class Prestamo{
    id: number;
    game: Game;
    client: Clientes;
    fechaPrestamo: Date;
    fechaDevolucion: Date;
}