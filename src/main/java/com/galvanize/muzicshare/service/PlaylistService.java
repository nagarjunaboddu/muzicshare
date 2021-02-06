package com.galvanize.muzicshare.service;

import com.galvanize.muzicshare.entity.Playlist;
import com.galvanize.muzicshare.repository.PlaylistRepository;
import org.springframework.stereotype.Service;

@Service
public class PlaylistService {

    private PlaylistRepository playlistRepository;

    public PlaylistService (PlaylistRepository playlistRepository){
        this.playlistRepository = playlistRepository;
    }


    public Playlist addNewPlaylist(String name) {
        Playlist request = Playlist.builder().name(name).build();
        Playlist response = playlistRepository.save(request);
        return response;
    }
}
