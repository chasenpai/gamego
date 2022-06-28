package com.gamego.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@EntityListeners(value = {AuditingEntityListener.class}) //Auditing 을 적용
@MappedSuperclass //공통 매핑이 필요할 때,  부모 클래스를 상속 받는 자식 클래스에 매핑 정보만 제공함
@Getter
@Setter
public abstract class BaseTimeEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime regDate;
    //엔티티가 생성될 때 자동으로 시간을 저장

    @LastModifiedDate
    private LocalDateTime updateDate;
    //엔티티의 값이 변경될 대 자동으로 시간을 저장

}
