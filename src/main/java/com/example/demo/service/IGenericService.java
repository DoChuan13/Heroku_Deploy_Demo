package com.example.demo.service;

public interface IGenericService<T> {
    Iterable<T> findAll();

    void save(T t);
}
