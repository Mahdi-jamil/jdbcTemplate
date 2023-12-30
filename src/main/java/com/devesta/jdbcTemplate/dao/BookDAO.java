package com.devesta.jdbcTemplate.dao;

import com.devesta.jdbcTemplate.domain.Author;
import com.devesta.jdbcTemplate.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDAO {
    void create(Book book);
    Optional<Book> findOne(String isbn);

    List<Book> findAll();
    void update(String isbn, Book book);

    void delete(String isbn);
}
