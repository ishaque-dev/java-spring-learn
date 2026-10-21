package com.ishaque.celebratio.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ishaque.celebratio.dao.IBookDao;
import com.ishaque.celebratio.domain.Book;

@Component
public class BookDaoImpl implements IBookDao {
    private final JdbcTemplate jdbcTemplate;

    public BookDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createBook(Book book) {

        jdbcTemplate.update("INSERT INTO books (isbn, title, author_id) VALUES (?,?,?)", book.getIsbn(),
                book.getTitle(), book.getAuthorId());
    }

    @Override
    public Optional<Book> findOne(String isbn) {
        List<Book> result = jdbcTemplate.query("SELECT isbn,title,author_id FROM books WHERE isbn = ? ",
                new BookRowMapper(), isbn);
        return result.stream().findFirst();
    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT isbn,title,author_id FROM books", new BookRowMapper());
    }

    public class BookRowMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Book.builder().authorId(rs.getLong("author_id")).isbn(rs.getString("isbn")).title(rs.getString(
                    "title")).build();
        }

    }

    @Override
    public void updateBook(Book book) {
        jdbcTemplate.update("UPDATE books SET isbn=?,title=?,author_id=? WHERE isbn=?", book.getIsbn(), book.getTitle(),
                book.getAuthorId(), book.getIsbn());
    }

    @Override
    public void deleteAbook(String isbn) {
        jdbcTemplate.update("DELETE FROM books WHERE isbn=?", isbn);
    }
}
