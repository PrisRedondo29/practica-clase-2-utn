package ar.edu.utn.clientes.api.service;

import ar.edu.utn.clientes.api.dto.ClienteDto;
import ar.edu.utn.clientes.api.repository.ClienteRepository;
import ar.edu.utn.clientes.api.repository.ClienteRepositoryLegacyAdapter;
import ar.edu.utn.clientes.dao.ClienteDaoMemoria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteServiceTest {

    private ClienteService service;

    @BeforeEach
    void setUp() {
        ClienteRepository repository =
                new ClienteRepositoryLegacyAdapter(
                        new ClienteDaoMemoria()
                );

        service = new ClienteService(repository);
    }

    @Test
    void obtenerClienteExistente() {
        ClienteDto cliente =
                service.obtenerCliente(1L);

        assertEquals("Ana", cliente.getNombre());
    }

    @Test
    void obtenerClienteInexistente() {
        assertThrows(
                ClienteNoEncontradoException.class,
                () -> service.obtenerCliente(999L)
        );
    }
}