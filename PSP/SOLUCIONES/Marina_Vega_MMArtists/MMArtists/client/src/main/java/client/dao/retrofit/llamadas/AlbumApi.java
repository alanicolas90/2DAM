package client.dao.retrofit.llamadas;

import client.common.Constants;
import domain.common.ApiConstants;
import domain.model.Album;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface AlbumApi {

    @GET(Constants.ALBUMS)
    Single<List<Album>> getAllAlbums();

    @GET(Constants.ALBUMS + ApiConstants.SLASH_ARTISTS + ApiConstants.SLASH_ID)
    Single<List<Album>> getAlbumsByArtist(@Path(ApiConstants.ID) String artistId);

    @DELETE(Constants.ALBUMS + ApiConstants.SLASH_ID)
    Single<Response<Void>> deleteAlbum(@Path(ApiConstants.ID) String albumId);
}