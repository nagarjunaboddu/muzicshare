package com.galvanize.muzicshare.model;

import com.galvanize.muzicshare.entity.Playlist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaylistResponse {

    private String responseCode;
    private String responseText;

    private Playlist responseBody;
}
