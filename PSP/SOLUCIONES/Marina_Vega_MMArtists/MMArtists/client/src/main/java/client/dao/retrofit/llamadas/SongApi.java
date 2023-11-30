package client.dao.retrofit.llamadas;

import client.common.Constants;
import domain.common.ApiConstants;
import domain.model.Song;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import java.util.List;

public interface SongApi {

    @GET(Constants.SONGS)
    Single<List<Song>> getAllSongs();

    @GET(Constants.SONGS + ApiConstants.SLASH_ALBUMS + ApiConstants.SLASH_ID)
    Single<List<Song>> getSongsByAlbum(@Path(ApiConstants.ID) String albumId);

    @PUT(Constants.SONGS)
    Single<Response<Void>> deleteSongs(@Body List<Song> songList);
}