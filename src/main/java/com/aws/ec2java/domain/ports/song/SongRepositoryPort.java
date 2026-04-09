package com.aws.ec2java.domain.ports.song;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.aws.ec2java.domain.models.Song;

public interface SongRepositoryPort {
    Song addSong(Song newSong);
    List<Song> getAllSongs();
    Optional<Song> getSongById(UUID songId);
}
