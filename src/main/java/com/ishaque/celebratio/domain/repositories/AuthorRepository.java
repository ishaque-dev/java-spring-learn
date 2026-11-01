package com.ishaque.celebratio.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ishaque.celebratio.domain.entities.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Iterable<Author> ageLessThan(int age);

    Iterable<Author> ageGreaterThan(int age);

    @Query("SELECT a FROM Author a WHERE a.age > :age")
    Iterable<Author> findAuthorLessthan(@Param("age") int age);

}
