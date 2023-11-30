package org.example.jakarta.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.log4j.Log4j2;
import org.example.domain.common.DomainConstants;
import org.example.domain.model.ApiError;
import org.example.domain.model.Artist;
import org.example.domain.usecases.artists.*;
import org.example.jakarta.common.JakartaConstants;

import java.util.List;

@Path(JakartaConstants.SLASH_ARTISTS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Log4j2
public class RestArtists {

    private final GetAllArtistsUseCase getAllArtistsUseCase;
    private final GetArtistUseCase getArtistUseCase;
    private final AddArtistUseCase addArtistUseCase;
    private final UpdateArtistUseCase updateArtistUseCase;
    private final DeleteArtistUseCase deleteArtistUseCase;

    @Inject
    public RestArtists(GetAllArtistsUseCase getAllArtistsUseCase, GetArtistUseCase getArtistUseCase, AddArtistUseCase addArtistUseCase, UpdateArtistUseCase updateArtistUseCase, DeleteArtistUseCase deleteArtistUseCase) {
        this.getAllArtistsUseCase = getAllArtistsUseCase;
        this.getArtistUseCase = getArtistUseCase;
        this.addArtistUseCase = addArtistUseCase;
        this.updateArtistUseCase = updateArtistUseCase;
        this.deleteArtistUseCase = deleteArtistUseCase;
    }

    @GET
    public List<Artist> getAllArtists() {
        return getAllArtistsUseCase.getAllArtists();
    }

    @GET
    @Path(JakartaConstants.SLASH_ID)
    public Response getArtist(@PathParam(JakartaConstants.ID) String id) {
        try {
            int numericId = Integer.parseInt(id);
            return Response.status(Response.Status.OK)
                    .entity(getArtistUseCase.getArtist(numericId)).build();
        } catch (NumberFormatException e) {
            log.error(e.getMessage());
            ApiError apiError = new ApiError(DomainConstants.THE_ID_IS_NOT_A_NUMBER);
            return Response.status(Response.Status.BAD_REQUEST).entity(apiError)
                    .type(MediaType.APPLICATION_JSON_TYPE).build();
        }
    }

    @POST
    public Response addArtist(Artist artist) {
        if (addArtistUseCase.addArtist(artist)) {
            return Response.status(Response.Status.CREATED)
                    .entity(artist).build();
        } else {
            return Response.status(Response.Status.CONFLICT)
                    .entity(new ApiError(JakartaConstants.THE_ARTIST_COULD_NOT_BE_ADDED)).build();
        }
    }

    @PUT
    public Artist updateArtist(Artist artist) {
        return updateArtistUseCase.updateArtist(artist);
    }

    @DELETE
    @Path(JakartaConstants.SLASH_ID)
    public Response deleteArtist(@PathParam(JakartaConstants.ID) String id) {
        Response response;
        try {
            int numericId = Integer.parseInt(id);
            if (deleteArtistUseCase.deleteArtist(numericId)) {
                response = Response.status(Response.Status.NO_CONTENT)
                        .build();
            } else {
                response = Response.status(Response.Status.CONFLICT)
                        .entity(new ApiError(JakartaConstants.THE_ARTIST_COULD_NOT_BE_DELETED)).build();
            }
        } catch (NumberFormatException e) {
            log.error(e.getMessage());
            ApiError apiError = new ApiError(DomainConstants.THE_ID_IS_NOT_A_NUMBER);
            response = Response.status(Response.Status.BAD_REQUEST).entity(apiError)
                    .type(MediaType.APPLICATION_JSON_TYPE).build();
        }
        return response;
    }
}