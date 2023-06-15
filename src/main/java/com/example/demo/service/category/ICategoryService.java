package com.example.demo.service.category;

import com.example.demo.model.Category;
import com.example.demo.service.IGenericService;

public interface ICategoryService extends IGenericService<Category> {
    Boolean existByName(String name);
}
