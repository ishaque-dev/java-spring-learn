package com.ishaque.celebratio.dao.impl.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ishaque.celebratio.dao.TestDataUtils;
import com.ishaque.celebratio.domain.entities.Author;
import com.ishaque.celebratio.domain.entities.Book;
import com.ishaque.celebratio.domain.repositories.AuthorRepository;
import com.ishaque.celebratio.domain.repositories.BookRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDaoImplIntegrationTest {

    AuthorRepository authorUnderTest;
    BookRepository underTest;

    @Autowired
    public BookDaoImplIntegrationTest(AuthorRepository authorUnderTest, BookRepository underTest) {
        this.authorUnderTest = authorUnderTest;
        this.underTest = underTest;
    }

    @Test
    public void testThatBookCreatedAndRecalled() {
        Author author = authorUnderTest.save(TestDataUtils.createTestAuthor());
        Book book = TestDataUtils.createTestBook(author);
        book.setAuthor(author);
        underTest.save(book);
        Optional<Book> result = underTest.findById(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testThatMultipleBooksCreatedAndRecalled() {
        Author author = authorUnderTest.save(TestDataUtils.createTestAuthor());

        Book book = TestDataUtils.createTestBook(author);
        Book bookA = TestDataUtils.createTestBookA(author);
        Book bookB = TestDataUtils.createTestBookB(author);
        Book bookC = TestDataUtils.createTestBookC(author);

        underTest.save(book);
        underTest.save(bookA);
        underTest.save(bookB);
        underTest.save(bookC);
        Iterable<Book> result = underTest.findAll();
        assertThat(result).hasSize(4).contains(book, bookA, bookB, bookC);

    }

    @Test
    public void testThatMultipleBooksCanBeUpdated() {
        Author author = TestDataUtils.createTestAuthor();
        authorUnderTest.save(author);
        Book book = TestDataUtils.createTestBook(author);
        underTest.save(book);
        book.setTitle("New title");
        underTest.save(book);
        Optional<Book> result = underTest.findById(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
        assertThat(result.get().getTitle()).isEqualTo("New title");
    }

    @Test
    public void testThatooksCanBeDeleted() {
        Author author = TestDataUtils.createTestAuthor();
        authorUnderTest.save(author);
        Book book = TestDataUtils.createTestBook(author);
        underTest.save(book);
        underTest.deleteById(book.getIsbn());
        Optional<Book> result = underTest.findById(book.getIsbn());
        assertThat(result).isEmpty();
    }

}
