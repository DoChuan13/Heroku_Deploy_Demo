package com.example.demo.service.category;

import com.example.demo.model.Category;
import com.example.demo.service.IGenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICategoryService extends IGenericService<Category> {
    Boolean existByName(String name);

    Page<Category> findAll(Pageable pageable);

    Optional<Category> findById(Long id);
}
