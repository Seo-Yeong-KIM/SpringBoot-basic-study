package com.example.testprojcet.data.entity;

import lombok.*;

import javax.persistence.*;

// BaseEntity를 상속받았기 때문에 entity의 create, update 정보가 자동 저장됨
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "short_url")
public class ShortUrl extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true) // 컬럼의 속성 설정 가능
    private String hash;

    @Column(nullable = false, unique = true)
    private String url;

    @Column(nullable = false, unique = true)
    private String orgUrl;

}