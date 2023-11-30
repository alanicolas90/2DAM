package servlets;

public class ConstantesServlet {
    public static final String SESION_INTENTOS = "intentos";
    public static final String SESION_NUMERO_SECRETO = "numero";
    public static final String GUESS = "guess";
    public static final String SESION_HISTORIAL = "historial";
    public static final String MENSAJE_NUMERO_ADIVINADO = "Has adivinado. El numero era ";
    public static final String MENSAJE_INICIAL = "";
    public static final String MENSAJE_NUMERO_INCORRECTO = "Numero incorrecto, intentalo de nuevo. ";
    public static final String MENSAJE_NUMERO_REPETIDO = "Ya has ingresado este numero antes. Intenta con uno nuevo";
    public static final String NUMERO_INTRODUCIDO_NO_VALIDO = "Numero no valido, asegurate de no introducir letras o numeros con decimales.";
    public static final String NUMERO_INTRODUCIDO_NO_EXISTE = "No has puesto numero";
    public static final String MENSAJE = "mensaje";
    public static final String ADIVINA_TEMPLATE = "adivina";
    public static final String MENSAJE_INTENTOS_AGOTADOS = "Se han agotado los intentos. El numero secreto era ";
    public static final String RESET = "reset";
    public static final String ADIVINA = "adivina";
    public static final String RUTA_RAIZ = "/";
    public static final int INTENTOS_INICIALES = 10;
    public static final int INTENTOS_AGOTADOS = 0;
    public static final int RANGO_SUPERIOR_DEL_NUMERO_ALEATORIO = 20;
    public static final String VARIABLE_RANGO_SUPERIOR = "rangoSuperior";
    public static final String MENSAJE_NUMERO_MAYOR = "El numero secreto es mayor";
    public static final String MENSAJE_NUMERO_MENOR = "El numero secreto es menor";

    private ConstantesServlet() {
    }
}
