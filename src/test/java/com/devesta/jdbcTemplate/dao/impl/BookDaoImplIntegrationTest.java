package com.devesta.jdbcTemplate.dao.impl;

import com.devesta.jdbcTemplate.TestDataUtil;
import com.devesta.jdbcTemplate.dao.AuthorDAO;
import com.devesta.jdbcTemplate.domain.Author;
import com.devesta.jdbcTemplate.domain.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDaoImplIntegrationTest {

    private final BookDaoImpl daoUnderTest;
    private final AuthorDAO authorDAO;

    @Autowired
    public BookDaoImplIntegrationTest(BookDaoImpl daoUnderTest, AuthorDAO authorDAO) {
        this.daoUnderTest = daoUnderTest;
        this.authorDAO = authorDAO;
    }

    @Test
    public void testThatBookCanBeCreatedInDataBase() {
        Author author = TestDataUtil.createAuthorInstanceA();
        authorDAO.create(author);
        Book book = TestDataUtil.createBookInstance();
        book.setAuthorId(author.getId());
        daoUnderTest.create(book);
        Optional<Book> optionalBook = daoUnderTest.findOne(book.getIsbn());
        Assertions.assertThat(optionalBook).isPresent();
        Assertions.assertThat(optionalBook.get()).isEqualTo(book);
    }

    /*
    * make 3 books ,change author to book and run test , set author id to real one as above
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
    }*/


    @Test
    public void testBookCanBeUpdated(){
        Author author1 = TestDataUtil.createAuthorInstanceA();
        authorDAO.create(author1);

        Book book = TestDataUtil.createBookInstance();
        book.setAuthorId(author1.getId());
        daoUnderTest.create(book);

        book.setTitle("c++");
        daoUnderTest.update(book.getIsbn(),book);

        Optional<Book> result = daoUnderTest.findOne(book.getIsbn());
        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testBookCanBeDeleted(){
        Author author = TestDataUtil.createAuthorInstanceA();
        authorDAO.create(author);
        Book book = TestDataUtil.createBookInstance();
        book.setAuthorId(author.getId());

        daoUnderTest.create(book);
        daoUnderTest.delete(book.getIsbn());
        Optional<Book> result = daoUnderTest.findOne(book.getIsbn());
        Assertions.assertThat(result).isEmpty();
    }

}
