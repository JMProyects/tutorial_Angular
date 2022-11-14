package com.ccsw.tutorial.prestamo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.util.UriComponentsBuilder;

import com.ccsw.tutorial.client.model.ClientDto;
import com.ccsw.tutorial.game.model.GameDto;
import com.ccsw.tutorial.prestamo.model.PrestamoDto;
import com.ccsw.tutorial.prestamo.model.PrestamoSearchDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PrestamoIT {

    public static final String LOCALHOST = "http://localhost:";
    public static final String SERVICE_PATH = "/author/";

    public static final Long DELETE_PRESTAMO_ID = 6L;
    public static final Long MODIFY_PRESTAMO_ID = 3L;
    @SuppressWarnings("deprecation")
    public static final Date NEW_FPRESTAMO = new Date(2000, 11, 02);
    @SuppressWarnings("deprecation")
    public static final Date NEW_FDEVOLUCION = new Date(2000, 11, 02);

    // public static final String NEW_NATIONALITY = "Nueva Nacionalidad";

    private static final int TOTAL_PRESTAMOS = 6;
    private static final int PAGE_SIZE = 5;

    // para los filtros
    private static final String GAME_ID_PARAM = "idGame";
    private static final String CLIENT_ID_PARAM = "idCategory";
    private static final String FPRESTAMO_PARAM = "fecha_prestamo";
    private static final String FDEVOLUCION_PARAM = "fecha_devolucion";
    private static final Long EXISTS_GAME = 6L;
    private static final Long EXISTS_CLIENT = 8L;
    private static final Long NOT_EXISTS_GAME = 0L;
    private static final Long NOT_EXISTS_CLIENT = 0L;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    ParameterizedTypeReference<Page<PrestamoDto>> responseTypePage = new ParameterizedTypeReference<Page<PrestamoDto>>() {
    };

    ParameterizedTypeReference<List<PrestamoDto>> responseType = new ParameterizedTypeReference<List<PrestamoDto>>() {
    };

    private String getUrlWithParams() {
        return UriComponentsBuilder.fromHttpUrl(LOCALHOST + port + SERVICE_PATH)
                .queryParam(GAME_ID_PARAM, "{" + GAME_ID_PARAM + "}")
                .queryParam(CLIENT_ID_PARAM, "{" + CLIENT_ID_PARAM + "}")
                .queryParam(FPRESTAMO_PARAM, "{" + FPRESTAMO_PARAM + "}")
                .queryParam(FDEVOLUCION_PARAM, "{" + FDEVOLUCION_PARAM + "}").encode().toUriString();
    }

    @Test
    public void findWithoutFiltersShouldReturnAllPrestamosInDB() {

        int PRESTAMOS_WITH_FILTER = 6;

        Map<String, Object> params = new HashMap<>();
        params.put(GAME_ID_PARAM, null);
        params.put(CLIENT_ID_PARAM, null);
        params.put(FPRESTAMO_PARAM, null);
        params.put(FDEVOLUCION_PARAM, null);

        ResponseEntity<List<PrestamoDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null,
                responseType, params);

        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().size());
    }

    @Test
    public void findFirstPageWithFiveSizeShouldReturnFirstFiveResults() {

        PrestamoSearchDto searchDto = new PrestamoSearchDto();
        searchDto.setPageable(PageRequest.of(0, PAGE_SIZE));

        ResponseEntity<Page<PrestamoDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(TOTAL_PRESTAMOS, response.getBody().getTotalElements());
        assertEquals(PAGE_SIZE, response.getBody().getContent().size());
    }

    @Test
    public void findSecondPageWithFiveSizeShouldReturnLastResult() {

        int elementsCount = TOTAL_PRESTAMOS - PAGE_SIZE;

        PrestamoSearchDto searchDto = new PrestamoSearchDto();
        searchDto.setPageable(PageRequest.of(1, PAGE_SIZE));

        ResponseEntity<Page<PrestamoDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(TOTAL_PRESTAMOS, response.getBody().getTotalElements());
        assertEquals(elementsCount, response.getBody().getContent().size());
    }

    @Test
    public void findExistsGameShouldReturnPrestamo() {

        int PRESTAMOS_WITH_FILTER = 6;

        Map<String, Object> params = new HashMap<>();
        params.put(GAME_ID_PARAM, EXISTS_GAME);
        params.put(CLIENT_ID_PARAM, null);
        params.put(FPRESTAMO_PARAM, null);
        params.put(FDEVOLUCION_PARAM, null);

        ResponseEntity<List<PrestamoDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null,
                responseType, params);

        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().size());
    }

    @Test
    public void findExistsClientShouldReturnPrestamo() {

        int PRESTAMOS_WITH_FILTER = 6;

        Map<String, Object> params = new HashMap<>();
        params.put(GAME_ID_PARAM, null);
        params.put(CLIENT_ID_PARAM, EXISTS_CLIENT);
        params.put(FPRESTAMO_PARAM, null);
        params.put(FDEVOLUCION_PARAM, null);

        ResponseEntity<List<PrestamoDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null,
                responseType, params);

        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().size());
    }

    @Test
    public void findExistsFPrestamoShouldReturnPrestamo() {

        int PRESTAMOS_WITH_FILTER = 6;

        Map<String, Object> params = new HashMap<>();
        params.put(GAME_ID_PARAM, null);
        params.put(CLIENT_ID_PARAM, null);
        params.put(FPRESTAMO_PARAM, NEW_FPRESTAMO);
        params.put(FDEVOLUCION_PARAM, null);

        ResponseEntity<List<PrestamoDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null,
                responseType, params);

        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().size());
    }

    @Test
    public void findExistsFDevolucionShouldReturnPrestamo() {

        int PRESTAMOS_WITH_FILTER = 6;

        Map<String, Object> params = new HashMap<>();
        params.put(GAME_ID_PARAM, null);
        params.put(CLIENT_ID_PARAM, null);
        params.put(FPRESTAMO_PARAM, null);
        params.put(FDEVOLUCION_PARAM, NEW_FDEVOLUCION);

        ResponseEntity<List<PrestamoDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null,
                responseType, params);

        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().size());
    }

    @Test
    public void findExistsGameAndClientShouldReturnPrestamo() {

        int PRESTAMOS_WITH_FILTER = 6;

        Map<String, Object> params = new HashMap<>();
        params.put(GAME_ID_PARAM, EXISTS_GAME);
        params.put(CLIENT_ID_PARAM, EXISTS_CLIENT);
        params.put(FPRESTAMO_PARAM, null);
        params.put(FDEVOLUCION_PARAM, null);

        ResponseEntity<List<PrestamoDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null,
                responseType, params);

        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().size());
    }

    @Test
    public void findExistsGameAndFPrestamoShouldReturnPrestamo() {

        int PRESTAMOS_WITH_FILTER = 6;

        Map<String, Object> params = new HashMap<>();
        params.put(GAME_ID_PARAM, EXISTS_GAME);
        params.put(CLIENT_ID_PARAM, null);
        params.put(FPRESTAMO_PARAM, NEW_FPRESTAMO);
        params.put(FDEVOLUCION_PARAM, null);

        ResponseEntity<List<PrestamoDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null,
                responseType, params);

        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().size());
    }

    @Test
    public void findExistsGameAndFDevolucionShouldReturnPrestamo() {

        int PRESTAMOS_WITH_FILTER = 6;

        Map<String, Object> params = new HashMap<>();
        params.put(GAME_ID_PARAM, EXISTS_GAME);
        params.put(CLIENT_ID_PARAM, null);
        params.put(FPRESTAMO_PARAM, null);
        params.put(FDEVOLUCION_PARAM, NEW_FDEVOLUCION);

        ResponseEntity<List<PrestamoDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null,
                responseType, params);

        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().size());
    }

    @Test
    public void findExistsClientAndFPrestamoShouldReturnPrestamo() {

        int PRESTAMOS_WITH_FILTER = 6;

        Map<String, Object> params = new HashMap<>();
        params.put(GAME_ID_PARAM, null);
        params.put(CLIENT_ID_PARAM, EXISTS_CLIENT);
        params.put(FPRESTAMO_PARAM, NEW_FPRESTAMO);
        params.put(FDEVOLUCION_PARAM, null);

        ResponseEntity<List<PrestamoDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null,
                responseType, params);

        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().size());
    }

    @Test
    public void findExistsClientAndFDevolucionShouldReturnPrestamo() {

        int PRESTAMOS_WITH_FILTER = 6;

        Map<String, Object> params = new HashMap<>();
        params.put(GAME_ID_PARAM, null);
        params.put(CLIENT_ID_PARAM, EXISTS_CLIENT);
        params.put(FPRESTAMO_PARAM, null);
        params.put(FDEVOLUCION_PARAM, NEW_FDEVOLUCION);

        ResponseEntity<List<PrestamoDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null,
                responseType, params);

        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().size());
    }

    @Test
    public void findExistsFPrestamoAndFDevolucionShouldReturnPrestamo() {

        int PRESTAMOS_WITH_FILTER = 6;

        Map<String, Object> params = new HashMap<>();
        params.put(GAME_ID_PARAM, null);
        params.put(CLIENT_ID_PARAM, null);
        params.put(FPRESTAMO_PARAM, NEW_FPRESTAMO);
        params.put(FDEVOLUCION_PARAM, NEW_FDEVOLUCION);

        ResponseEntity<List<PrestamoDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null,
                responseType, params);

        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().size());
    }

    @Test
    public void findExistsGameAndFPrestamoAndFDevolucionShouldReturnPrestamo() {

        int PRESTAMOS_WITH_FILTER = 6;

        Map<String, Object> params = new HashMap<>();
        params.put(GAME_ID_PARAM, EXISTS_GAME);
        params.put(CLIENT_ID_PARAM, null);
        params.put(FPRESTAMO_PARAM, NEW_FPRESTAMO);
        params.put(FDEVOLUCION_PARAM, NEW_FDEVOLUCION);

        ResponseEntity<List<PrestamoDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null,
                responseType, params);

        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().size());
    }

    @Test
    public void findExistsClientAndFPrestamoAndFDevolucionShouldReturnPrestamo() {

        int PRESTAMOS_WITH_FILTER = 6;

        Map<String, Object> params = new HashMap<>();
        params.put(GAME_ID_PARAM, null);
        params.put(CLIENT_ID_PARAM, EXISTS_CLIENT);
        params.put(FPRESTAMO_PARAM, NEW_FPRESTAMO);
        params.put(FDEVOLUCION_PARAM, NEW_FDEVOLUCION);

        ResponseEntity<List<PrestamoDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null,
                responseType, params);

        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().size());
    }

    @Test
    public void findExistsGameAndClientAndFPrestamoAndFDevolucionShouldReturnPrestamo() {

        int PRESTAMOS_WITH_FILTER = 6;

        Map<String, Object> params = new HashMap<>();
        params.put(GAME_ID_PARAM, EXISTS_GAME);
        params.put(CLIENT_ID_PARAM, EXISTS_CLIENT);
        params.put(FPRESTAMO_PARAM, NEW_FPRESTAMO);
        params.put(FDEVOLUCION_PARAM, NEW_FDEVOLUCION);

        ResponseEntity<List<PrestamoDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null,
                responseType, params);

        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().size());
    }

    @Test
    public void findNotExistsGameShouldReturnEmpty() {

        int PRESTAMOS_WITH_FILTER = 6;

        Map<String, Object> params = new HashMap<>();
        params.put(GAME_ID_PARAM, NOT_EXISTS_GAME);
        params.put(CLIENT_ID_PARAM, null);
        params.put(FPRESTAMO_PARAM, null);
        params.put(FDEVOLUCION_PARAM, null);

        ResponseEntity<List<PrestamoDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null,
                responseType, params);

        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().size());
    }

    @Test
    public void findNotExistsClientShouldReturnEmpty() {

        int PRESTAMOS_WITH_FILTER = 6;

        Map<String, Object> params = new HashMap<>();
        params.put(GAME_ID_PARAM, null);
        params.put(CLIENT_ID_PARAM, NOT_EXISTS_CLIENT);
        params.put(FPRESTAMO_PARAM, null);
        params.put(FDEVOLUCION_PARAM, null);

        ResponseEntity<List<PrestamoDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null,
                responseType, params);

        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().size());
    }

    @Test
    public void findNotExistsGameOrClientShouldReturnEmpty() {

        int PRESTAMOS_WITH_FILTER = 6;

        Map<String, Object> params = new HashMap<>();
        params.put(GAME_ID_PARAM, NOT_EXISTS_GAME);
        params.put(CLIENT_ID_PARAM, NOT_EXISTS_CLIENT);
        params.put(FPRESTAMO_PARAM, null);
        params.put(FDEVOLUCION_PARAM, null);

        ResponseEntity<List<PrestamoDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null,
                responseType, params);
        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().size());

        params.put(GAME_ID_PARAM, NOT_EXISTS_GAME);
        params.put(CLIENT_ID_PARAM, EXISTS_CLIENT);
        params.put(FPRESTAMO_PARAM, null);
        params.put(FDEVOLUCION_PARAM, null);

        response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null, responseType, params);
        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().size());

        params.put(GAME_ID_PARAM, EXISTS_GAME);
        params.put(CLIENT_ID_PARAM, NOT_EXISTS_CLIENT);
        params.put(FPRESTAMO_PARAM, null);
        params.put(FDEVOLUCION_PARAM, null);

        response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null, responseType, params);
        assertNotNull(response);
        assertEquals(PRESTAMOS_WITH_FILTER, response.getBody().size());
    }

    @Test
    public void saveWithoutIdShouldCreateNewPrestamo() {

        PrestamoDto dto = new PrestamoDto();
        GameDto gameDto = new GameDto();
        gameDto.setId(1L);

        ClientDto clientDto = new ClientDto();
        clientDto.setId(1L);

        dto.setGame(gameDto);
        dto.setClient(clientDto);
        dto.setFechaPrestamo(NEW_FPRESTAMO);
        dto.setFechaDevolucion(NEW_FDEVOLUCION);

        Map<String, Object> params = new HashMap<>();
        params.put(GAME_ID_PARAM, null);
        params.put(CLIENT_ID_PARAM, null);
        params.put(FPRESTAMO_PARAM, NEW_FPRESTAMO);
        params.put(FDEVOLUCION_PARAM, NEW_FDEVOLUCION);

        ResponseEntity<List<PrestamoDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null,
                responseType, params);

        assertNotNull(response);
        assertEquals(6, response.getBody().size());

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, new HttpEntity<>(dto), Void.class);

        response = restTemplate.exchange(getUrlWithParams(), HttpMethod.GET, null, responseType, params);

        assertNotNull(response);
        assertEquals(6, response.getBody().size());
    }

}
