package com.ccsw.tutorial.prestamo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.ccsw.tutorial.prestamo.model.Prestamo;

public class PrestamoTest {

    @Mock
    private PrestamoRepository prestamoRepository;

    @InjectMocks
    private PrestamoServiceImpl prestamoService;

    // Este test nos permite obtener toda la lista de prestamos si hacemos uso de la
    // funcion "findAll()"
    @Test
    public void findAllShouldReturnAllPrestamos() {

        List<Prestamo> list = new ArrayList<>();
        list.add(mock(Prestamo.class));

        when(prestamoRepository.findAll()).thenReturn(list);

        List<Prestamo> prestamos = prestamoService.findAll();

        assertNotNull(prestamos);
        assertEquals(1, prestamos.size());
    }
}
