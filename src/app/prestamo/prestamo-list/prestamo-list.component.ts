import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { DialogConfirmationComponent } from 'src/app/core/dialog-confirmation/dialog-confirmation.component';
import { Pageable } from 'src/app/core/model/page/Pageable';
import { Prestamo } from '../model/Prestamo';
import { PrestamoEditComponent } from '../prestamo-edit/prestamo-edit.component';
import { Game } from 'src/app/game/model/Game';
import { Clientes } from 'src/app/clientes/model/Clientes';
import { PrestamoService } from '../prestamo.service';
import { GameService } from 'src/app/game/game.service';
import { ClientesService } from 'src/app/clientes/clientes.service';
import { PrestamoPage } from '../model/PrestamoPage';


@Component({
  selector: 'app-prestamo-list',
  templateUrl: './prestamo-list.component.html',
  styleUrls: ['./prestamo-list.component.scss']
})
export class PrestamoListComponent implements OnInit {
  clientes : Clientes[];
  games: Game[];
  filterGame: Game;
  filterClient: Clientes;  
  fechaPrestamo: Date;
  prestamos: PrestamoPage;
  
  
  pageNumber: number = 0;
  pageSize: number = 5;
  totalElements: number = 0;

  dataSource = new MatTableDataSource<Prestamo>();
  displayedColumns: string[] = ['id', 'game', 'client', 'fechaPrestamo', 'fechaDevolucion','action'];

  constructor(
    private prestamoService: PrestamoService,
    private gameService: GameService,
    private clientService: ClientesService,
    public dialog: MatDialog
    ) { }

  ngOnInit(): void {
    this.loadPage();
    
    this.gameService.getGames().subscribe(
      games => this.games = games
    );
    
    this.clientService.getClientes().subscribe(
      clientes => this.clientes = clientes
    );   

  }


onCleanFilter(): void {
  //this.filterTitle = null;
  this.filterClient = null;
  this.filterGame = null;
  this.fechaPrestamo = null;

  this.loadPage();
}

//de momento no hacemos nada con esto
onSearch(): void {
 this.loadPage();
}

loadPage(event?: PageEvent) {
  
  let pageable : Pageable =  {
      pageNumber: this.pageNumber,
      pageSize: this.pageSize,
      sort: [{
          property: 'id',
          direction: 'ASC'
      }]
  }

  if (event != null) {
      pageable.pageSize = event.pageSize
      pageable.pageNumber = event.pageIndex;
  }

  let gameId = this.filterGame != null ? this.filterGame.id : null;
  let clientId = this.filterClient != null ? this.filterClient.id : null;
  let fechaPrestamo = this.fechaPrestamo != null ? this.fechaPrestamo : null;

  this.prestamoService.getPrestamos(pageable, gameId, clientId, fechaPrestamo).subscribe(data => {
    this.dataSource.data = data.content;
    this.pageNumber = data.pageable.pageNumber;
    this.pageSize = data.pageable.pageSize;
    this.totalElements = data.totalElements;
});

  //console.log(this)

}  

createPrestamo() {    
  const dialogRef = this.dialog.open(PrestamoEditComponent, {
      data: {}
  });

  dialogRef.afterClosed().subscribe(result => {
      this.ngOnInit();
  });    
}
/*
editPrestamo(prestamo: Prestamo) {
  const dialogRef = this.dialog.open(PrestamoEditComponent, {
    data: { prestamo: prestamo }
  });

  dialogRef.afterClosed().subscribe(result => {
    this.ngOnInit();
  });
}*/

deletePrestamo(prestamo: Prestamo) {    
  const dialogRef = this.dialog.open(DialogConfirmationComponent, {
    data: { title: "Eliminar préstamo", description: "Atención si borra el préstamo se perderán sus datos.<br> ¿Desea eliminar el préstamo?" }
  });

  dialogRef.afterClosed().subscribe(result => {
    if (result) {
      this.prestamoService.deletePrestamo(prestamo.id).subscribe(result => {
        this.ngOnInit();
      }); 
    }
  });
}  


}
