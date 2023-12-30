package com.devesta.jdbcTemplate.dao.impl;

import com.devesta.jdbcTemplate.dao.BookDAO;
import com.devesta.jdbcTemplate.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
@Component
public class BookDaoImpl implements BookDAO {
    private final JdbcTemplate jdbcTemplate;

    public BookDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Book book) {
        jdbcTemplate.update(
                "INSERT INTO book (isbn, title, author_id) VALUES (?, ?, ?)",
                book.getIsbn(), book.getTitle(), book.getAuthorId());
    }

    @Override
    public Optional<Book> findOne(String isbn) {
        List<Book> result = jdbcTemplate.query(
                "select isbn,title,author_id from book where isbn = ?"
                , new BookRowMapper(), isbn);
        return result.stream().findFirst();
    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query("select isbn,title,author_id from book", new BookRowMapper() );
    }

    @Override
    public void update(String isbn, Book book) {
        jdbcTemplate.update("update book set isbn = ?, title = ?, author_id = ? where isbn=?",
                book.getIsbn(),book.getTitle(),book.getAuthorId(),isbn
        );
    }

    @Override
    public void delete(String isbn) {
        jdbcTemplate.update("delete from book where isbn = ?",isbn);
    }

    public static class BookRowMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Book.builder()
                    .isbn(rs.getString("isbn"))
                    .title(rs.getString("title"))
                    .authorId(rs.getLong("author_id"))
                    .build();
        }
    }


}
