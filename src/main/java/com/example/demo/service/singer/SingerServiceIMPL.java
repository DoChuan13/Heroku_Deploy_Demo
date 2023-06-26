package com.example.demo.service.singer;

import com.example.demo.model.Singer;
import com.example.demo.model.User;
import com.example.demo.repository.ISingerRepository;
import com.example.demo.security.userprincal.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SingerServiceIMPL implements ISingerService {
    @Autowired
    private ISingerRepository singerRepository;
    @Autowired
    private UserDetailService userDetailService;

    @Override
    public Iterable<Singer> findAll() {
        return singerRepository.findAll();
    }

    @Override
    public void save(Singer singer) {
        User user = userDetailService.getCurrentUser();
        singer.setUser(user);
        singerRepository.save(singer);
    }

    @Override
    public Optional<Singer> findById(Long id) {
        return singerRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        singerRepository.deleteById(id);
    }

    @Override
    public Page<Singer> findAll(Pageable pageable) {
        return singerRepository.findAll(pageable);
    }
}
