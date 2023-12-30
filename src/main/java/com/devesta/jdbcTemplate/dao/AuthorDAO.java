package com.devesta.jdbcTemplate.dao;

import com.devesta.jdbcTemplate.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDAO {
    void create(Author author);

    Optional<Author> findOne(long authorID);

    List<Author> findAll();

    void update(Long id,Author author);

    void delete(Long id);
}
