package com.example.notice_project.controller;

import com.example.notice_project.dto.ArticleForm;

import com.example.notice_project.dto.CommentDto;
import com.example.notice_project.entity.Article;
import com.example.notice_project.repository.ArticleRepository;
import com.example.notice_project.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller // 컨트롤러 선언
@Slf4j // 로깅을 위한 어노테이션
public class ArticleController {

    @Autowired // 스프링 부트가 미리 생성해놓은 객체를 가져다가 자동 연결!
    private ArticleRepository articleRepository;
    //자바라면 new 하면서 객체를 생성해줘야하는데 스프링 부트가 자체적으로 해줌
    @Autowired
    private CommentService commentService;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        log.info(form.toString());
//        System.out.println(form.toString()); -> 로깅기능으로 대체!

        // 1. DTO를 변환! Entity로
        Article article = form.toEntity();
        log.info(article.toString());
//        System.out.println(article.toString());

        // 2. Repository에게 Entity를 DB에 저장하도록 함!
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
//        System.out.println(saved.toString());

        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id = " + id); // @PathVariable로 id값이 그대로 나오는 지 확인

        //1. id로 데이터를 가져온다.
        Article articleEntity = articleRepository.findById(id).orElse(null); // 나중에 Optional로 리팩토링하기
        List<CommentDto> commentDtos = commentService.comments(id);

        //2. 가져온 데이터를 모델에 등록
        model.addAttribute("article", articleEntity);
        model.addAttribute("commentDtos", commentDtos);
        //3. 보여줄 페이지를 설정
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        //1. 모든 article을 가져온다!
        List<Article> articleEntityList = articleRepository.findAll();
        //2. 가져온 Article 묶음을 뷰로 전달
        model.addAttribute("articleList", articleEntityList);

        //3. 뷰 페이지를 설정
        return "articles/index"; //articles/index.mustache
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        // 수정할 데이터를 가져오기!
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 모델에 데이터를 등록!
        model.addAttribute("article", articleEntity);

        // 뷰 페이지 설정
        return "articles/edit";
    }

    @PostMapping("/articles/update") //mustache에서 form에서는 fetch를 쓸 수 없어서 post로 일단 씀.
    public String update(ArticleForm form){
        log.info(form.toString());

        //1. DTO를 엔티티로 변환
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());

        //2. 엔티티를 DB로 저장한다.
        //2-1. DB에 기존 데이터를 가져온다
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);

        //2-2 기존 데이터에 값을 갱신
        if(target != null){
            articleRepository.save(articleEntity); // 엔티티가 DB로 갱신
        }
        //3. 수정결과 페이지로 리다이렉트
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete") //HTML에서 DeleteMapping을 지원하지않아서
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제 요청이 들어왔습니다!");

        //1. 삭제 대상을 가져온다.
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());

        //2. 대상을 삭제한다.
        if (target != null) {
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제가 완료되었습니다!"); //1회성 휘발성 데이터
        }

        //3. 결과 페이지로 리다이렉트 한다.
        return "redirect:/articles"; //정확히는 index.mustache
    }
}
