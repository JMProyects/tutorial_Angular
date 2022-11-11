import { PrestamoPage } from "./PrestamoPage"
export const PRESTAMO_DATA: PrestamoPage = {
    content: [
        { id: 1, game: {id: 1, title: 'Juego 1', age: 6, category: {id: 1, name: 'Categoría 1'}, author: {id: 1, name: 'Author 1', nationality: 'Nationality 1'}}, client: {id: 1, name:'Cliente 1'}, fechaPrestamo: new Date('11/02/2022'), fechaDevolucion: new Date('11/09/2022') },
        { id: 2, game: {id: 2, title: 'Juego 2', age: 8, category: {id: 2, name: 'Categoría 2'}, author: {id: 2, name: 'Author 2', nationality: 'Nationality 2'}}, client: {id: 2, name:'Cliente 2'}, fechaPrestamo: new Date('11/03/2022'), fechaDevolucion: new Date('11/10/2022') },
        { id: 3, game: {id: 3, title: 'Juego 3', age: 9, category: {id: 3, name: 'Categoría 3'}, author: {id: 3, name: 'Author 3', nationality: 'Nationality 3'}}, client: {id: 3, name:'Cliente 3'}, fechaPrestamo: new Date('11/04/2022'), fechaDevolucion: new Date('11/11/2022') },
        { id: 4, game: {id: 4, title: 'Juego 4', age: 18, category: {id: 4, name: 'Categoría 4'}, author: {id: 4, name: 'Author 4', nationality: 'Nationality 4'}}, client: {id: 4, name:'Cliente 4'}, fechaPrestamo: new Date('11/05/2022'), fechaDevolucion: new Date('11/12/2022') },
        { id: 5, game: {id: 5, title: 'Juego 5', age: 12, category: {id: 5, name: 'Categoría 5'}, author: {id: 5, name: 'Author 5', nationality: 'Nationality 5'}}, client: {id: 5, name:'Cliente 5'}, fechaPrestamo: new Date('11/06/2022'), fechaDevolucion:new Date('11/13/2022') },
        { id: 6, game: {id: 6, title: 'Juego 6', age: 5, category: {id: 6, name: 'Categoría 6'}, author: {id: 6, name: 'Author 6', nationality: 'Nationality 6'}}, client: {id: 6, name:'Cliente 6'}, fechaPrestamo: new Date('11/07/2022'), fechaDevolucion: new Date('11/14/2022') },
        { id: 7, game: {id: 7, title: 'Juego 7', age: 7, category: {id: 7, name: 'Categoría 7'}, author: {id: 7, name: 'Author 7', nationality: 'Nationality 6'}}, client: {id: 6, name:'Cliente 7'}, fechaPrestamo: new Date('11/08/2022'), fechaDevolucion: new Date('11/15/2022') },
    ],  
    pageable : {
        pageSize: 5,
        pageNumber: 0,
        sort: [
            {property: "id", direction: "ASC"}
        ]
    },
    totalElements: 7
}
