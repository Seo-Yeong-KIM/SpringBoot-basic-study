package com.example.testprojcet.data.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

// create, update 데이터를 저장하는 어노테이션을 가짐
@Getter
@Setter
@MappedSuperclass // 이 어노테이션 명시해야 상속받은 entity에서 @CreatedDate.. 등등 어노테이션 사용 가능
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @CreatedDate // entity가 생성될 때 시간 자동 저장
    @Column(updatable = false) // 처음 생성된 후로는 update x
    private LocalDateTime createdAt;

  /*
  @CreatedBy
  @Column(updatable = false)
  private String createdBy;
  */

    @LastModifiedDate // entity가 수정될 때 시간 자동 저장
    private LocalDateTime updatedAt;

  /*
  @LastModifiedBy
  private String updatedBy;
  */

}

