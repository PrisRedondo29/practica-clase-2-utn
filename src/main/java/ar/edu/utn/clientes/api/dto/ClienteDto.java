package ar.edu.utn.clientes.api.dto;

public class ClienteDto {

    private final long id;
    private final String nombre;
    private final String email;

    public ClienteDto(long id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }
}