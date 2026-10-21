package com.ishaque.celebratio.dao;

import java.util.List;
import java.util.Optional;

import com.ishaque.celebratio.domain.Book;

public interface IBookDao {
    public void createBook(Book book);

    Optional<Book> findOne(String isbn);

    List<Book> findAll();

    void updateBook(Book book);

    void deleteAbook(String isbn);
}
