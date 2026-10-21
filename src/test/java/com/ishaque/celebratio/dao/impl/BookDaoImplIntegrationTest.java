package com.ishaque.celebratio.dao.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ishaque.celebratio.dao.IAuthorDao;
import com.ishaque.celebratio.dao.TestDataUtils;
import com.ishaque.celebratio.domain.Author;
import com.ishaque.celebratio.domain.Book;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDaoImplIntegrationTest {

    IAuthorDao authorUnderTest;
    BookDaoImpl underTest;

    @Autowired
    public BookDaoImplIntegrationTest(AuthorDaoImpl authorUnderTest, BookDaoImpl underTest) {
        this.authorUnderTest = authorUnderTest;
        this.underTest = underTest;
    }

    @Test
    public void testThatBookCreatedAndRecalled() {
        Author author = TestDataUtils.createTestAuthor();
        authorUnderTest.create(author);
        Book book = TestDataUtils.createTestBook();
        book.setAuthorId(author.getId());
        underTest.createBook(book);
        Optional<Book> result = underTest.findOne(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testThatMultipleBooksCreatedAndRecalled() {
        Author author = TestDataUtils.createTestAuthor();
        authorUnderTest.create(author);
        Book book = TestDataUtils.createTestBook();
        book.setAuthorId(author.getId());
        Book bookA = TestDataUtils.createTestBookA();
        bookA.setAuthorId(author.getId());
        Book bookB = TestDataUtils.createTestBookB();
        bookB.setAuthorId(author.getId());
        Book bookC = TestDataUtils.createTestBookC();
        bookC.setAuthorId(author.getId());
        underTest.createBook(book);
        underTest.createBook(bookA);
        underTest.createBook(bookB);
        underTest.createBook(bookC);
        List<Book> result = underTest.findAll();
        assertThat(result).hasSize(4).contains(book, bookA, bookB, bookC);

    }

    @Test
    public void testThatMultipleBooksCanBeUpdated() {
        Author author = TestDataUtils.createTestAuthor();
        authorUnderTest.create(author);
        Book book = TestDataUtils.createTestBook();
        underTest.createBook(book);
        book.setTitle("New title");
        underTest.updateBook(book);
        Optional<Book> result = underTest.findOne(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
        assertThat(result.get().getTitle()).isEqualTo("New title");
    }

    @Test
    public void testThatooksCanBeDeleted() {
        Author author = TestDataUtils.createTestAuthor();
        authorUnderTest.create(author);
        Book book = TestDataUtils.createTestBook();
        underTest.createBook(book);
        underTest.deleteAbook(book.getIsbn());
        Optional<Book> result = underTest.findOne(book.getIsbn());
        assertThat(result).isEmpty();
    }

}
