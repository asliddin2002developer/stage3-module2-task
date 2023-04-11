package com.mjc.school.repository.model.impl;

import com.mjc.school.repository.datasource.Utils;
import com.mjc.school.repository.model.BaseEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

@Getter
@Setter
public class AuthorModel implements BaseEntity<Long> {
    private static final AtomicLong count = new AtomicLong(1);
    private Long id;
    private String name;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;

    public AuthorModel(String name){
        this.id = count.getAndIncrement();
        this.name = name;
        this.createDate = Utils.getRandomDate();
        this.lastUpdateDate = Utils.getRandomDate();
    }

    @Override
    public String toString() {
        return "AuthorModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createDate=" + createDate +
                ", lastUpdateDate=" + lastUpdateDate +
                '}';
    }
}
