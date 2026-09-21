package ar.edu.utn.clientes.api.controller;

import ar.edu.utn.clientes.api.dto.ClienteDto;
import ar.edu.utn.clientes.api.service.ClienteService;
import ar.edu.utn.clientes.api.service.ClienteService.ClienteNoEncontradoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller REST. Solo mapea HTTP. Sin logica de negocio.
 * Delega toda la logica en ClienteService.
 */
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> obtenerCliente(@PathVariable long id) {
        try {
            ClienteDto dto = service.obtenerCliente(id);
            return ResponseEntity.ok(dto);
        } catch (ClienteNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }
}