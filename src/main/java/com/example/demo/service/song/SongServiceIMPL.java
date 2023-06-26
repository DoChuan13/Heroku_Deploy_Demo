package com.example.demo.service.song;

import com.example.demo.model.Song;
import com.example.demo.model.User;
import com.example.demo.repository.ISongRepository;
import com.example.demo.security.userprincal.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class SongServiceIMPL implements ISongService {
    @Autowired
    private ISongRepository songRepository;
    @Autowired
    private UserDetailService userDetailService;

    @Override
    public Iterable<Song> findAll() {
        return songRepository.findAll();
    }

    @Override
    public void save(Song song) {
        User user = userDetailService.getCurrentUser();
        song.setUser(user);
        songRepository.save(song);
    }

    @Override
    public Page<Song> findAll(Pageable pageable) {
        return songRepository.findAll(pageable);
    }

    @Override
    public Optional<Song> findById(Long id) {
        return songRepository.findById(id);
    }
}
