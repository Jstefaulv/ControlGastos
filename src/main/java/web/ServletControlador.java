package web;

import dao.ProductosDAO;
import dto.Producto;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Controlador")
public class ServletControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest peticion, HttpServletResponse respuesta)
            throws ServletException, IOException {
        String accion = peticion.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "editar":
                    break;
                case "eliminar":
                    break;
                default:
                    this.accionesDefault(peticion, respuesta);
                    break;
            }

        } else {
            this.accionesDefault(peticion, respuesta);
        }

    }

    @Override
    protected void doPost(HttpServletRequest peticion, HttpServletResponse respuesta)
            throws ServletException, IOException {

    }

    private void accionesDefault(HttpServletRequest peticion, HttpServletResponse respuesta)
            throws ServletException, IOException {
        List<Producto> listaProducto = new ProductosDAO().listar();
        HttpSession sesion = peticion.getSession();
        // sesion.setAttribute();
        respuesta.sendRedirect("productos.jsp");

    }
}
