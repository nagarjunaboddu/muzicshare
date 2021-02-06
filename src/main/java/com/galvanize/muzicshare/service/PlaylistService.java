package com.galvanize.muzicshare.service;

import com.galvanize.muzicshare.entity.Playlist;
import com.galvanize.muzicshare.entity.Song;
import com.galvanize.muzicshare.exception.PlaylistException;
import com.galvanize.muzicshare.repository.PlaylistRepository;
import com.galvanize.muzicshare.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlaylistService {

    private PlaylistRepository playlistRepository;

    private SongRepository songRepository;

    public PlaylistService(PlaylistRepository playlistRepository, SongRepository songRepository) {
        this.playlistRepository = playlistRepository;
        this.songRepository = songRepository;
    }


    public Playlist addNewPlaylist(Playlist reqPlaylist) throws PlaylistException {
        if (reqPlaylist.getName() == null || reqPlaylist.getName().isBlank()) {
            System.out.println("Name is Blank or empty");
            throw new PlaylistException("Name is required","PLAYLIST_NAME_ERROR");
        }
        Playlist response = playlistRepository.save(reqPlaylist);
        return response;
    }

    public Playlist updatePlaylist(Playlist reqPlaylist, String songName) throws PlaylistException {
        Optional<Playlist> playlistOpt = playlistRepository.findByName(reqPlaylist.getName());
        if (playlistOpt.isEmpty()) {
            throw new PlaylistException("No Such Playlist Found","PLAYLIST_NON_EXISTENCE_ERROR");
        }
        Optional<Song> songOpt = songRepository.findByName(songName);
        if (songOpt.isEmpty()) {
            throw new PlaylistException("No Such Song Found","SONG_NON_EXISTENCE_ERROR");
        }
        Playlist pl = playlistOpt.get();
        pl.getSongs().add(songOpt.get());
        Playlist response = playlistRepository.save(pl);
        return response;
    }
}
