package ar.edu.utn.clientes.api.dto;

public record ClienteDto(
        long id,
        String nombre,
        String email
) {
}