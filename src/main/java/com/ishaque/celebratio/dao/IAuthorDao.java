package com.ishaque.celebratio.dao;

import java.util.List;
import java.util.Optional;

import com.ishaque.celebratio.domain.Author;

public interface IAuthorDao {

    void create(Author author);

    Optional<Author> findOne(long authorId);

    List<Author> findMany();

    void updateAuthor(Author author);

    void deleteAuthor(long authorId);

}
