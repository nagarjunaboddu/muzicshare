package com.galvanize.muzicshare.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "song")
@Builder
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name="playlist_id",nullable = false)
    private Playlist playlist;
}
