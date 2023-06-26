package com.example.demo.service.singer;

import com.example.demo.model.Singer;
import com.example.demo.service.IGenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ISingerService extends IGenericService<Singer> {
    Optional<Singer> findById(Long id);

    void deleteById(Long id);

    Page<Singer> findAll(Pageable pageable);
}
