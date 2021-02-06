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
        return playlistRepository.save(reqPlaylist);
    }

    public Playlist findPlayList(Playlist reqPlaylist) throws PlaylistException{
        Optional<Playlist> playlistOpt = playlistRepository.findByName(reqPlaylist.getName());
        if (playlistOpt.isEmpty()) {
            throw new PlaylistException("No Such Playlist Found","PLAYLIST_NON_EXISTENCE_ERROR");
        }
        return playlistOpt.get();
    }

    public Song findSong(String songName) throws PlaylistException {
        Optional<Song> songOpt = songRepository.findByName(songName);
        if (songOpt.isEmpty()) {
            throw new PlaylistException("No Such Song Found","SONG_NON_EXISTENCE_ERROR");
        }
        return songOpt.get();
    }
    public Playlist addSongToPlaylist(Playlist reqPlaylist, Song song){
        reqPlaylist.getSongs().add(song);
        Playlist response = playlistRepository.save(reqPlaylist);
        return response;
    }

    public Playlist deleteSongFromPlaylist(Playlist reqPlaylist, String songName){
        System.out.println("Size in begining : " + reqPlaylist.getSongs().size());
        for (Song song : reqPlaylist.getSongs()){
            if(song.getName().equalsIgnoreCase(songName)){
                reqPlaylist.getSongs().remove(song);
            }
        }
        System.out.println("Size in end : " + reqPlaylist.getSongs().size());
        return playlistRepository.save(reqPlaylist);
    }
}
