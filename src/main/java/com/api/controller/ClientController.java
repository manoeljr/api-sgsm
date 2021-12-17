package com.api.controller;

import com.api.domain.Client;
import com.api.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> list() {
        return ResponseEntity.ok(clientService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable long id) {
        return ResponseEntity.ok(clientService.findByIdOrThrowBadRequestException(id));
    }

    @GetMapping("/find")
    public ResponseEntity<List<Client>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(clientService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<Client> save(@RequestBody Client client) {
        return new ResponseEntity<>(clientService.save(client), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody Client client) {
        clientService.replace(client);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        clientService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
