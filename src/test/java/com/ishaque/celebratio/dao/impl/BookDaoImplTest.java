package com.ishaque.celebratio.dao.impl;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ishaque.celebratio.dao.TestDataUtils;
import com.ishaque.celebratio.domain.Book;

@ExtendWith(MockitoExtension.class)
public class BookDaoImplTest {
        @Mock
        JdbcTemplate jdbcTemplate;
        @InjectMocks
        private BookDaoImpl underTest;

        @Test
        void testThatCreateBookGenerateCorrectSQL() {
                Book book = TestDataUtils.createTestBook();
                underTest.createBook(book);
                verify(jdbcTemplate).update(eq(
                                "INSERT INTO books (isbn, title, author_id) VALUES (?,?,?)"), eq("A"),
                                eq("Bare Bone"), eq(1L));
        }

        @Test

        void testThatFindOneGeneratesCorrectSQL() {
                Book book = TestDataUtils.createTestBook();
                underTest.findOne(book.getIsbn());
                verify(jdbcTemplate).query(eq("SELECT isbn,title,author_id FROM books WHERE isbn = ? "),
                                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any(), eq(book.getIsbn()));
        }

        @Test
        void testThatFindAllGeneratesCorrectSQL() {
                List<Book> result = underTest.findAll();
                verify(jdbcTemplate).query(eq("SELECT isbn,title,author_id FROM books"),
                                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any());

        }

        @Test
        void testThatUpdateGenerateCorrectSQL() {
                Book book = TestDataUtils.createTestBookA();
                book.setTitle("Updated title");
                underTest.updateBook(book);
                verify(jdbcTemplate).update(eq("UPDATE books SET isbn=?,title=?,author_id=? WHERE isbn=?"),
                                eq(book.getIsbn()), eq(book.getTitle()), eq(book.getAuthorId()), eq(book.getIsbn()));

        }

        @Test
        void testThatDeleteGenerateCorrectSQL() {
                Book book = TestDataUtils.createTestBookA();
                underTest.deleteAbook(book.getIsbn());
                verify(jdbcTemplate).update(eq("DELETE FROM books WHERE isbn=?"), eq(book.getIsbn()));

        }
}
