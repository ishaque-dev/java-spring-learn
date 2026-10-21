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
import com.ishaque.celebratio.domain.Author;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTest {
        @Mock
        JdbcTemplate jdbcTemplate;
        @InjectMocks
        private AuthorDaoImpl underTest;

        @Test

        public void testThatCreateAuthorGenerateCorrectSQL() {
                Author author = TestDataUtils.createTestAuthor();
                underTest.create(author);
                verify(jdbcTemplate).update(
                                eq("INSERT INTO authors (id,name,age) VALUES (?,?,?)"),
                                eq(1L), eq("James bond"), eq(20));

        }

        @Test
        public void testThatFindOneGenerateCorrectSQL() {
                Author author = TestDataUtils.createTestAuthor();
                underTest.findOne(author.getId());
                verify(jdbcTemplate).query(eq(
                                "SELECT id,name,age FROM authors WHERE id = ? LIMIT 1"),
                                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),
                                eq(author.getId()));
        }

        @Test
        public void testThatFindManyGeneratesCorrectSQL() {
                List<Author> authors = underTest.findMany();
                verify(jdbcTemplate).query(eq("SELECT id,name,age FROM authors"),
                                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any());
        }

        @Test
        public void testThatUpdateGenerateCorrectSQL() {
                Author author = TestDataUtils.createTestAuthor();
                underTest.updateAuthor(author);
                verify(jdbcTemplate).update(eq("UPDATE authors SET id = ?,name = ?,age = ? WHERE id = ?"),
                                eq(author.getId()), eq(author.getName()), eq(author.getAge()), eq(author.getId()));

        }

        @Test
        public void testThatDeleteGenerateCorrectSQL() {
                underTest.deleteAuthor(1L);
                verify(jdbcTemplate).update(eq("DELETE FROM authors WHERE id=?"), eq(1L));
        }

}
