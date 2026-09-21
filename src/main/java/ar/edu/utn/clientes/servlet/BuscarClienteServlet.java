package ar.edu.utn.clientes.servlet;

import ar.edu.utn.clientes.dao.ClienteDao;
import ar.edu.utn.clientes.dao.ClienteDaoMemoria;
import ar.edu.utn.clientes.model.Cliente;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet legacy que busca un cliente por id y hace forward a cliente.jsp.
 * Esta clase NO se modifica durante la practica.
 */
public class BuscarClienteServlet extends HttpServlet {

    private final ClienteDao clienteDao;

    public BuscarClienteServlet() {
        this.clienteDao = new ClienteDaoMemoria();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idParam = req.getParameter("id");
        if (idParam == null || idParam.isBlank()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parametro 'id' requerido");
            return;
        }

        long id;
        try {
            id = Long.parseLong(idParam);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "El parametro 'id' debe ser numerico");
            return;
        }

        Cliente cliente = clienteDao.buscar(id);
        if (cliente == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Cliente no encontrado: " + id);
            return;
        }

        req.setAttribute("cliente", cliente);
        req.getRequestDispatcher("/cliente.jsp").forward(req, resp);
    }
}