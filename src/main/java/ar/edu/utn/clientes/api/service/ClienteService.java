package ar.edu.utn.clientes.api.service;

import ar.edu.utn.clientes.api.dto.ClienteDto;
import ar.edu.utn.clientes.api.repository.ClienteRepository;
import ar.edu.utn.clientes.model.Cliente;

public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(
            ClienteRepository repository) {

        this.repository = repository;
    }

    public ClienteDto obtenerCliente(long id) {

        Cliente cliente = repository.buscarPorId(id)
                .orElseThrow(() ->
                        new ClienteNoEncontradoException(id));

        return new ClienteDto(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getEmail()
        );
    }
}