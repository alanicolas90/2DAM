package client.dao;

import domain.common.ApiError;
import domain.model.Song;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;

import java.util.List;

public interface DaoSongs {
    Single<Either<ApiError, List<Song>>> getAllSongs();

    Single<Either<ApiError, List<Song>>> getSongsByAlbum(String albumId);

    Single<Either<ApiError, String>> deleteSongs(List<Song> songList);
}