package com.devesta.jdbcTemplate.dao.impl;

import com.devesta.jdbcTemplate.TestDataUtil;
import com.devesta.jdbcTemplate.dao.impl.AuthorDaoImpl;
import com.devesta.jdbcTemplate.domain.Author;
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
public class AuthorDaoImplTest {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private AuthorDaoImpl daoUnderTest;
    
    @Test
    public void testThatCreateAuthorWithCorrectSql(){
        Author author = TestDataUtil.createAuthorInstanceA();
        daoUnderTest.create(author);

        verify(jdbcTemplate).update(
                eq("insert into author (id,name,age) values(?,?,?)"),
                eq(1L),eq("mahdi jamil"),eq(20));
    }

    @Test
    public void testThatFindOneGenerateCorrectSql(){
        daoUnderTest.findOne(1L);
        verify(jdbcTemplate).query(
                eq("select id,name,age from author where id = ?"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),
                eq(1L)
        );
    }
}
