package org.hibernate.bugs.batchsizebug.test;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String categoryId;

    @BatchSize(size = 20)
    @ManyToMany
    private List<Tag> tags = new ArrayList<>();

}
