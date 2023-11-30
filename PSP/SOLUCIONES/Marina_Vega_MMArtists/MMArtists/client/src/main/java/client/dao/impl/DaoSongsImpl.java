package client.dao.impl;

import client.dao.DaoSongs;
import client.dao.retrofit.llamadas.SongApi;
import com.google.gson.Gson;
import domain.common.ApiError;
import domain.model.Song;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class DaoSongsImpl extends DaoGenerics implements DaoSongs {
    private final SongApi songApi;

    @Inject
    public DaoSongsImpl(SongApi songApi, Gson gson) {
        super(gson);
        this.songApi = songApi;
    }

    @Override
    public Single<Either<ApiError, List<Song>>> getAllSongs() {
        return safeSingleApiCall(songApi.getAllSongs());
    }

    @Override
    public Single<Either<ApiError, List<Song>>> getSongsByAlbum(String albumId) {
        return safeSingleApiCall(songApi.getSongsByAlbum(albumId));
    }

    @Override
    public Single<Either<ApiError, String>> deleteSongs(List<Song> songList) {
        return safeSingleVoidApiCall(songApi.deleteSongs(songList));
    }
}