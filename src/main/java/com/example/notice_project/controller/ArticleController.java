package com.example.notice_project.controller;

import com.example.notice_project.dto.ArticleForm;

import com.example.notice_project.entity.Article;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        System.out.println(form.toString());

        // 1. DTO를 변환! Entity로
//        Article article = form.toEntity();

        // 2. Repository에게 Entity를 DB에 저장하도록 함!


       return "";
    }


}
