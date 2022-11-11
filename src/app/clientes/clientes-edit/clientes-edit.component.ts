import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { endWith } from 'rxjs';
import { ClientesService } from '../clientes.service';
import { Clientes } from '../model/Clientes';

@Component({
  selector: 'app-clientes-edit',
  templateUrl: './clientes-edit.component.html',
  styleUrls: ['./clientes-edit.component.scss']
})
export class ClientesEditComponent implements OnInit {

  client : Clientes;
  msgException: String;

  constructor(
    public dialogRef: MatDialogRef<ClientesEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private clienteServicio: ClientesService
  ) { }

  ngOnInit(): void {
    if (this.data.client!= null) {
      this.client= Object.assign({}, this.data.client);
    }
    else {
      this.client = new Clientes();
    }
  }

  onSave() {
    this.clienteServicio.saveClient(this.client).subscribe(result => {
      //console.log(result);
      this.dialogRef.close();
    }, (error) => {
      console.log(error,"HTTP.CONFLICT (409)");
      this.msgException = error;
      //this.dialogRef.close();
    });    
  } 

  onClose() {
    this.dialogRef.close();
  }

}
