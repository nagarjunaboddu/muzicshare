package com.galvanize.muzicshare.controller;

import com.galvanize.muzicshare.model.PlaylistResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/")
public class MuzicController {

    @PostMapping("/playlist/add")
    public ResponseEntity<PlaylistResponse> reviewSpecificMovie(@RequestBody String name) {
        PlaylistResponse resp = new PlaylistResponse();
        resp.setResponseText("Playlist Successfully Added");
        return new ResponseEntity<PlaylistResponse>( resp, HttpStatus.CREATED);
    }

}
