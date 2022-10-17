package com.osckorea.board.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity //  엔티티 어노테이션을 붙여줘야 객체를 인식한다.
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Article {

    @Id // 대표값을 지정 고유의 값
    @GeneratedValue // 자동 생성
    private Long id;

    @Column // DB에서 인식하게 컬럼을 붙여준다 세로줄.
    private String title;

    @Column
    private String content;


    public void patch(Article article) {
        if ( article.title != null)
            this.title = article.title;
        if (article.content != null)
            this.content = article.content;
        }
    }
