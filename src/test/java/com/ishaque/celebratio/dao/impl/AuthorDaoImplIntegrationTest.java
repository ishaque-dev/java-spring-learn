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

import com.ishaque.celebratio.dao.TestDataUtils;
import com.ishaque.celebratio.domain.Author;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorDaoImplIntegrationTest {

    AuthorDaoImpl underTest;

    @Autowired
    public AuthorDaoImplIntegrationTest(AuthorDaoImpl underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCreatedAndRecalled() {
        Author author = TestDataUtils.createTestAuthorA();
        underTest.create(author);
        Optional<Author> result = underTest.findOne(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatMultipleAuthorsCreatedAndRecalled() {
        Author authorA = TestDataUtils.createTestAuthorA();
        underTest.create(authorA);
        Author authorB = TestDataUtils.createTestAuthorB();
        underTest.create(authorB);
        Author authorC = TestDataUtils.createTestAuthorC();
        underTest.create(authorC);
        List<Author> res = underTest.findMany();
        assertThat(res).hasSize(3);
        assertThat(res).contains(authorA, authorB, authorC);

    }

    @Test
    public void testThatAuthorCanBeUpdated() {
        Author authorA = TestDataUtils.createTestAuthorA();
        underTest.create(authorA);
        authorA.setName("Jack sparrow");
        underTest.updateAuthor(authorA);
        Optional<Author> result = underTest.findOne(authorA.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorA);

    }

    @Test
    public void testThatAuthorCanBeDeleted() {
        Author authorA = TestDataUtils.createTestAuthorA();
        underTest.create(authorA);
        underTest.deleteAuthor(authorA.getId());
        Optional<Author> result = underTest.findOne(authorA.getId());
        assertThat(result).isEmpty();

    }
}
