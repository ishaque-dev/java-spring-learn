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
import com.ishaque.celebratio.domain.repositories.AuthorRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorDaoImplIntegrationTest {

    AuthorRepository underTest;

    @Autowired
    public AuthorDaoImplIntegrationTest(AuthorRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCreatedAndRecalled() {
        Author author = TestDataUtils.createTestAuthorA();
        underTest.save(author);
        Optional<Author> result = underTest.findById(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatMultipleAuthorsCreatedAndRecalled() {
        Author authorA = TestDataUtils.createTestAuthorA();
        underTest.save(authorA);
        Author authorB = TestDataUtils.createTestAuthorB();
        underTest.save(authorB);
        Author authorC = TestDataUtils.createTestAuthorC();
        underTest.save(authorC);
        Iterable<Author> res = underTest.findAll();
        assertThat(res).hasSize(3);
        assertThat(res).contains(authorA, authorB, authorC);

    }

    @Test
    public void testThatAuthorCanBeUpdated() {
        Author authorA = TestDataUtils.createTestAuthorA();
        underTest.save(authorA);
        authorA.setName("Jack sparrow");
        underTest.save(authorA);
        Optional<Author> result = underTest.findById(authorA.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorA);

    }

    @Test
    public void testThatAuthorCanBeDeleted() {
        Author authorA = TestDataUtils.createTestAuthorA();
        underTest.save(authorA);
        underTest.deleteById(authorA.getId());
        Optional<Author> result = underTest.findById(authorA.getId());
        assertThat(result).isEmpty();

    }

    @Test
    public void testThatGetAuthorsBelowAge() {
        Author authorA = underTest.save(TestDataUtils.createTestAuthorA());
        Author authorB = underTest.save(TestDataUtils.createTestAuthorB());
        Author authorC = underTest.save(TestDataUtils.createTestAuthorC());
        Iterable<Author> result = underTest.ageLessThan(50);
        assertThat(result).isNotEmpty();
        assertThat(result).containsExactly(authorB, authorC);
    }

    @Test
    public void testThatGetAuthorsAboveAge() {
        Author authorA = underTest.save(TestDataUtils.createTestAuthorA());
        Author authorB = underTest.save(TestDataUtils.createTestAuthorB());
        Author authorC = underTest.save(TestDataUtils.createTestAuthorC());
        Iterable<Author> result = underTest.findAuthorLessthan(50);
        assertThat(result).isNotEmpty();
        assertThat(result).containsExactly(authorA);
    }
}
