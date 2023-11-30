package client.domain.usecases.songs;

import client.dao.DaoSongs;
import domain.common.ApiError;
import domain.model.Song;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class GetSongsByAlbumUseCase {
    private final DaoSongs daoSongs;

    @Inject
    public GetSongsByAlbumUseCase(DaoSongs daoSongs) {
        this.daoSongs = daoSongs;
    }

    public Single<Either<ApiError, List<Song>>> getSongsByAlbum(String albumId) {
        return daoSongs.getSongsByAlbum(albumId);
    }
}