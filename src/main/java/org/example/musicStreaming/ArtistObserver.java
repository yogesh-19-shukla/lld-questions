package org.example.musicStreaming;

public interface ArtistObserver {
    void update(Artist artist, Album newAlbum);
}