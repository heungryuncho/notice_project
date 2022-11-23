package com.example.notice_project.repository;

import com.example.notice_project.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
}
//<1,2>에는 1에는 관리대상 엔티티를 집어넣음 2는 대푯값의 타입