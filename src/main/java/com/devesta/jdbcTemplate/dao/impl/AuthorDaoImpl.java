package com.devesta.jdbcTemplate.dao.impl;

import com.devesta.jdbcTemplate.dao.AuthorDAO;
import com.devesta.jdbcTemplate.domain.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
@Component
public class AuthorDaoImpl implements AuthorDAO {
    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Author author) {
        jdbcTemplate.update(
                "insert into author (id,name,age) values(?,?,?)"
        , author.getId(),author.getName(),author.getAge());
    }

    @Override
    public Optional<Author> findOne(long authorId) {
        List<Author> query = jdbcTemplate.query(
                "select id,name,age from author where id = ?"
                , new AuthorRowMapper(), authorId);
        return query.stream().findFirst();
    }

    @Override
    public List<Author> findAll() {
        return jdbcTemplate.query("select id,name,age from author",new AuthorRowMapper());
    }

    public static class AuthorRowMapper implements RowMapper<Author>{
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Author.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .age(rs.getInt("age"))
                    .build();
        }
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("delete from author where id = ?",id);
    }

    @Override
    public void update(Long id, Author author) {
        jdbcTemplate.update("update author set id = ?, name = ?, age = ? where id=?",
                author.getId(),author.getName(),author.getAge(),id);
    }
}
