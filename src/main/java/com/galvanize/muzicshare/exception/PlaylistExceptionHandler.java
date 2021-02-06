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
        System.out.println("Subhrajit : "  + playlistExceptionModel.getErrorMsg());
        return new ResponseEntity<PlaylistExceptionModel>( playlistExceptionModel, HttpStatus.BAD_REQUEST);
    }
}
