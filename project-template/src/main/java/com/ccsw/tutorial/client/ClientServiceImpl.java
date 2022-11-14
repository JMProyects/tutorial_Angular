package com.ccsw.tutorial.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.tutorial.client.model.Client;
import com.ccsw.tutorial.client.model.ClientDto;
import com.ccsw.tutorial.common.exception.AlreadyExistsException;

/**
 * @author ccsw
 *
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Client get(Long id) {

        return this.clientRepository.findById(id).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Client> findAll() {
        return (List<Client>) this.clientRepository.findAll();
    }

    @Override
    public void save(Long id, ClientDto dto) throws AlreadyExistsException {
        Client cliente = null;
        if (id == null)
            cliente = new Client();
        else
            // cliente = this.clientRepository.findById(id).orElse(null);
            cliente = this.get(id);

        if (this.clientRepository.findByName(dto.getName()) == null) {
            cliente.setName(dto.getName());
            this.clientRepository.save(cliente);
        } else
            throw new AlreadyExistsException("El nombre del cliente ya existe! Introduzca otro.");

        // cliente.setName(dto.getName());

        // this.clientRepository.save(cliente);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        this.clientRepository.deleteById(id);
    }

}
