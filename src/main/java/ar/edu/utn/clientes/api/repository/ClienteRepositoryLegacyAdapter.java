package ar.edu.utn.clientes.api.repository;

import ar.edu.utn.clientes.dao.ClienteDao;
import ar.edu.utn.clientes.model.Cliente;
import java.util.Optional;

/**
 * Adapta ClienteDao legacy al contrato ClienteRepository.
 * Patron Adapter: envuelve la interfaz vieja (null-return) en la nueva (Optional).
 */
public class ClienteRepositoryLegacyAdapter implements ClienteRepository {

    private final ClienteDao clienteDao;

    public ClienteRepositoryLegacyAdapter(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    @Override
    public Optional<Cliente> buscarPorId(long id) {
        return Optional.ofNullable(clienteDao.buscar(id));
    }
}