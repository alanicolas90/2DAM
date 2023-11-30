package server.jakarta.rest;


import domain.common.ApiConstants;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath(ApiConstants.SLASH_API)
public class JAXRSApplication extends Application {
}