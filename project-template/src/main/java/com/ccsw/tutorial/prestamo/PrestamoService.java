package com.ccsw.tutorial.prestamo;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import com.ccsw.tutorial.common.exception.InvalidDateException;
import com.ccsw.tutorial.common.exception.InvalidDateRangeException;
import com.ccsw.tutorial.common.exception.SameClientMoreThanTwoGamesException;
import com.ccsw.tutorial.common.exception.SameGameTwoClientsException;
import com.ccsw.tutorial.prestamo.model.Prestamo;
import com.ccsw.tutorial.prestamo.model.PrestamoDto;
import com.ccsw.tutorial.prestamo.model.PrestamoSearchDto;

public interface PrestamoService {

    /**
     * Recupera un {@link com.ccsw.tutorial.prestamo.model.Prestamo} a través de su
     * ID
     * 
     * @param id
     * @return
     */
    Prestamo get(Long id);

    /**
     * Recupera los prestamos filtrando opcionalmente por título y/o cliente y/o
     * fecha
     * 
     * @param idGame
     * @param idClient
     * @param fPrestamo
     * @return
     */
    // List<Prestamo> find(Long idGame, Long idClient, Date fPrestamo);

    Page<Prestamo> find(PrestamoSearchDto dto, Long idGame, Long idClient, Date fechaPrestamo);

    /**
     * Guarda un prestamo, dependiendo de si el id está o no informado
     * 
     * @param id
     * @param dto
     */
    void save(Long id, PrestamoDto dto) throws InvalidDateException, InvalidDateRangeException,
            SameGameTwoClientsException, SameClientMoreThanTwoGamesException;

    /**
     * Método para crear o actualizar un
     * {@link com.ccsw.tutorial.prestamo.model.Prestamo}
     * 
     * @param id
     */
    void delete(Long id);

    /**
     * Recupera un listado de prestamos
     * 
     * @return
     */
    List<Prestamo> findAll();

}
