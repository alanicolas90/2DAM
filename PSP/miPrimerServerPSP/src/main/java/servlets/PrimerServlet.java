package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "Servlet", value = "/servlet")
public class PrimerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.getWriter().println("""
                <html>
                    <head>
                        <title>Primer Servlet</title>
                    </head>
                    <body>
                        <h1>Primer Servlet</h1>
                        <p>Este es mi primer servlet</p>
                    </body>
                </html>
                        """);

    }
}
