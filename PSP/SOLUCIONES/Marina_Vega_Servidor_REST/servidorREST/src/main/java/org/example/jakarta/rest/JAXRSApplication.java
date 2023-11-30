package org.example.jakarta.rest;


import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.example.jakarta.common.JakartaConstants;

@ApplicationPath(JakartaConstants.SLASH_API)
public class JAXRSApplication extends Application {
}