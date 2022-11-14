package com.ccsw.tutorial.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ccsw.tutorial.client.model.Client;
import com.ccsw.tutorial.client.model.ClientDto;
import com.ccsw.tutorial.common.exception.AlreadyExistsException;

@ExtendWith(MockitoExtension.class)
public class ClientTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    public static final String CLIENT_NAME = "CLT1";
    public static final String EXISTS_CLIENT_NAME = "CLT1";
    public static final Long EXISTS_CLIENT_ID = 1L;
    public static final Long NOT_EXISTS_CLIENT_ID = 0L;

    // este test sirve para comprobar que al llamar a la función "save()"
    // no existe el id del cliente debería insertar un nuevo cliente.
    @Test
    public void saveNotExistsClientIdShouldInsert() throws AlreadyExistsException {

        ClientDto clientDto = new ClientDto();
        clientDto.setName(CLIENT_NAME);

        ArgumentCaptor<Client> client = ArgumentCaptor.forClass(Client.class);

        clientService.save(null, clientDto);

        verify(clientRepository).save(client.capture());

        assertEquals(CLIENT_NAME, client.getValue().getName());
    }

    // este test nos permite comprobar que si existe un id del cliente llamando a la
    // funcion
    // "save(), solamente debería actualizar/modificar el estado del cliente

    @Test
    public void saveExistsClientIdShouldUpdate() throws AlreadyExistsException {

        ClientDto clientDto = new ClientDto();
        clientDto.setName(CLIENT_NAME);

        Client client = mock(Client.class);
        when(clientRepository.findById(EXISTS_CLIENT_ID)).thenReturn(Optional.of(client));

        clientService.save(EXISTS_CLIENT_ID, clientDto);

        verify(clientRepository).save(client);
    }

    /*
    @Test
    public void saveExistsClientNameShouldReturnError() throws AlreadyExistsException {

        ClientDto clientDto = new ClientDto();
        clientDto.setName(EXISTS_CLIENT_NAME);
        clientDto.setId(EXISTS_CLIENT_ID);

        Client client = mock(Client.class);
        when(clientRepository.findByName(EXISTS_CLIENT_NAME)).thenReturn(client);
        when(clientRepository.findById(EXISTS_CLIENT_ID)).thenReturn(Optional.of(client));

        clientService.save(EXISTS_CLIENT_ID, clientDto);

        verify(clientRepository).save(client);
    }*/

    // Este test nos permite obtener toda la lista de clientes si hacemos uso de la
    // funcion "findAll()"
    @Test
    public void findAllShouldReturnAllClients() {

        List<Client> list = new ArrayList<>();
        list.add(mock(Client.class));

        when(clientRepository.findAll()).thenReturn(list);

        List<Client> clients = clientService.findAll();

        assertNotNull(clients);
        assertEquals(1, clients.size());
    }

    // Este test me permite eliminar un cliente si existe su ID haciendo uso
    // de la funcion delete().
    @Test
    public void deleteExistsClientIdShouldDelete() {

        clientService.delete(EXISTS_CLIENT_ID);

        verify(clientRepository).deleteById(EXISTS_CLIENT_ID);
    }

    // Este test me permite comprobar si existe un ID de cliente mediante la
    // llamada a la funcion
    // "get()", devolviendo un solo cliente.
    @Test
    public void getExistsClientIdShouldReturnClient() {

        Client client = mock(Client.class);
        when(client.getId()).thenReturn(EXISTS_CLIENT_ID);
        when(clientRepository.findById(EXISTS_CLIENT_ID)).thenReturn(Optional.of(client));

        Client clientResponse = clientService.get(EXISTS_CLIENT_ID);

        assertNotNull(clientResponse);
        assertEquals(EXISTS_CLIENT_ID, client.getId());
    }

    // este test permite comprobar que si no existe un cliente ID haciendo uso de la
    // funcion "get()", devolverá null
    @Test
    public void getNotExistsClientIdShouldReturnNull() {

        when(clientRepository.findById(NOT_EXISTS_CLIENT_ID)).thenReturn(Optional.empty());

        Client client = clientService.get(NOT_EXISTS_CLIENT_ID);

        assertNull(client);
    }

}
