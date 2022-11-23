package com.example.notice_project.entity;

import lombok.AllArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity // DB가 해당 객체를 인식하게 함!
@AllArgsConstructor // 모든 필드에 있는 생성자를 만들어줌
@ToString
public class Article {

    @Id //대푯갑을 지정 eg. 주민등록번호
    @GeneratedValue // 1,2,3 자동 어노테이션!
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

}
