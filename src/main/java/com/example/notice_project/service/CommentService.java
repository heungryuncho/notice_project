package com.example.notice_project.service;

import com.example.notice_project.dto.CommentDto;
import com.example.notice_project.entity.Comment;
import com.example.notice_project.repository.ArticleRepository;
import com.example.notice_project.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired private CommentRepository commentRepository;
    @Autowired private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
        // 반환
        return commentRepository.findByArticleId(articleId) // 조회: 댓글 목록
                .stream() // 변환: 엔티티 -> DTO
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        // 게시글 조회 및 예외 발생
        

        // 댓글 엔티티 생성


        // 댓글 엔티티를 DB로 저장


        // DTO로 변경하여 반환
        return null;
    }
}
