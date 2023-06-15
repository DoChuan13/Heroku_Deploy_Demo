package com.example.demo.service.category;

import com.example.demo.model.Category;
import com.example.demo.model.User;
import com.example.demo.repository.ICategoryRepository;
import com.example.demo.security.userprincal.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceIMPL implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private UserDetailService userDetailService;

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void save(Category category) {
        User user = userDetailService.getCurrentUser();
        category.setUser(user);
        categoryRepository.save(category);
    }

    @Override
    public Boolean existByName(String name) {
        return categoryRepository.existsByName(name);
    }
}
