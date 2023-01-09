package org.hibernate.bugs.batchsizebug.test;

import lombok.Getter;

import java.util.List;

@Getter
public class ArticleResponseDto {

    private final int id;
    private final List<TagDto> tags;

    public ArticleResponseDto(Article article) {
        this.id = article.getId();
        this.tags = article.getTags().stream()
                .map(TagDto::new)
                .toList();
    }
}

@Getter
class TagDto {

    private final String name;

    public TagDto(Tag tag) {
        this.name = tag.getName();
    }
}
