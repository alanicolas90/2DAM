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
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@WebServlet(name = ConstantesServlet.ADIVINA, value = ConstantesServlet.RUTA_RAIZ)
public class AdivinaServlet extends HttpServlet {


    private final Random random = new Random();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdivinaServletState state = new AdivinaServletState();

        setUpGame(req, state);

        String userInput = req.getParameter(ConstantesServlet.GUESS);
        String resetGame = req.getParameter(ConstantesServlet.RESET);

        if (userInput != null)
            adivinar( state, userInput);
        else
            state.setMensaje(ConstantesServlet.NUMERO_INTRODUCIDO_NO_EXISTE);

        finTurno(req, state, resetGame);

        salidaPlantilla(req, resp, state);
    }

    private void setUpGame(HttpServletRequest req, AdivinaServletState state) {
        setupIntentos(req, state);
        setupNumeroSecreto(req, state);
        setupHistorial(req, state);
        state.setMensaje(ConstantesServlet.MENSAJE_INICIAL);
        state.setJuegoFinalizado(false);
    }


    private void setupHistorial(HttpServletRequest req, AdivinaServletState state) {
        state.setHistorial((Set<Integer>) req.getSession().getAttribute(ConstantesServlet.SESION_HISTORIAL));
        if (state.getHistorial() == null) {
            state.setHistorial(new HashSet<>());
        }
    }

    private void setupNumeroSecreto(HttpServletRequest req, AdivinaServletState state) {
        state.setNumeroSecreto((Integer) req.getSession().getAttribute(ConstantesServlet.SESION_NUMERO_SECRETO));
        if (state.getNumeroSecreto() == null) {
            state.setNumeroSecreto(getRandomNum());
            req.getSession().setAttribute(ConstantesServlet.SESION_NUMERO_SECRETO, state.getNumeroSecreto());
        }
    }

    private void setupIntentos(HttpServletRequest req, AdivinaServletState state) {
        state.setIntentos((Integer) req.getSession().getAttribute(ConstantesServlet.SESION_INTENTOS));
        if (state.getIntentos() == null) {
            state.setIntentos(ConstantesServlet.INTENTOS_INICIALES);
        }
    }

    private int getRandomNum() {
        return random.nextInt(ConstantesServlet.RANGO_SUPERIOR_DEL_NUMERO_ALEATORIO);
    }

    private void adivinar(AdivinaServletState state, String userInput) {
        try {
            Integer numeroIntroducido = Integer.parseInt(userInput);

            if (state.getIntentos() >= ConstantesServlet.INTENTOS_AGOTADOS
                    && !state.getHistorial().contains(numeroIntroducido)) {
                state.getHistorial().add(numeroIntroducido);
                state.setIntentos(state.getIntentos() - 1);

                if (state.getIntentos() > ConstantesServlet.INTENTOS_AGOTADOS) {
                    if (numeroIntroducido.equals(state.getNumeroSecreto())) {
                        state.setMensaje(ConstantesServlet.MENSAJE_NUMERO_ADIVINADO + state.getNumeroSecreto());
                        state.setJuegoFinalizado(true);
                    } else if (state.getNumeroSecreto() > numeroIntroducido) {
                        state.setMensaje(ConstantesServlet.MENSAJE_NUMERO_INCORRECTO + ConstantesServlet.MENSAJE_NUMERO_MAYOR);
                    } else {
                        state.setMensaje(ConstantesServlet.MENSAJE_NUMERO_INCORRECTO + ConstantesServlet.MENSAJE_NUMERO_MENOR);
                    }
                } else {
                    state.setMensaje(ConstantesServlet.MENSAJE_INTENTOS_AGOTADOS + state.getNumeroSecreto());
                    state.setJuegoFinalizado(true);
                }

            } else {
                state.setMensaje(ConstantesServlet.MENSAJE_NUMERO_REPETIDO);
            }
        } catch (NumberFormatException e) {
            state.setMensaje(ConstantesServlet.NUMERO_INTRODUCIDO_NO_VALIDO);
        }
    }

    private void finTurno(HttpServletRequest req, AdivinaServletState state, String resetGame) {

        if (resetGame != null || state.isJuegoFinalizado()) {
            state.setIntentos(ConstantesServlet.INTENTOS_INICIALES);
            state.setNumeroSecreto(getRandomNum());
            state.setHistorial(new HashSet<>());
        }
        req.getSession().setAttribute(ConstantesServlet.SESION_INTENTOS, state.getIntentos());
        req.getSession().setAttribute(ConstantesServlet.SESION_NUMERO_SECRETO, state.getNumeroSecreto());
        req.getSession().setAttribute(ConstantesServlet.SESION_HISTORIAL, state.getHistorial());
    }

    private void salidaPlantilla(HttpServletRequest req, HttpServletResponse resp, AdivinaServletState state) throws IOException {
        TemplateEngine templateEngine = (TemplateEngine) getServletContext().getAttribute(ThymeLeafListener.TEMPLATE_ENGINE_ATTR);
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);
        context.setVariable(ConstantesServlet.VARIABLE_RANGO_SUPERIOR, ConstantesServlet.RANGO_SUPERIOR_DEL_NUMERO_ALEATORIO);
        context.setVariable(ConstantesServlet.SESION_INTENTOS, state.getIntentos());
        context.setVariable(ConstantesServlet.MENSAJE, state.getMensaje());
        templateEngine.process(ConstantesServlet.ADIVINA_TEMPLATE, context, resp.getWriter());
    }
}
