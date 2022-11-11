import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Clientes } from '../model/Clientes';
import { MatDialog } from '@angular/material/dialog';
import { ClientesEditComponent } from '../clientes-edit/clientes-edit.component';
import { ClientesService } from '../clientes.service';
import { DialogConfirmationComponent } from 'src/app/core/dialog-confirmation/dialog-confirmation.component';

@Component({
  selector: 'app-clientes-list',
  templateUrl: './clientes-list.component.html',
  styleUrls: ['./clientes-list.component.scss']
})
export class ClientesListComponent implements OnInit {

  dataSource = new MatTableDataSource<Clientes>();
  displayedColumns: string[] = ['id', 'name', 'action'];

  constructor(private clienteServicio: ClientesService,
    public dialog: MatDialog) { }

  ngOnInit(): void {
    this.clienteServicio.getClientes().subscribe(
      clientes => this.dataSource.data = clientes
    );
  }

  createClient() {    
    const dialogRef = this.dialog.open(ClientesEditComponent, {
      data: {}
    });

    dialogRef.afterClosed().subscribe(result => {
      this.ngOnInit();
    });    
  }
  
  editClient(client: Clientes) {
    const dialogRef = this.dialog.open(ClientesEditComponent, {
      data: { client: client }
    });

    dialogRef.afterClosed().subscribe(result => {
      this.ngOnInit();
    });
  }

  deleteClient(client: Clientes) {    
    const dialogRef = this.dialog.open(DialogConfirmationComponent, {
      data: { title: "Eliminar cliente", description: "Atención si borra el cliente se perderán sus datos.<br> ¿Desea eliminar el cliente?" }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.clienteServicio.deleteClient(client.id).subscribe(result => {
          this.ngOnInit();
        }); 
      }
    });
  }  


}
