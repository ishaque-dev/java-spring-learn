package com.ishaque.celebratio.dao;

import com.ishaque.celebratio.domain.entities.Author;
import com.ishaque.celebratio.domain.entities.Book;

public final class TestDataUtils {

    public TestDataUtils() {
    }

    public static Author createTestAuthor() {
        return Author.builder().name("James bond").age(55)
                .build();
    }

    public static Author createTestAuthorA() {
        return Author.builder().name("Johny depp").age(80)
                .build();
    }

    public static Author createTestAuthorB() {
        return Author.builder().name("Tony stark").age(30)
                .build();
    }

    public static Author createTestAuthorC() {
        return Author.builder().name("Lioanrdo decaprio").age(45)
                .build();
    }

    public static Book createTestBook(Author author) {
        return Book.builder().author(author).isbn("A").title("Bare Bone")
                .build();
    }

    public static Book createTestBookA(Author author) {
        return Book.builder().author(
                author).isbn("B").title("Wolverine")
                .build();
    }

    public static Book createTestBookB(Author author) {
        return Book.builder().author(
                author).isbn("C").title("Black pearl")
                .build();
    }

    public static Book createTestBookC(Author author) {
        return Book.builder().author(
                author).isbn("D").title("troy")
                .build();
    }
}
