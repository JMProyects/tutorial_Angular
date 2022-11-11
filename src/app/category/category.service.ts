import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Category } from './model/Category';
//import { CATEGORY_DATA } from './model/mock-categories';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http: HttpClient) { }

  getCategories(): Observable<Category[]> {
    return this.http.get<Category[]>('http://localhost:8080/category');
  }

  //Aqui no se pone Observable<Category> ya que en el back definimos el método como void.
  saveCategory(category: Category): Observable<void> {
    let url = 'http://localhost:8080/category';
        if (category.id != null) url += '/'+category.id;

        return this.http.put<void>(url, category);
  }

  //Aqui vale pone Observable<any> o Observable<void>. Son lo mismo, any es un tipo de void.
  //Habría que añadir en el metodo delete de abajo detras el tipo void: http.delete<void>...
  deleteCategory(idCategory : number): Observable<any> {
    return this.http.delete('http://localhost:8080/category/'+idCategory);
  }
}
