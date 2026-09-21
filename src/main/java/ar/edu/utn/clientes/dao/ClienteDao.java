package ar.edu.utn.clientes.dao;

import ar.edu.utn.clientes.model.Cliente;

/**
 * Interfaz del DAO legacy.
 * Contrato de acceso a datos original de la aplicacion.
 */
public interface ClienteDao {
    Cliente buscar(long id);
}