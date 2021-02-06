package com.galvanize.muzicshare.it;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.muzicshare.entity.Playlist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MuzicIT {

    @Autowired
    private MockMvc mockMvc;

    /*
    When a playlist is created with a name
    Then a confirmation is returned that it was successful.
    And the playlist is empty.
     */

    @Test
    void postNewPlaylist_returnsCreated() throws Exception {
        String playlistName = "MyFunky";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/playlist/add")
                .content(playlistName).contentType(MediaType.TEXT_PLAIN_VALUE))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.responseText").value("Playlist Successfully Added"));;
    }
}
