package ar.edu.utn.clientes.dao;

import ar.edu.utn.clientes.model.Cliente;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClienteDaoMemoriaTest {

    private final ClienteDao dao = new ClienteDaoMemoria();

    @Test
    void buscarClienteExistente() {
        Cliente cliente = dao.buscar(1L);
        assertNotNull(cliente);
        assertEquals("Ana", cliente.getNombre());
    }

    @Test
    void buscarClienteInexistente() {
        assertNull(dao.buscar(999L));
    }

    @Test
    void buscarOtroClienteExistente() {
        Cliente cliente = dao.buscar(2L);
        assertNotNull(cliente);
        assertEquals("Bruno", cliente.getNombre());
    }
}