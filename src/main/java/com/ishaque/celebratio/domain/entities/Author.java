package com.ishaque.celebratio.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "authors")
public class Author {
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_id_sequence")
    @SequenceGenerator(name = "author_id_sequence", sequenceName = "author_id_sequence", allocationSize = 1)
    long id;
    String name;
    int age;

}
