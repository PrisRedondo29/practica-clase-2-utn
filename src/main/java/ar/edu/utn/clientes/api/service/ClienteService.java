package ar.edu.utn.clientes.api.service;

import ar.edu.utn.clientes.api.dto.ClienteDto;
import ar.edu.utn.clientes.api.repository.ClienteRepository;

import java.util.Optional;

public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public Optional<ClienteDto> obtenerCliente(long id) {
        return repository.buscarPorId(id)
                .map(cliente -> new ClienteDto(
                        cliente.getId(),
                        cliente.getNombre(),
                        cliente.getEmail()
                ));
    }
}