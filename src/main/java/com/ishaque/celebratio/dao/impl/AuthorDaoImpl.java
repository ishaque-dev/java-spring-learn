package com.ishaque.celebratio.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ishaque.celebratio.dao.IAuthorDao;
import com.ishaque.celebratio.domain.Author;

@Component
public class AuthorDaoImpl implements IAuthorDao {
    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Author author) {
        jdbcTemplate.update("INSERT INTO authors (id,name,age) VALUES (?,?,?)", author.getId(), author.getName(),
                author.getAge());
    }

    @Override
    public Optional<Author> findOne(long authorId) {
        List<Author> result = jdbcTemplate.query(
                "SELECT id,name,age FROM authors WHERE id = ? LIMIT 1",
                new AuthorRowMapper(), authorId);
        if (result.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(result.get(0));
    }

    @Override
    public List<Author> findMany() {
        return jdbcTemplate.query("SELECT id,name,age FROM authors", new AuthorRowMapper());
    }

    public static class AuthorRowMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Author.builder().id(rs.getLong("id")).name(rs.getString("name")).age(rs.getInt("age")).build();
        }

    }

    @Override
    public void updateAuthor(Author author) {
        jdbcTemplate.update("UPDATE authors SET id = ?,name = ?,age = ? WHERE id = ?", author.getId(), author.getName(),
                author.getAge(), author.getId());
    }

    @Override
    public void deleteAuthor(long authorId) {
        jdbcTemplate.update("DELETE FROM authors WHERE id=?", authorId);
    }

}
