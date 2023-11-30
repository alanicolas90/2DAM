package org.example.jakarta.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.log4j.Log4j2;
import org.example.domain.common.DomainConstants;
import org.example.domain.model.ApiError;
import org.example.domain.model.Song;
import org.example.domain.usecases.songs.*;
import org.example.jakarta.common.JakartaConstants;

import java.util.List;

@Path(JakartaConstants.SLASH_SONGS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Log4j2
public class RestSongs {

    private final GetAllSongsUseCase getAllSongsUseCase;
    private final GetSongUseCase getSongUseCase;
    private final AddSongUseCase addSongUseCase;
    private final UpdateSongUseCase updateSongUseCase;
    private final DeleteSongUseCase deleteSongUseCase;

    @Inject
    public RestSongs(GetAllSongsUseCase getAllSongsUseCase, GetSongUseCase getSongUseCase, AddSongUseCase addSongUseCase, UpdateSongUseCase updateSongUseCase, DeleteSongUseCase deleteSongUseCase) {
        this.getAllSongsUseCase = getAllSongsUseCase;
        this.getSongUseCase = getSongUseCase;
        this.addSongUseCase = addSongUseCase;
        this.updateSongUseCase = updateSongUseCase;
        this.deleteSongUseCase = deleteSongUseCase;
    }

    @GET
    public List<Song> getAllSongs() {
        return getAllSongsUseCase.getAllSongs();
    }

    @GET
    @Path(JakartaConstants.SLASH_ID)
    public Response getSong(@PathParam(JakartaConstants.ID) String id) {
        try {
            int numericId = Integer.parseInt(id);
            return Response.status(Response.Status.OK)
                    .entity(getSongUseCase.getSong(numericId)).build();
        } catch (NumberFormatException e) {
            log.error(e.getMessage());
            ApiError apiError = new ApiError(DomainConstants.THE_ID_IS_NOT_A_NUMBER);
            return Response.status(Response.Status.BAD_REQUEST).entity(apiError)
                    .type(MediaType.APPLICATION_JSON_TYPE).build();
        }
    }

    @POST
    public Response addSong(Song song) {
        if (addSongUseCase.addSong(song)) {
            return Response.status(Response.Status.CREATED)
                    .entity(song).build();
        } else {
            return Response.status(Response.Status.CONFLICT)
                    .entity(new ApiError(JakartaConstants.THE_SONG_COULD_NOT_BE_ADDED)).build();
        }
    }

    @PUT
    public Song updateSong(Song song) {
        return updateSongUseCase.updateSong(song);
    }

    @DELETE
    @Path(JakartaConstants.SLASH_ID)
    public Response deleteSong(@PathParam(JakartaConstants.ID) String id) {
        Response response;
        try {
            int numericId = Integer.parseInt(id);
            if (deleteSongUseCase.deleteSong(numericId)) {
                response = Response.status(Response.Status.NO_CONTENT)
                        .build();
            } else {
                response = Response.status(Response.Status.CONFLICT)
                        .entity(new ApiError(JakartaConstants.THE_SONG_COULD_NOT_BE_DELETED)).build();
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