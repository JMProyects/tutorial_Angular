package com.ccsw.tutorial.prestamo;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ccsw.tutorial.client.ClientService;
import com.ccsw.tutorial.common.exception.InvalidDateException;
import com.ccsw.tutorial.common.exception.InvalidDateRangeException;
import com.ccsw.tutorial.common.exception.SameClientMoreThanTwoGamesException;
import com.ccsw.tutorial.common.exception.SameGameTwoClientsException;
import com.ccsw.tutorial.game.GameService;
import com.ccsw.tutorial.prestamo.model.Prestamo;
import com.ccsw.tutorial.prestamo.model.PrestamoDto;
import com.ccsw.tutorial.prestamo.model.PrestamoSearchDto;

@Service
@Transactional
public class PrestamoServiceImpl implements PrestamoService {

    @Autowired
    PrestamoRepository prestamoRepository;

    @Autowired
    GameService gameService;

    @Autowired
    PrestamoService prestamoService;

    @Autowired
    ClientService clientService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Prestamo get(Long id) {

        return this.prestamoRepository.findById(id).orElse(null);
    }

    /*
     **
     * {@inheritDoc}
     */
    @Override
    public Page<Prestamo> find(PrestamoSearchDto dto, Long game, Long client, Date fechaPrestamo) {
        return this.prestamoRepository.find(dto.getPageable(), game, client, fechaPrestamo);
    }

    // @SuppressWarnings("deprecation")
    @Override
    public void save(Long id, PrestamoDto dto) throws InvalidDateException, InvalidDateRangeException,
            SameClientMoreThanTwoGamesException, SameGameTwoClientsException {
        Prestamo prestamo = null;

        if (id == null)
            prestamo = new Prestamo();
        else
            prestamo = this.get(id);

        if (!dto.getFechaPrestamo().after(dto.getFechaDevolucion())) {

            long range = Math.abs(dto.getFechaPrestamo().getTime() - dto.getFechaDevolucion().getTime());
            long daysRange = TimeUnit.DAYS.convert(range, TimeUnit.MILLISECONDS);
            if (daysRange <= 14) {
                if (this.verifyClientMaxTwoGamesPrestamo(dto.getClient().getId())) {
                    if (this.verifyGamesMaxOneClient(dto.getGame().getId())) {
                        prestamo.setGame(gameService.get(dto.getGame().getId()));
                        prestamo.setClient(clientService.get(dto.getClient().getId()));
                        prestamo.setFechaPrestamo(dto.getFechaPrestamo());
                        prestamo.setFechaDevolucion(dto.getFechaDevolucion());
                        this.prestamoRepository.save(prestamo);
                    } else
                        throw new SameGameTwoClientsException("El juego introducido está prestado a dos clientes!");
                } else
                    throw new SameClientMoreThanTwoGamesException("El cliente introducido tiene más de dos juegos!");
            } else
                throw new InvalidDateRangeException(
                        "El rango de fechas introducida es incorrecta. Vuelva a introducirla.");
        } else
            throw new InvalidDateException("La fecha introducida es incorrecta. Vuelva a introducirla.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {

        this.prestamoRepository.deleteById(id);

    }

    @Override
    public List<Prestamo> findAll() {

        return (List<Prestamo>) this.prestamoRepository.findAll();
    }

    public Boolean verifyClientMaxTwoGamesPrestamo(Long dto) {
        Boolean mod = false;
        if (dto < 3) {
            mod = true;
        }
        return mod;
    }

    public Boolean verifyGamesMaxOneClient(Long dto) {
        Boolean mod = false;
        if (dto == 1) {
            mod = true;
        }
        return mod;
    }

}
