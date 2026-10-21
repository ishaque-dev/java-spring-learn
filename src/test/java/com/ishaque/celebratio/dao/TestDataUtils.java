package com.ishaque.celebratio.dao;

import com.ishaque.celebratio.domain.Author;
import com.ishaque.celebratio.domain.Book;

public final class TestDataUtils {

    public TestDataUtils() {
    }

    public static Author createTestAuthor() {
        return Author.builder().id(1L).name("James bond").age(20)
                .build();
    }

    public static Author createTestAuthorA() {
        return Author.builder().id(2L).name("Johny depp").age(20)
                .build();
    }

    public static Author createTestAuthorB() {
        return Author.builder().id(3L).name("Tony stark").age(20)
                .build();
    }

    public static Author createTestAuthorC() {
        return Author.builder().id(4L).name("Lioanrdo decaprio").age(20)
                .build();
    }

    public static Book createTestBook() {
        return Book.builder().authorId(1L).isbn("A").title("Bare Bone")
                .build();
    }

    public static Book createTestBookA() {
        return Book.builder().authorId(2L).isbn("B").title("Wolverine")
                .build();
    }

    public static Book createTestBookB() {
        return Book.builder().authorId(3L).isbn("C").title("Black pearl")
                .build();
    }

    public static Book createTestBookC() {
        return Book.builder().authorId(4L).isbn("D").title("troy")
                .build();
    }
}
