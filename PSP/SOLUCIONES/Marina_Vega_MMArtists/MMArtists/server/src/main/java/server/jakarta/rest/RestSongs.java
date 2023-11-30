package server.jakarta.rest;

import domain.common.ApiConstants;
import domain.common.ApiError;
import domain.model.Song;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import server.domain.usecases.songs.DeleteSongsUseCase;
import server.domain.usecases.songs.GetAllSongsUseCase;
import server.domain.usecases.songs.GetSongsByAlbumUseCase;
import server.jakarta.filters.Secure;

import java.util.List;

@Path(ApiConstants.SLASH_SONGS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Secure
public class RestSongs {
    private final GetAllSongsUseCase getAllSongsUseCase;
    private final GetSongsByAlbumUseCase getSongsByAlbumUseCase;
    private final DeleteSongsUseCase deleteSongsUseCase;

    @Inject
    public RestSongs(GetAllSongsUseCase getAllSongsUseCase, GetSongsByAlbumUseCase getSongsByAlbumUseCase, DeleteSongsUseCase deleteSongsUseCase) {
        this.getAllSongsUseCase = getAllSongsUseCase;
        this.getSongsByAlbumUseCase = getSongsByAlbumUseCase;
        this.deleteSongsUseCase = deleteSongsUseCase;
    }

    @GET
    public List<Song> getAllSongs() {
        return getAllSongsUseCase.getAllSongs();
    }

    @GET
    @Path(ApiConstants.SLASH_ALBUMS + ApiConstants.SLASH_ID)
    public List<Song> getSongsByAlbum(@PathParam(ApiConstants.ID) String id) {
        return getSongsByAlbumUseCase.getSongsByAlbum(id);
    }

    @PUT
    public Response deleteSongs(List<Song> songList) {
        Response response;
        if (deleteSongsUseCase.deleteSongs(songList)) {
            response = Response.status(Response.Status.NO_CONTENT)
                    .build();
        } else {
            response = Response.status(Response.Status.CONFLICT)
                    .entity(new ApiError(ApiConstants.THE_ARTIST_COULD_NOT_BE_DELETED)).build();
        }
        return response;
    }
}