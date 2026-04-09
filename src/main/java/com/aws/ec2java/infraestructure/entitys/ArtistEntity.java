package com.aws.ec2java.infraestructure.entitys;

import java.util.List;
import java.util.UUID;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
@Table(name="artists", uniqueConstraints = {
    @UniqueConstraint(name = "uk_artistic_name", columnNames = "artistic_name")
})
public class ArtistEntity {
    @Id
    @GeneratedValue(
        strategy = GenerationType.UUID
    )
    @Column(name="id")
    private UUID uuid;

    @Column(name="artistic_name",nullable = false)
    private String artisticname;

    @Column(name = "is_group")
    private Boolean isGroup;

    @ManyToMany
    @JoinTable(
        name="artist_members",
        joinColumns = @JoinColumn(name="member_id"),
        inverseJoinColumns = @JoinColumn(name="group_id")
    )
    private List<ArtistEntity> groups;

    @ManyToMany(mappedBy = "groups")
    private List<ArtistEntity> members;

    @OneToMany(mappedBy = "artist")
    private List<AlbumEntity> albums;
}
