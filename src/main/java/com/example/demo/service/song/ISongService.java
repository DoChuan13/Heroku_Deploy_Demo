package com.example.demo.service.song;

import com.example.demo.model.Song;
import com.example.demo.service.IGenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ISongService extends IGenericService<Song> {
    Page<Song> findAll(Pageable pageable);

    Optional<Song> findById(Long id);
}
