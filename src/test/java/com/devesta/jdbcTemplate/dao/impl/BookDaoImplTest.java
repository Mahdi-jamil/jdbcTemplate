package com.devesta.jdbcTemplate.dao.impl;
import com.devesta.jdbcTemplate.TestDataUtil;
import com.devesta.jdbcTemplate.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private BookDaoImpl daoUnderTest;

    @Test
    public void testThatCreateAuthorWithCorrectSql(){
        Book book = TestDataUtil.createBookInstance();
        daoUnderTest.create(book);

        verify(jdbcTemplate).update(
                eq("INSERT INTO book (isbn, title, author_id) VALUES (?, ?, ?)"),
                eq("1234"),eq("java"),eq(1L));
    }

    @Test
    public void testThatFindOneGenerateCorrectSql(){
        daoUnderTest.findOne("1234");
        verify(jdbcTemplate).query(
                eq("select isbn,title,author_id from book where isbn = ?"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any(),
                eq("1234")
        );
    }


}
