package com.aws.ec2java.infraestructure.entitys;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "genres",uniqueConstraints = {
    @UniqueConstraint(name = "uk_genre_name", columnNames = "name")
})
public class GenreEntity {
    @Id
    @GeneratedValue(
        strategy = GenerationType.UUID
    )
    private UUID id;
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "genreAlb",cascade = CascadeType.ALL)
    private List<AlbumEntity> albums;

    @ManyToMany(mappedBy = "genres")
    private List<SongEntity> songs;
}
