package servlets;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ServletPrueba", value = "/servletPrueba")
public class ServletPrueba extends HttpServlet {

    public static final String CONTADOR = "contador";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer contador;
        if (req.getSession().getAttribute(CONTADOR) == null) {
            req.getSession().setAttribute(CONTADOR, 0);
        }
        contador = (Integer) req.getSession().getAttribute(CONTADOR);

        resp.getWriter().println(contador);
        contador++;
        req.getSession().setAttribute(CONTADOR, contador);
    }
}
