package org.example.jakarta.exceptionmappers;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.example.domain.model.ApiError;
import org.example.domain.model.exceptions.NotValidFieldException;

@Provider
public class NotValidFieldExceptionMapper implements ExceptionMapper<NotValidFieldException> {
    @Override
    public Response toResponse(NotValidFieldException e) {
        ApiError apiError = new ApiError(e.getMessage());
        return Response.status(Response.Status.BAD_REQUEST).entity(apiError)
                .type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}