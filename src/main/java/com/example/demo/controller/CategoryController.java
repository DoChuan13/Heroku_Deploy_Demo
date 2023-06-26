package com.example.demo.controller;

import com.example.demo.dto.response.ResponseMessage;
import com.example.demo.model.Category;
import com.example.demo.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = {"/category"})
@CrossOrigin(origins = "*")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> showListCategory() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detailCategory(@PathVariable Long id) {
        Optional<Category> category = categoryService.findById(id);
        if (!category.isPresent()) {
            return new ResponseEntity<>(new ResponseMessage("Not found"), HttpStatus.OK);
        }
        return new ResponseEntity<>(category.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addNewCategory(@RequestBody Category category) {
        if (categoryService.existByName(category.getName())) {
            return new ResponseEntity<>(new ResponseMessage("Category Name Exist"), HttpStatus.OK);
        } else {
            categoryService.save(category);
            return new ResponseEntity<>(new ResponseMessage("Add Success"), HttpStatus.OK);
        }
    }

    @GetMapping("/page")
    public ResponseEntity<?> pageCategory(@PageableDefault(size = 3) Pageable pageable) {
        return new ResponseEntity<>(categoryService.findAll(pageable), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        Optional<Category> current = categoryService.findById(id);
        if (!current.isPresent()) {
            return new ResponseEntity<>(new ResponseMessage("Not found"), HttpStatus.OK);
        }
        Category currentCategory = current.get();
        if (currentCategory.getName().equals(category.getName())) {
            if (category.getAvatar().equals(currentCategory.getAvatar())) {
                return new ResponseEntity<>(new ResponseMessage("Nothing Change"), HttpStatus.OK);
            } else {
                currentCategory.setAvatar(category.getAvatar());
            }
        } else {
            if (categoryService.existByName(category.getName())) {
                return new ResponseEntity<>(new ResponseMessage("Exist Name"), HttpStatus.OK);
            } else {
                currentCategory.setName(category.getName());
            }

            if (!category.getAvatar().equals(currentCategory.getAvatar())) {
                currentCategory.setAvatar(category.getAvatar());
            }
        }

        categoryService.save(currentCategory);
        return new ResponseEntity<>(new ResponseMessage("Update Success"), HttpStatus.OK);
    }
}
