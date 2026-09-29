package ar.edu.utn.clientes.api.service;

public class ClienteNoEncontradoException
        extends RuntimeException {

    public ClienteNoEncontradoException(long id) {
        super("Cliente no encontrado: " + id);
    }
}