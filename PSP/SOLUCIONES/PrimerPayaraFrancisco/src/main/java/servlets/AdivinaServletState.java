package servlets;

import lombok.Data;

import java.util.Set;

@Data
public class AdivinaServletState {
    private Integer intentos;
    private Integer numeroSecreto;
    private boolean juegoFinalizado;
    private Set<Integer> historial;
    private String mensaje;
}
