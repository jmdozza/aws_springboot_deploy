package com.aws.ec2java.domain.ports.song;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.aws.ec2java.application.command.UploadSongCommand;
import com.aws.ec2java.domain.models.Song;
import com.aws.ec2java.domain.models.SongFile;

public interface SongUseCases {
    Song addSong (UploadSongCommand newSong);
    void deleteSong (Song newSong);
    List<Song> getSongs();
    Optional<Song> getSongById(UUID id);
    List<Song> getSongsByArtistName(String artistName);
    SongFile getSongFile(String objectKey);

}
