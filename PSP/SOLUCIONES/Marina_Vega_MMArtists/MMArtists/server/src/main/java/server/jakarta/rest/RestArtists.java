package server.jakarta.rest;

import domain.common.ApiConstants;
import domain.common.ApiError;
import domain.model.Artist;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import server.domain.usecases.artists.AddArtistUseCase;
import server.domain.usecases.artists.DeleteArtistUseCase;
import server.domain.usecases.artists.GetAllArtistsUseCase;
import server.domain.usecases.artists.UpdateArtistUseCase;
import server.jakarta.filters.Secure;

import java.util.List;

@Path(ApiConstants.SLASH_ARTISTS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Secure
public class RestArtists {
    private final GetAllArtistsUseCase getAllArtistsUseCase;
    private final AddArtistUseCase addArtistUseCase;
    private final UpdateArtistUseCase updateArtistUseCase;
    private final DeleteArtistUseCase deleteArtistUseCase;

    @Inject
    public RestArtists(GetAllArtistsUseCase getAllArtistsUseCase, AddArtistUseCase addArtistUseCase, UpdateArtistUseCase updateArtistUseCase, DeleteArtistUseCase deleteArtistUseCase) {
        this.getAllArtistsUseCase = getAllArtistsUseCase;
        this.addArtistUseCase = addArtistUseCase;
        this.updateArtistUseCase = updateArtistUseCase;
        this.deleteArtistUseCase = deleteArtistUseCase;
    }

    @GET
    public List<Artist> getAllArtists() {
        return getAllArtistsUseCase.getAllArtists();
    }

    @POST
    public Response addArtist(Artist artist) {
        if (addArtistUseCase.addArtist(artist)) {
            return Response.status(Response.Status.CREATED)
                    .entity(artist).build();
        } else {
            return Response.status(Response.Status.CONFLICT)
                    .entity(new ApiError(ApiConstants.THE_ARTIST_COULD_NOT_BE_ADDED)).build();
        }
    }

    @PUT
    public Artist updateArtist(Artist artist) {
        return updateArtistUseCase.updateArtist(artist);
    }

    @DELETE
    @Path(ApiConstants.SLASH_ID)
    public Response deleteArtist(@PathParam(ApiConstants.ID) String id) {
        Response response;
        if (deleteArtistUseCase.deleteArtist(id)) {
            response = Response.status(Response.Status.NO_CONTENT)
                    .build();
        } else {
            response = Response.status(Response.Status.CONFLICT)
                    .entity(new ApiError(ApiConstants.THE_ARTIST_COULD_NOT_BE_DELETED)).build();
        }
        return response;
    }
}