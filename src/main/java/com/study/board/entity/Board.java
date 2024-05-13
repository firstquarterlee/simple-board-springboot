package com.study.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

// jpa가 읽어들인다.
@Entity // 엔티티는 테이블을 의미한다.
@Data // 이렇게 적으면 롬복이 컨트롤러에서 board.~~~ 쓸 수 있게된다.
public class Board {

    @Id // 프라이머리 키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 전략을 어떻게 할건지
    //identity는 mysql, 마리아디비에서 주로 사용함
    //sequence는 오라클
    //auto는 알아서지정해주는것
    private Integer id;
    private String title;
    private String content;
}
