import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { PrestamoPage } from './model/PrestamoPage';
import { PRESTAMO_DATA } from './model/mock-prestamo';
import { Pageable } from 'src/app/core/model/page/Pageable';
import { Prestamo } from './model/Prestamo';

@Injectable({
  providedIn: 'root'
})
export class PrestamoService {

  constructor(private http: HttpClient) { }

  /*Antiguo get de prestamos
  getPrestamos(pageable: Pageable): Observable<PrestamoPage> {
    return of(PRESTAMO_DATA);
    //return this.http.post<PrestamoPage>('http://localhost:8080/prestamo', {pageable:pageable});
  } */

  getPrestamos(pageable: Pageable, gameId?: number, clientId?: number, fechaPrestamo?: Date): Observable<PrestamoPage> {
    
    return this.http.post<PrestamoPage>(this.composeFindUrl(gameId, clientId, fechaPrestamo), {pageable: pageable});
    
  }

  
    private composeFindUrl(gameId?: number, clientId?: number, fechaPrestamo?: Date): string{
      let params = '';
  
          if (gameId != null) {
              params += 'idGame='+gameId;
          }
  
          if (clientId != null) {
              if (params != '') params += "&";
              params += "idClient="+clientId;
          }

          if (fechaPrestamo != null) {
            if (params != '') params += "&";
            params += "fechaPrestamo="+fechaPrestamo.getDate() + "/" + (fechaPrestamo.getMonth() +1) + "/" + fechaPrestamo.getFullYear();
        }
  
          let url = 'http://localhost:8080/prestamo'

          if (params == '') return url;
          else return url + '?'+params;

          
    }
    
  
    deletePrestamo(idClient : number): Observable<any> {
      return this.http.delete<void>('http://localhost:8080/prestamo/'+idClient);
    }
  
    savePrestamo(prestamo : Prestamo): Observable<void> {
      let url = 'http://localhost:8080/prestamo';
    
        if (prestamo.id != null) {
            url += '/'+prestamo.id;
        }
        return this.http.put<void>(url, prestamo); 
    }
  }

  

