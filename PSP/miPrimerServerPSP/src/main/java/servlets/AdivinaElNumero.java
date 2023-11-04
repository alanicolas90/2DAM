package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import listeners.ThymeLeafListener;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "AdivinaElNumero", value = "/a")
public class AdivinaElNumero extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        TemplateEngine templateEngine = (TemplateEngine) getServletContext().getAttribute("thymeleaf.TemplateEngineInstance");
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);


        String template;
        String sNumero = req.getParameter("numero");

        try {
            int valor = Integer.parseInt(sNumero);
            if (valor == 4) {
                context.setVariable("mensaje", Constantes.FELICIDADES_HAS_ADIVINADO_EL_NUMERO);
                context.setVariable("backgroundColor", Constantes.COLOR_AMARILLO);
                template = "param";
            } else {
                context.setVariable("error", Constantes.NO_HAS_ADIVINADO_EL_NUMERO);
                context.setVariable("backgroundColor", Constantes.COLOR_ROJO);
                template = "error";
            }

        } catch (NumberFormatException e) {
            if (sNumero.isEmpty() || sNumero.isBlank()) {
                context.setVariable("error", Constantes.NUMERO_VACIO_PORFAVOR_ESCRIBA_UN_NUMERO);
            } else if (sNumero.matches(Constantes.ES_UN_STRING)) {
                context.setVariable("error", Constantes.ESCRIBE_UN_NUMERO);
            } else {
                context.setVariable("error", Constantes.NUMERO_NO_VALIDO);
            }
            context.setVariable("backgroundColor", Constantes.COLOR_AZUL);
            template = "error";
        }


        templateEngine.process(template, context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }


}
