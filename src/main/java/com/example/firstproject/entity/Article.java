package com.example.firstproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity // 이걸 붙여줘야 db가 해당 객체를 인식 가능
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Article {
    @Id
    //@GeneratedValue
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 ID를 자동 생성한다.
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

}
