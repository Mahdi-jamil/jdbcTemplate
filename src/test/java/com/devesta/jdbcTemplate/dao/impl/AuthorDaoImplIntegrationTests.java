package com.devesta.jdbcTemplate.dao.impl;

import com.devesta.jdbcTemplate.TestDataUtil;
import com.devesta.jdbcTemplate.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorDaoImplIntegrationTests {

    private final AuthorDaoImpl daoUnderTest;

    @Autowired
    public AuthorDaoImplIntegrationTests(AuthorDaoImpl daoUnderTest) {
        this.daoUnderTest = daoUnderTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedInDataBase(){
        Author author = TestDataUtil.createAuthorInstanceA();
        daoUnderTest.create(author);
        Optional<Author> optionalAuthor = daoUnderTest.findOne(author.getId());
        Assertions.assertThat(optionalAuthor).isPresent();
        Assertions.assertThat(optionalAuthor.get()).isEqualTo(author);
    }

    @Test
    public void testThatMultipleAuthorCanExist(){
        Author author1 = TestDataUtil.createAuthorInstanceA();
        Author author2 = TestDataUtil.createAuthorInstanceB();
        Author author3 = TestDataUtil.createAuthorInstanceC();
        daoUnderTest.create(author1);
        daoUnderTest.create(author2);
        daoUnderTest.create(author3);

        List<Author> result = daoUnderTest.findAll();
        Assertions.assertThat(result)
                .hasSize(3)
                .containsExactly(author1,author2,author3);
    }

    @Test
    public void testAuthorCanBeUpdated(){
        Author author1 = TestDataUtil.createAuthorInstanceA();
        daoUnderTest.create(author1);
        author1.setName("updated name");

        daoUnderTest.update(author1.getId(), author1);
        Optional<Author> result = daoUnderTest.findOne(author1.getId());
        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get()).isEqualTo(author1);
    }

    @Test
    public void testAuthorCanBeDeleted(){
        Author author = TestDataUtil.createAuthorInstanceA();
        daoUnderTest.create(author);
        daoUnderTest.delete(author.getId());
        Optional<Author> result = daoUnderTest.findOne(author.getId());
        Assertions.assertThat(result).isEmpty();
    }


}
