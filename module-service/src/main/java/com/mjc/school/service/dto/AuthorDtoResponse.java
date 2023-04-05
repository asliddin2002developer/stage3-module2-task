package com.mjc.school.service.dto;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
public class AuthorDtoResponse {

    private Long id;
    private String name;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;


    @Override
    public String toString() {
        return "AuthorDtoResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createDate=" + createDate +
                ", lastUpdateDate=" + lastUpdateDate +
                '}';
    }
}

