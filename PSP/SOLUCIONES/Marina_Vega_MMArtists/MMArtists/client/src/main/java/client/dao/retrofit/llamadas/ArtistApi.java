package client.dao.retrofit.llamadas;

import client.common.Constants;
import domain.common.ApiConstants;
import domain.model.Artist;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;
import retrofit2.http.*;

import java.util.List;

public interface ArtistApi {

    @GET(Constants.ARTISTS)
    Single<List<Artist>> getAllArtists();

    @POST(Constants.ARTISTS)
    Single<Artist> addArtist(@Body Artist artist);

    @PUT(Constants.ARTISTS)
    Single<Artist> updateArtist(@Body Artist artist);

    @DELETE(Constants.ARTISTS + ApiConstants.SLASH_ID)
    Single<Response<Void>> deleteArtist(@Path(ApiConstants.ID) String artistId);
}