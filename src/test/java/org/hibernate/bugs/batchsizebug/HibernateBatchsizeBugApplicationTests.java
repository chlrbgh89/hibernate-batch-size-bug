package org.hibernate.bugs.batchsizebug;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.hibernate.bugs.batchsizebug.test.Article;
import org.hibernate.bugs.batchsizebug.test.Tag;
import org.hibernate.bugs.batchsizebug.test.ArticleResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class HibernateBatchSizeBugApplicationTests {

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void fixture() {
        List<Tag> tags = List.of(new Tag("t1"), new Tag("t2"), new Tag("t3"));
        List<Tag> tags2 = List.of(new Tag("t4"), new Tag("t5"));

        Article article = new Article();
        Article article2 = new Article();
        Article article3 = new Article();
        Article article4 = new Article();
        Article article5 = new Article();

        article.setTags(tags);
        article3.setTags(tags2);

        tags.forEach(entityManager::persist);
        tags2.forEach(entityManager::persist);

        entityManager.persist(article);
        entityManager.persist(article2);
        entityManager.persist(article3);
        entityManager.persist(article4);
        entityManager.persist(article5);

        entityManager.flush();
        entityManager.clear();
    }

    @Test
    void hibernate_batch_size_bug() {
        Query query = entityManager.createQuery("select a from Article a");
        List<Article> tech = query.setMaxResults(20).getResultList();

        tech.stream()
                .map(ArticleResponseDto::new)
                .toList();
    }

}
