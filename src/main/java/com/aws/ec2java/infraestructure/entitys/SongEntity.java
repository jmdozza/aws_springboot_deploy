package com.aws.ec2java.infraestructure.entitys;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@Table(name = "songs")
@AllArgsConstructor
@NoArgsConstructor
public class SongEntity {
    @Id
    @GeneratedValue(
        strategy = GenerationType.UUID
    )
    @Column(name="id",updatable = false,nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String title;
    
    @Column(name="duration" ,nullable = true)
    private int duration;

    @Column(name = "audio_key",nullable = true)
    private String audioKey;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id",nullable = false)
    private AlbumEntity album;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name="songs_genres",
        joinColumns = @JoinColumn(name="song_id"),
        inverseJoinColumns = @JoinColumn(name="genre_id")
    )
    private List<GenreEntity> genres;
}