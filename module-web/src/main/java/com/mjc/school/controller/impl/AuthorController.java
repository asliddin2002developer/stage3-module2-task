package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("authorController")
public class AuthorController implements BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> {
    private final BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> model;
    private final View<AuthorDtoResponse, List<AuthorDtoResponse>> view;
    private AuthorDtoResponse author;

    @Autowired
    public AuthorController(@Qualifier("authorService") BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> model,
                            @Qualifier("authorView") View<AuthorDtoResponse, List<AuthorDtoResponse>> view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public List<AuthorDtoResponse> readAll() {
        var authors = model.readAll();
        view.displayAll(authors);
        return authors;
    }

    @Override
    public AuthorDtoResponse readById(Long id) {
        this.author = model.readById(id);
        view.display(author);
        return this.author;
    }

    @Override
    public AuthorDtoResponse create(AuthorDtoRequest createRequest) {
        this.author = model.create(createRequest);
        view.display(author);
        return this.author;
    }

    @Override
    public AuthorDtoResponse update(AuthorDtoRequest updateRequest) {
        this.author = model.update(updateRequest);
        view.display(author);
        return this.author;
    }

    @Override
    public boolean deleteById(Long id) {
        return model.deleteById(id);
    }
}
