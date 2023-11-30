package client.domain.usecases.songs;

import client.dao.DaoSongs;
import domain.common.ApiError;
import domain.model.Song;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class DeleteSongsUseCase {
    private final DaoSongs daoSongs;

    @Inject
    public DeleteSongsUseCase(DaoSongs daoSongs) {
        this.daoSongs = daoSongs;
    }

    public Single<Either<ApiError, String>> deleteSongs(List<Song> songList) {
        return daoSongs.deleteSongs(songList);
    }
}