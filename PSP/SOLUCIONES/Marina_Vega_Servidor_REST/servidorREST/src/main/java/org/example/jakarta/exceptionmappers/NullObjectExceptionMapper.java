package org.example.jakarta.exceptionmappers;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.example.domain.model.ApiError;
import org.example.domain.model.exceptions.NullObjectException;

@Provider
public class NullObjectExceptionMapper implements ExceptionMapper<NullObjectException> {
    @Override
    public Response toResponse(NullObjectException e) {
        ApiError apiError = new ApiError(e.getMessage());
        return Response.status(Response.Status.BAD_REQUEST).entity(apiError)
                .type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}