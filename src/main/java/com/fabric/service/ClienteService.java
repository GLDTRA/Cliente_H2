package com.fabric.service;

import com.fabric.entity.Cliente;
import com.fabric.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;

    public Cliente saveCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public List<Cliente> getAll(){
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getOneById(@PathVariable Long id){
        return clienteRepository.findById(id);
    }

//    private Cliente update(@PathVariable Long id){
//        Object cliente = clienteRepository.findById(id);
//    }

    public void deleteById(@PathVariable Long id){
        clienteRepository.deleteById(id);
    }
}
