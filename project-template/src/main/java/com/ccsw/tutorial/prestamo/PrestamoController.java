package com.ccsw.tutorial.prestamo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.tutorial.common.exception.InvalidDateException;
import com.ccsw.tutorial.common.exception.InvalidDateRangeException;
import com.ccsw.tutorial.common.exception.SameClientMoreThanTwoGamesException;
import com.ccsw.tutorial.common.exception.SameGameTwoClientsException;
import com.ccsw.tutorial.config.mapper.BeanMapper;
import com.ccsw.tutorial.prestamo.model.PrestamoDto;
import com.ccsw.tutorial.prestamo.model.PrestamoSearchDto;

@RequestMapping(value = "/prestamo")
@RestController
@CrossOrigin(origins = "*")

public class PrestamoController {

    @Autowired
    PrestamoService prestamoService;

    @Autowired
    BeanMapper beanMapper;

    @RequestMapping(path = "", method = RequestMethod.POST)
    public Page<PrestamoDto> find(@RequestBody PrestamoSearchDto dto,
            @RequestParam(value = "idGame", required = false) Long idGame,
            @RequestParam(value = "idClient", required = false) Long idClient,
            @RequestParam(value = "fechaPrestamo", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date fechaPrestamo) {
        // List<Prestamo> prestamos = prestamoService.find(idGame, idClient, fPrestamo);

        return beanMapper.mapPage(this.prestamoService.find(dto, idGame, idClient, fechaPrestamo), PrestamoDto.class);
    }

    /**
     * Método para crear o actualizar un
     * {@link com.ccsw.tutorial.prestamo.model.Prestamo}
     * 
     * @param id
     * @param data datos de la entidad
     */
    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody PrestamoDto data)
            throws InvalidDateException, InvalidDateRangeException, SameClientMoreThanTwoGamesException,
            SameGameTwoClientsException {

        this.prestamoService.save(id, data);
    }

    /**
     * Método para crear o actualizar un
     * {@link com.ccsw.tutorial.prestamo.model.Prestamo}
     * 
     * @param id PK de la entidad
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {

        this.prestamoService.delete(id);
    }

    /**
     * Recupera un listado de prestamos
     * 
     * @return
     *
     * @RequestMapping(path = "", method = RequestMethod.GET) public
     *                      List<PrestamoDto> findAll() {
     * 
     *                      List<Prestamo> prestamos =
     *                      this.prestamoService.findAll();
     * 
     *                      return this.beanMapper.mapList(prestamos,
     *                      PrestamoDto.class); }
     */
}
