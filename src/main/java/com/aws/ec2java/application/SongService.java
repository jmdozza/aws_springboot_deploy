package com.aws.ec2java.application;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.aws.ec2java.application.command.UploadSongCommand;
import com.aws.ec2java.domain.models.Album;
import com.aws.ec2java.domain.models.Genre;
import com.aws.ec2java.domain.models.Song;
import com.aws.ec2java.domain.models.SongFile;
import com.aws.ec2java.domain.ports.album.AlbumRepositoryPort;
import com.aws.ec2java.domain.ports.genre.GenreRepositoryPort;
import com.aws.ec2java.domain.ports.out.FileStoragePort;
import com.aws.ec2java.domain.ports.song.SongRepositoryPort;
import com.aws.ec2java.domain.ports.song.SongUseCases;

import jakarta.persistence.EntityNotFoundException;

public class SongService implements SongUseCases{
    
    private final SongRepositoryPort songRepositoryPort;
    private final AlbumRepositoryPort albumRepositoryPort;
    private final GenreRepositoryPort genreRepositoryPort;
    private final FileStoragePort fileStoragePort;

    public SongService(SongRepositoryPort songRepositoryPort,
                        AlbumRepositoryPort albumRepositoryPort,
                        GenreRepositoryPort genreRepositoryPort,
                        FileStoragePort fileStoragePort
    ){
        this.songRepositoryPort=songRepositoryPort;
        this.albumRepositoryPort=albumRepositoryPort;
        this.genreRepositoryPort=genreRepositoryPort;
        this.fileStoragePort=fileStoragePort;
    }

    @Override
    public Song addSong(UploadSongCommand newSong) {
        // Verificamos que el album de la request existe
        Album songAlbum=albumRepositoryPort.getAlbumById
                                            (newSong.album_id())
                                            .orElseThrow(
                                                        ()-> new EntityNotFoundException("ALBUM NOT FOUND"));
        //Traemos lo generos
        List<Genre> songGenres= genreRepositoryPort.getGeneresByIds(newSong.genre_ids());
        //Verificamos que hayan generos validos
        if(songGenres.isEmpty()){
            throw new EntityNotFoundException("GENRES NOT FOUND");
        }

        //Le asignamos un nombre especifico para el archivo que se sube al bucket
        String s3ObjectKey=generateSongS3Key(songAlbum.id(), newSong.originalFileName());
        uploadSongToBucket(newSong, s3ObjectKey);
        
        try{
        Song createdSong = Song.builder()
                            .album(songAlbum)
                            .genres(songGenres)
                            .title(newSong.title())
                            .duration(null)
                            .audio_url(s3ObjectKey)
                            .build();
        
                            
        return songRepositoryPort.addSong(createdSong);
        } catch(Exception e){
            //Borramos el object que se alcanzo a subir al bucket
            fileStoragePort.deleteFile(s3ObjectKey);
            throw new RuntimeException("Error guarando en BD. Archivo eliminado de S3");
        }
    }


    public void uploadSongToBucket(UploadSongCommand newSong,String objectKey){
        SongFile songFile=SongFile.builder()
                            .audioFileName(objectKey)
                            .audioFileBytes(newSong.audioFileBytes())
                            .contentType(newSong.contentType())
                            .build();
        fileStoragePort.uploadFile(songFile);
    }

    //TODO
    @Override
    public void deleteSong(Song newSong) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteSong'");
    }

    @Override
    public List<Song> getSongs() {
        return songRepositoryPort.getAllSongs();
    }


    public String generateSongS3Key(UUID albumId,String fileName){
        UUID newSongId= UUID.randomUUID();
        String extension= extractFileExtension(fileName);
        String s3ObjectKey="albums/"+albumId.toString()+"/songs/"+newSongId.toString()+extension;
        return s3ObjectKey;
    }

    public String extractFileExtension(String fileName){
        String extension="";
        if(fileName.contains(".")){
            extension=fileName.substring(fileName.lastIndexOf("."));
        }
        else{
            extension=".mp3";
        }
        return extension;
    }

    @Override
    public Optional<Song> getSongById(UUID id) {
        return songRepositoryPort.getSongById(id);
    }

    @Override
    public List<Song> getSongsByArtistName(String artistName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSongsByArtistName'");
    }

    @Override
    public SongFile getSongFile(String objectKey) {
        return fileStoragePort.downloadFile(objectKey);
    }

}
