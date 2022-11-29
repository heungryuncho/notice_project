package com.example.notice_project.dto;

import com.example.notice_project.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor // 이 어노테이션을 쓰면 Constructor, Setter 생성자 안써줘도 됨.
@ToString
public class ArticleForm {

    private Long id; // id 필드 추가
    private String title;
    private String content;

    public Article toEntity() {
        return new Article(id, title, content);
    }
}
