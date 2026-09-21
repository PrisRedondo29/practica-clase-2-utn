package ar.edu.utn.clientes.dao;

import ar.edu.utn.clientes.model.Cliente;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementacion en memoria del DAO legacy.
 */
public class ClienteDaoMemoria implements ClienteDao {

    private static final Map<Long, Cliente> CLIENTES = new HashMap<>();

    static {
        CLIENTES.put(1L,  new Cliente(1L,  "Ana",    "ana@example.com"));
        CLIENTES.put(2L,  new Cliente(2L,  "Bruno",  "bruno@example.com"));
        CLIENTES.put(42L, new Cliente(42L, "Carlos", "carlos@example.com"));
    }

    @Override
    public Cliente buscar(long id) {
        return CLIENTES.get(id);
    }
}