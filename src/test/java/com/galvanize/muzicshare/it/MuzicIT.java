package com.galvanize.muzicshare.it;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.muzicshare.entity.Playlist;
import com.galvanize.muzicshare.repository.PlaylistRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class MuzicIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    /*
    When a playlist is created with a name
    Then a confirmation is returned that it was successful.
    And the playlist is empty.
     */

    @Test
    void postNewPlaylist_returnsCreated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/playlist/add")
                .content(getPlaylistRequestAsString("MyFunky")).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.responseText").value("Playlist Successfully Added"));
    }

    @Test
    void postNewPlaylist_validateEmptySongsForNewPlaylist() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/playlist/add")
                .content(getPlaylistRequestAsString("MyFunky")).contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.responseBody.songs", hasSize(0)));
    }

    @Test
    void postNewPlaylist_validateAddingMultiplePlaylist() throws Exception {
        String playlistName = "MyFunky";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/playlist/add")
                .content(getPlaylistRequestAsString(playlistName)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.responseBody.name").value(playlistName))
                .andExpect(jsonPath("$.responseBody.id").value(4));
        playlistName = "MyGroovy";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/playlist/add")
                .content(getPlaylistRequestAsString(playlistName)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.responseBody.name").value(playlistName))
                .andExpect(jsonPath("$.responseBody.id").value(5));
    }

    /*
    When a playlist is created without a name
    Then a message is returned that a name is required.
     */
    @Test
    void postNewPlaylist_error_NoName_Null() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/playlist/add")
                .content(getPlaylistRequestAsString(null)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMsg").value("Name is required"));
    }

    @Test
    void postNewPlaylist_error_NoName_empty() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/playlist/add")
                .content(getPlaylistRequestAsString("")).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMsg").value("Name is required"));
    }
    @Test
    void postNewPlaylist_error_NoName_blank() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/playlist/add")
                .content(getPlaylistRequestAsString("      ")).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMsg").value("Name is required"));
    }


    /*
    Given an empty playlist
    When an song is added
    Then the playlist has 1 song
     */

    @Test
    void validateAddSongsForNewPlaylist_error_NoPlaylistFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/playlist/add/song/Heart")
                .content(getPlaylistRequestAsString("dummy")).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMsg").value("No Such Playlist Found"));
    }


    @Test
    void validateAddSongsForNewPlaylist() throws Exception {
        String playlistName = "SadSongs";
        //Add a new Playlist with no songs
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/playlist/add")
                .content(getPlaylistRequestAsString(playlistName)).contentType(MediaType.APPLICATION_JSON));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/playlist/add/song/Heart")
                .content(getPlaylistRequestAsString(playlistName)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.responseBody.songs", hasSize(1)));
    }


    @Test
    void validateAddSongsForNewPlaylist_error_NoSongFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/playlist/add/song/Stop")
                .content(getPlaylistRequestAsString("Melody")).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMsg").value("No Such Song Found"));
    }


    //Utility Method to get Request playlist as a String
    private String getPlaylistRequestAsString(String playlistName) throws JsonProcessingException {
        Playlist request = Playlist.builder().name(playlistName).build();
        String requestString = mapper.writeValueAsString(request);
        return requestString;
    }
}
