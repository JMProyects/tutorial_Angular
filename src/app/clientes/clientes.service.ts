import { Injectable } from '@angular/core';
import { Clientes } from './model/Clientes';
import { CLIENTES_DATA } from './model/mock-clientes';
import { Observable, of} from 'rxjs'
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ClientesService {

  constructor(private http: HttpClient) { }

  getClientes(): Observable<Clientes[]> {
    //return of(CLIENTES_DATA);
    return this.http.get<Clientes[]>('http://localhost:8080/client');    
  }

  saveClient(client: Clientes): Observable<void> {
    let url = 'http://localhost:8080/client';
        if (client.id != null) url += '/'+client.id;

        return this.http.put<void>(url, client);
  }

  deleteClient(idClient : number): Observable<any> {
    return this.http.delete('http://localhost:8080/client/'+idClient);
  } 
}
