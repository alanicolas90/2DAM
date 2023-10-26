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

@WebServlet(name = "Juan", value = "/a")
public class PlantillaBackground extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        TemplateEngine templateEngine = (TemplateEngine) getServletContext().getAttribute(
                ThymeLeafListener.TEMPLATE_ENGINE_ATTR);

        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);


        String template;

        String sNumero = req.getParameter("numero");




        try {
            int valor = Integer.parseInt(sNumero);
            if(valor == 4) {
                context.setVariable("mensaje", "FELICIDADES HAS ADIVINADO EL NUMERO");
                context.setVariable("backgroundColor", "#FFFF00");
                template = "param";
            }else{
                context.setVariable("error", "no has adivinado el numero");
                context.setVariable("backgroundColor", "#b01e3a");
                template = "error";
            }

        } catch (NumberFormatException e) {
            if (sNumero.isEmpty() || sNumero.isBlank()) {
                context.setVariable("error", "numero vacio, porfavor escriba un numero");
            } else if (sNumero.matches(".*\\D.*")) {
                context.setVariable("error", "Escribe un numero");
            } else {
                context.setVariable("error", "numero no valido");
            }
            context.setVariable("backgroundColor", "#2236e1");
            template = "error";
        }


        templateEngine.process(template, context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }


}
