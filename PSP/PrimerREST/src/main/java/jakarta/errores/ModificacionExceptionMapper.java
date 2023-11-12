package jakarta.errores;


import domain.modelo.errores.ModificacionException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.LocalDateTime;


@Provider
public class ModificacionExceptionMapper implements ExceptionMapper<ModificacionException> {

  public Response toResponse(ModificacionException exception) {
    ApiError apiError = new ApiError(exception.getMessage(), LocalDateTime.now());
    return Response.status(Response.Status.valueOf(exception.getMessage())).entity(apiError)
        .type(MediaType.APPLICATION_JSON_TYPE).build();
  }

}
