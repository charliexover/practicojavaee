
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

import com.practico.entidad.TrabajadorDeLaSalud;
import com.practico.excepcion.ExcepcionDeNegocio;
import com.practico.servicios.TrabajadorDeLaSaludServiceRemote;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name = "testDaoServlet", value = "/testDao-servlet")
public class testDaoServlet extends HttpServlet{
    @EJB
    private TrabajadorDeLaSaludServiceRemote service;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
                List<TrabajadorDeLaSalud> lista = service.listar();
                if(lista.isEmpty()) {
                    try {
                        TrabajadorDeLaSalud t1 = new TrabajadorDeLaSalud();
                        t1.setNombre("Pepe");
                        t1.setMatricula(42554);
                        t1.setFechaRegistro(LocalDate.now());
                        service.agregarTrabajador(t1);
                        
                        TrabajadorDeLaSalud t2 = new TrabajadorDeLaSalud();
                        t2.setNombre("Maria");
                        t2.setMatricula(23451);
                        t2.setFechaRegistro(LocalDate.now());
                        service.agregarTrabajador(t2);

                        lista = service.listar();

                    } catch (ExcepcionDeNegocio ex) {
                        response.setContentType("text/plain;charset=UTF-8");
                        response.getWriter().println("Error de negocio: " + ex.getMessage());
                        return;
                    }
                }
                response.setContentType("Text/html;charset=UTF-8");
                try(PrintWriter out = response.getWriter()){
                    out.println("<html><body>");
                    out.println("<h1>Listado de Trabajadores de la Salud</h1>");
                    out.println("<ul>");
                    for(TrabajadorDeLaSalud t: lista) {
                        out.println("<li>" + t.getNombre() + " - " + t.getMatricula() + " - " + t.getFechaRegistro() + "</li>");
                    }
                    out.println("</ul>");
                    out.println("</body></html>");
            }
        }
}
