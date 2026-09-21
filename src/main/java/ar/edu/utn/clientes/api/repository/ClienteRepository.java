package ar.edu.utn.clientes.api.repository;

import ar.edu.utn.clientes.model.Cliente;
import java.util.Optional;

/**
 * Frontera de acceso a datos.
 * Contrato moderno que usa Optional para expresar la ausencia de resultado.
 */
public interface ClienteRepository {
    Optional<Cliente> buscarPorId(long id);
}