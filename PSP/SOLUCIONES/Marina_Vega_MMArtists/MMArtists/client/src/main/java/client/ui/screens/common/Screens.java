package client.ui.screens.common;

import lombok.Getter;

@Getter
public enum Screens {

    ALBUM_DETAIL(UIConstants.FXML_ALBUM_DETAIL_FXML),
    ALBUMS_MAIN(UIConstants.FXML_ALBUMS_MAIN_FXML),
    ARTISTS_MAIN(UIConstants.FXML_ARTISTS_MAIN_FXML),
    LOGIN(UIConstants.FXML_LOGIN_FXML),
    SONGS_MAIN(UIConstants.FXML_SONGS_MAIN_FXML);

    private final String path;

    Screens(String path) {
        this.path = path;
    }
}