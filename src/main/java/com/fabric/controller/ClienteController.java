package com.fabric.controller;

import com.fabric.entity.Cliente;
import com.fabric.service.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Cliente salvar(@RequestBody Cliente cliente){
        return clienteService.saveCliente(cliente);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private List<Cliente> getAll(){
        return clienteService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private Cliente getOne(@PathVariable("id") Long id){
    return clienteService.getOneById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void updateCliente(@PathVariable("id") Long id, @RequestBody Cliente cliente){
        clienteService.getOneById(id)
                .map(clienteBase -> {
                    modelMapper.map(cliente, clienteBase);
                    clienteService.saveCliente(clienteBase);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteCliente(@PathVariable("id") Long id){
        clienteService.getOneById(id)
                .map(clienteBase -> {
                    clienteService.deleteById(clienteBase.getId());
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!"));
    }
}
