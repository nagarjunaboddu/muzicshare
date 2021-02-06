package com.galvanize.muzicshare.exception;

import com.galvanize.muzicshare.model.PlaylistExceptionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PlaylistExceptionHandler {

    @ExceptionHandler(PlaylistException.class)
    public ResponseEntity<PlaylistExceptionModel> handleTriviaException(PlaylistException playlistException){
        PlaylistExceptionModel playlistExceptionModel = new PlaylistExceptionModel(playlistException.getMessage());
        HttpStatus status = null;
        if("PLAYLIST_NAME_ERROR".equalsIgnoreCase(playlistException.getErrorType())){
            status = HttpStatus.BAD_REQUEST;
        }else if("PLAYLIST_NON_EXISTENCE_ERROR".equalsIgnoreCase(playlistException.getErrorType())
                || "SONG_NON_EXISTENCE_ERROR".equalsIgnoreCase(playlistException.getErrorType())){
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<PlaylistExceptionModel>( playlistExceptionModel, status);
    }
}
