package server.jakarta.filters;

import domain.common.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import server.jakarta.common.JakartaConstants;

@Provider
@Secure
public class FilterLogin implements ContainerRequestFilter {
    @Context
    private HttpServletRequest request;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) {
        if (request.getSession().getAttribute(JakartaConstants.LOGIN) == null) {
            containerRequestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                    .entity(new ApiError(JakartaConstants.YOU_NEED_TO_LOG_IN_TO_ACCESS_HERE))
                    .type(MediaType.APPLICATION_JSON_TYPE).build());
        }
    }

}