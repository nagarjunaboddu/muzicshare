package com.galvanize.muzicshare.controller;

import com.galvanize.muzicshare.entity.Playlist;
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

    public MuzicController (PlaylistService playlistService){
        this.playlistService = playlistService;
    }

    @PostMapping("/playlist/add")
    public ResponseEntity<PlaylistResponse> addNewPlaylist(@RequestBody Playlist reqPlaylist) throws PlaylistException {
        PlaylistResponse resp = new PlaylistResponse();
        Playlist playlist = playlistService.addNewPlaylist(reqPlaylist);
        resp.setResponseText("Playlist Successfully Added");
        resp.setResponseBody(playlist);
        return new ResponseEntity<PlaylistResponse>( resp, HttpStatus.CREATED);
    }

    @PostMapping("/playlist/add/song/{songName}")
    public ResponseEntity<PlaylistResponse> addSongToPlaylist(@RequestBody Playlist reqPlaylist, @PathVariable String songName) throws PlaylistException {
        PlaylistResponse resp = new PlaylistResponse();
        Playlist playlist = playlistService.updatePlaylist(reqPlaylist, songName);
        resp.setResponseText("Playlist Successfully Updated");
        resp.setResponseBody(playlist);
        return new ResponseEntity<PlaylistResponse>( resp, HttpStatus.OK);
    }

}
