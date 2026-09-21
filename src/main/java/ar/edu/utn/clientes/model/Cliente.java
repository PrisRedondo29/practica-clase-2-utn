package ar.edu.utn.clientes.model;

/**
 * Modelo de dominio legacy.
 * Clase inmutable: todos los campos son final.
 */
public class Cliente {

    private final long id;
    private final String nombre;
    private final String email;

    public Cliente(long id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return "Cliente{id=" + id + ", nombre=" + nombre + ", email=" + email + "}";
    }
}