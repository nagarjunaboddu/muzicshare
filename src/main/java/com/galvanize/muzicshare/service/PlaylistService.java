package com.galvanize.muzicshare.service;

import com.galvanize.muzicshare.entity.Playlist;
import com.galvanize.muzicshare.exception.PlaylistException;
import com.galvanize.muzicshare.repository.PlaylistRepository;
import org.springframework.stereotype.Service;

@Service
public class PlaylistService {

    private PlaylistRepository playlistRepository;

    public PlaylistService (PlaylistRepository playlistRepository){
        this.playlistRepository = playlistRepository;
    }


    public Playlist addNewPlaylist(Playlist reqPlaylist) throws PlaylistException {
        if(reqPlaylist.getName() == null || reqPlaylist.getName().isBlank()){
            System.out.println("Name is Blank or empty");
            throw new PlaylistException("Name is required");
        }
        Playlist response = playlistRepository.save(reqPlaylist);
        return response;
    }
}
