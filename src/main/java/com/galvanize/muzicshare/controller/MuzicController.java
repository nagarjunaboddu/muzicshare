package com.galvanize.muzicshare.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/")
public class MuzicController {

    @PostMapping("/playlist/add")
    public String reviewSpecificMovie(@RequestBody String name) {
        return "Success";
    }

}
