package ar.edu.utn.clientes.api.config;

import ar.edu.utn.clientes.api.repository.ClienteRepository;
import ar.edu.utn.clientes.api.repository.ClienteRepositoryLegacyAdapter;
import ar.edu.utn.clientes.api.service.ClienteService;
import ar.edu.utn.clientes.dao.ClienteDao;
import ar.edu.utn.clientes.dao.ClienteDaoMemoria;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuracion de Spring. Registra beans con constructor injection.
 * Toda la cadena de dependencias se ensambla aqui: sin anotaciones magicas.
 */
@Configuration
public class AppConfig {

    @Bean
    public ClienteDao clienteDao() {
        return new ClienteDaoMemoria();
    }

    @Bean
    public ClienteRepository clienteRepository(ClienteDao dao) {
        return new ClienteRepositoryLegacyAdapter(dao);
    }

    @Bean
    public ClienteService clienteService(ClienteRepository repository) {
        return new ClienteService(repository);
    }
}