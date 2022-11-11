import { Component, OnInit, Inject } from '@angular/core';
import { Clientes } from 'src/app/clientes/model/Clientes';
import { Game } from 'src/app/game/model/Game';
import { Prestamo } from '../model/Prestamo';
import { PrestamoService } from '../prestamo.service';
import { MatDialogRef } from '@angular/material/dialog';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { GameService } from 'src/app/game/game.service';
import { ClientesService } from 'src/app/clientes/clientes.service';


@Component({
  selector: 'app-prestamo-edit',
  templateUrl: './prestamo-edit.component.html',
  styleUrls: ['./prestamo-edit.component.scss']
})
export class PrestamoEditComponent implements OnInit {
  prestamo: Prestamo;
  games: Game[];
  clientes: Clientes[];
  fechaPrestamo: Date;
  fechaDevolucion: Date;
  msgException: String;


  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    public dialogRef: MatDialogRef<PrestamoEditComponent>,    
    private prestamoService: PrestamoService,
    private gameService: GameService,
    private clientService: ClientesService) { }

  ngOnInit(): void {
    if (this.data.prestamo != null) {
      this.prestamo = Object.assign({}, this.data.prestamo);
  }
  else {
      this.prestamo = new Prestamo();
  }

  this.clientService.getClientes().subscribe(
    clientes => {
        this.clientes = clientes;

        if (this.prestamo.client != null) {
            let clientFilter: Clientes[] = clientes.filter(client => client.id == this.data.prestamo.client.id);
            if (clientFilter != null) {
                this.prestamo.client = clientFilter[0];
            }
        }
    }
);

this.gameService.getGames().subscribe(
    games => {
        this.games = games

        if (this.prestamo.game != null) {
            let gameFilter: Game[] = games.filter(game => game.id == this.data.prestamo.game.id);
            if (gameFilter != null) {
                this.prestamo.game = gameFilter[0];
            }
        }
    }
);
  }

  onSave() {
    this.prestamoService.savePrestamo(this.prestamo).subscribe(result => {
        this.dialogRef.close();
    }, (error) => {
        this.msgException = error;
    });    
}  

onClose() {
    this.dialogRef.close();
}

}
