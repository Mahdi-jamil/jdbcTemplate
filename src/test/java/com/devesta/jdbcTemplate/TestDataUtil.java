package com.devesta.jdbcTemplate;

import com.devesta.jdbcTemplate.domain.Author;
import com.devesta.jdbcTemplate.domain.Book;

public class TestDataUtil {
    private TestDataUtil(){}

    public static Author createAuthorInstanceA() {
        return Author.builder()
                .id(1L)
                .name("mahdi jamil")
                .age(20)
                .build();
    }
    public static Author createAuthorInstanceB() {
        return Author.builder()
                .id(2L)
                .name("zeinab ahmad")
                .age(20)
                .build();
    }
    public static Author createAuthorInstanceC() {
        return Author.builder()
                .id(3L)
                .name("test test")
                .age(24)
                .build();
    }
    public static Book createBookInstance() {
        return Book.builder()
                .isbn("1234")
                .title("java")
                .authorId(1L)
                .build();
    }
}
