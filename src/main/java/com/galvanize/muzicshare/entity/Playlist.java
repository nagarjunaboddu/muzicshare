package com.galvanize.muzicshare.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "playlist")
@Builder
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @Singular //to return empty list when null
    private List<Song> songs;


}
