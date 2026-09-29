package ar.edu.utn.clientes.api.repository;

import ar.edu.utn.clientes.model.Cliente;

import java.util.Optional;

public interface ClienteRepository {

    Optional<Cliente> buscarPorId(long id);
}