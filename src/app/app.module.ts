import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CoreModule } from './core/core.module';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { CategoryModule } from './category/category.module';
import { AuthorModule } from './author/author.module';
import { GameModule } from './game/game.module';
import { ClientesModule } from './clientes/clientes.module';
import { PrestamoModule } from './prestamo/prestamo.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CoreModule,
    GameModule,
    AuthorModule,
    CategoryModule,
    ClientesModule,
    PrestamoModule,
    MatDatepickerModule,
    MatNativeDateModule,
    BrowserAnimationsModule,
  ],
  exports:[
    MatDatepickerModule,
    MatNativeDateModule
  ],
  providers: [ 
    MatDatepickerModule,
    MatNativeDateModule,],
    
  bootstrap: [AppComponent]
})
export class AppModule { }
