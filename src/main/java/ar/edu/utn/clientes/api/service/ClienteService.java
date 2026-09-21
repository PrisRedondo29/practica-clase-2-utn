package ar.edu.utn.clientes.api.service;

import ar.edu.utn.clientes.api.dto.ClienteDto;
import ar.edu.utn.clientes.api.repository.ClienteRepository;
import ar.edu.utn.clientes.model.Cliente;

/**
 * Contiene la logica del caso de uso "obtener cliente".
 * NO conoce HTTP: no importa nada de jakarta.servlet ni de Spring MVC.
 */
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public ClienteDto obtenerCliente(long id) {
        Cliente cliente = repository.buscarPorId(id)
                .orElseThrow(() -> new ClienteNoEncontradoException("Cliente no encontrado: " + id));
        return new ClienteDto(cliente.getId(), cliente.getNombre(), cliente.getEmail());
    }

    public static class ClienteNoEncontradoException extends RuntimeException {
        public ClienteNoEncontradoException(String message) {
            super(message);
        }
    }
}