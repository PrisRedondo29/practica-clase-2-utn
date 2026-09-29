package ar.edu.utn.clientes.api.controller;

import ar.edu.utn.clientes.api.dto.ClienteDto;
import ar.edu.utn.clientes.api.service.ClienteNoEncontradoException;
import ar.edu.utn.clientes.api.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> buscar(@PathVariable long id) {
        return ResponseEntity.ok(clienteService.obtenerCliente(id));
    }

    @ExceptionHandler(ClienteNoEncontradoException.class)
    public ResponseEntity<Void> clienteNoEncontrado() {
        return ResponseEntity.notFound().build();
    }
}