package domain.modelo.errores;



public class ModificacionException extends RuntimeException{

    public ModificacionException(String error) {
        super(error);
    }
}
