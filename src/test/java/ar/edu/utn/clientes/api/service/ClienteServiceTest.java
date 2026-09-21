package ar.edu.utn.clientes.api.service;

import ar.edu.utn.clientes.api.dto.ClienteDto;
import ar.edu.utn.clientes.api.repository.ClienteRepositoryLegacyAdapter;
import ar.edu.utn.clientes.api.service.ClienteService.ClienteNoEncontradoException;
import ar.edu.utn.clientes.dao.ClienteDaoMemoria;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClienteServiceTest {

    private ClienteService buildService() {
        return new ClienteService(
            new ClienteRepositoryLegacyAdapter(new ClienteDaoMemoria()));
    }

    @Test
    void obtenerClienteExistente() {
        ClienteDto dto = buildService().obtenerCliente(1L);
        assertEquals("Ana", dto.getNombre());
    }

    @Test
    void obtenerClienteInexistente() {
        assertThrows(ClienteNoEncontradoException.class,
            () -> buildService().obtenerCliente(999L));
    }

    @Test
    void obtenerOtroClienteExistente() {
        ClienteDto dto = buildService().obtenerCliente(2L);
        assertEquals("Bruno", dto.getNombre());
    }
}