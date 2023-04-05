package com.mjc.school.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
public class AuthorDtoRequest {
    private long id;
    private String name;

    public AuthorDtoRequest(String name){
        this.name = name;
    }
    @Override
    public String toString() {
        return "AuthorDto{name='" + name + '\'' +
                '}';
    }
}
