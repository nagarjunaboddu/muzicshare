package com.galvanize.muzicshare.controller;

import com.galvanize.muzicshare.entity.Playlist;
import com.galvanize.muzicshare.entity.Song;
import com.galvanize.muzicshare.exception.PlaylistException;
import com.galvanize.muzicshare.model.PlaylistResponse;
import com.galvanize.muzicshare.service.PlaylistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/")
public class MuzicController {

    private PlaylistService playlistService;

    public MuzicController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @PostMapping("/playlist/add")
    public ResponseEntity<PlaylistResponse> addNewPlaylist(@RequestBody Playlist reqPlaylist) throws PlaylistException {
        PlaylistResponse resp = new PlaylistResponse();
        Playlist playlist = playlistService.addNewPlaylist(reqPlaylist);
        resp.setResponseText("Playlist Successfully Added");
        resp.setResponseBody(playlist);
        return new ResponseEntity<PlaylistResponse>(resp, HttpStatus.CREATED);
    }

    @PostMapping("/playlist/add/song/{songName}")
    public ResponseEntity<PlaylistResponse> addSongToPlaylist(@RequestBody Playlist reqPlaylist, @PathVariable String songName) throws PlaylistException {
        PlaylistResponse resp = new PlaylistResponse();
        Playlist playlistReq = playlistService.findPlayList(reqPlaylist);
        boolean error = false;
        try {
            Song song = playlistService.findSong(songName);
            playlistReq = playlistService.addSongToPlaylist(playlistReq, song);
        } catch (PlaylistException e) {
            error = true;
        }
        resp.setResponseBody(playlistReq);
        if (!error) {
            resp.setResponseText("Playlist Successfully Updated");
            return new ResponseEntity<PlaylistResponse>(resp, HttpStatus.OK);
        } else {
            resp.setResponseText("No Such Song Found");
            return new ResponseEntity<PlaylistResponse>(resp, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/playlist/remove/song/{songName}")
    public ResponseEntity<PlaylistResponse> removeSongFromPlaylist(@RequestBody Playlist reqPlaylist, @PathVariable String songName) throws PlaylistException {
        PlaylistResponse resp = new PlaylistResponse();
        Playlist playlistReq = playlistService.findPlayList(reqPlaylist);
        Playlist playlist = playlistService.deleteSongFromPlaylist(playlistReq, songName);
        resp.setResponseText("Playlist Successfully Updated");
        resp.setResponseBody(playlist);
        return new ResponseEntity<PlaylistResponse>(resp, HttpStatus.OK);
    }

}
