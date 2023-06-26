package com.example.demo.controller;

import com.example.demo.dto.request.SongDTO;
import com.example.demo.dto.response.ResponseMessage;
import com.example.demo.model.Song;
import com.example.demo.service.song.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = {"/song"})
public class SongController {
    @Autowired
    private ISongService songService;

    @GetMapping("/page")
    public ResponseEntity<?> pageSong(Pageable pageable) {
        return new ResponseEntity<>(songService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> listSong() {
        return new ResponseEntity<>(songService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createSong(
            @Valid
            @RequestBody
            SongDTO songDTO) {
        System.out.println(songDTO);
        Song song = new Song(songDTO.getName(), songDTO.getAvatar(), songDTO.getLyrics(), songDTO.getMp3Url(),
                             songDTO.getCategory(), songDTO.getSingers());
        songService.save(song);
        System.out.println(song);
        return new ResponseEntity<>(new ResponseMessage("Create Success!"), HttpStatus.OK);
    }
}
