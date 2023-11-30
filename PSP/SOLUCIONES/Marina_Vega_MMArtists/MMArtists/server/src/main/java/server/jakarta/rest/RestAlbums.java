package server.jakarta.rest;

import domain.common.ApiConstants;
import domain.common.ApiError;
import domain.model.Album;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import server.domain.usecases.albums.DeleteAlbumUseCase;
import server.domain.usecases.albums.GetAlbumUseCase;
import server.domain.usecases.albums.GetAlbumsByArtistUseCase;
import server.domain.usecases.albums.GetAllAlbumsUseCase;
import server.jakarta.filters.Secure;

import java.util.List;

@Path(ApiConstants.SLASH_ALBUMS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Secure
public class RestAlbums {
    private final GetAllAlbumsUseCase getAllAlbumsUseCase;
    private final GetAlbumUseCase getAlbumUseCase;
    private final GetAlbumsByArtistUseCase getAlbumsByArtistUseCase;
    private final DeleteAlbumUseCase deleteAlbumUseCase;

    @Inject
    public RestAlbums(GetAllAlbumsUseCase getAllAlbumsUseCase, GetAlbumUseCase getAlbumUseCase, GetAlbumsByArtistUseCase getAlbumsByArtistUseCase, DeleteAlbumUseCase deleteAlbumUseCase) {
        this.getAllAlbumsUseCase = getAllAlbumsUseCase;
        this.getAlbumUseCase = getAlbumUseCase;
        this.getAlbumsByArtistUseCase = getAlbumsByArtistUseCase;
        this.deleteAlbumUseCase = deleteAlbumUseCase;
    }

    @GET
    public List<Album> getAllAlbums() {
        return getAllAlbumsUseCase.getAllAlbums();
    }

    @GET
    @Path(ApiConstants.SLASH_ARTISTS + ApiConstants.SLASH_ID)
    public List<Album> getAlbumsByArtist(@PathParam(ApiConstants.ID) String id) {
        return getAlbumsByArtistUseCase.getAlbumByArtist(id);
    }

    @GET
    @Path(ApiConstants.SLASH_ID)
    public Album getAlbum(@PathParam(ApiConstants.ID) String id) {
        return getAlbumUseCase.getAlbum(id);
    }

    @DELETE
    @Path(ApiConstants.SLASH_ID)
    public Response deleteAlbum(@PathParam(ApiConstants.ID) String id) {
        Response response;
        if (deleteAlbumUseCase.deleteAlbum(id)) {
            response = Response.status(Response.Status.NO_CONTENT)
                    .build();
        } else {
            response = Response.status(Response.Status.CONFLICT)
                    .entity(new ApiError(ApiConstants.THE_ALBUM_COULD_NOT_BE_DELETED)).build();
        }
        return response;
    }
}