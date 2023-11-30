package client.dao;

import domain.common.ApiError;
import domain.model.Album;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;

import java.util.List;

public interface DaoAlbums {
    Single<Either<ApiError, List<Album>>> getAllAlbums();

    Single<Either<ApiError, List<Album>>> getAlbumsByArtist(String artistId);

    //Delete entire album with the songs only it contains (and the reference of the album in the songs that remain)
    Single<Either<ApiError, String>> deleteAlbum(String albumId);
}