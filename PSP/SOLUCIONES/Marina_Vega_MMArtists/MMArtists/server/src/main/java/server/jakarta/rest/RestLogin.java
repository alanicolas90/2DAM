package server.jakarta.rest;

import domain.common.ApiConstants;
import domain.common.ApiError;
import domain.model.User;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import server.domain.usecases.login.LoginUseCase;
import server.domain.usecases.login.RegisterUseCase;
import server.jakarta.common.JakartaConstants;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path(ApiConstants.SLASH_LOGIN)
public class RestLogin {
    @Context
    private HttpServletRequest request;
    private final RegisterUseCase registerUseCase;
    private final LoginUseCase loginUseCase;

    @Inject
    public RestLogin(RegisterUseCase registerUseCase, LoginUseCase loginUseCase) {
        this.registerUseCase = registerUseCase;
        this.loginUseCase = loginUseCase;
    }

    @GET
    public Response doLogin(@QueryParam(ApiConstants.USERNAME) String username, @QueryParam(ApiConstants.PASSWORD) String password) {
        if (loginUseCase.doLogin(new User(username, password))) {
            request.getSession().setAttribute(JakartaConstants.LOGIN, true);
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ApiError(JakartaConstants.THE_USERNAME_OR_PASSWORD_IS_NOT_CORRECT)).
                    build();
        }
    }

    @POST
    public Response register(User user) {
        if (registerUseCase.doRegister(user)) {
            return Response.status(Response.Status.CREATED)
                    .entity(user).build();
        } else {
            return Response.status(Response.Status.CONFLICT)
                    .entity(new ApiError(ApiConstants.THE_REGISTRATION_COULD_NOT_BE_COMPLETED)).build();
        }
    }
}