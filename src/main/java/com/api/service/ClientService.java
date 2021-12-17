package com.api.service;

import com.api.domain.Client;
import com.api.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public List<Client> listAll() {
        return clientRepository.findAll();
    }

    public Client findByIdOrThrowBadRequestException(long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client not found"));
    }

    public List<Client> findByName(String name) {
        return clientRepository.findByName(name);
    }

//    Falta alterar o mapeamento
    public Client save(Client client) {
        return clientRepository.save(client);
    }

//    Falta alterar
    public void replace(Client client) {
        Client savedClient = findByIdOrThrowBadRequestException(client.getId());
//        Client client =
        client.setId(savedClient.getId());
        clientRepository.save(client);
    }

    public void delete(long id) {
        clientRepository.delete(findByIdOrThrowBadRequestException(id));
    }
}
