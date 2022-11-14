package com.ccsw.tutorial.prestamo;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ccsw.tutorial.prestamo.model.Prestamo;

public interface PrestamoRepository extends CrudRepository<Prestamo, Long> {

    @Query("select p from Prestamo p where (:game is null or p.game.id = :game) and (:client is null or p.client.id = :client) and (:fechaPrestamo is null or p.fechaPrestamo = :fechaPrestamo)")
    Page<Prestamo> find(Pageable pageable, @Param("game") Long game, @Param("client") Long client,
            @Param("fechaPrestamo") Date fechaPrestamo);

    Page<Prestamo> findAll(Pageable pageable);

}
