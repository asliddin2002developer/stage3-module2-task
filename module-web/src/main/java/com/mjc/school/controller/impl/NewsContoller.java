package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotations.CommandHandler;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller("newsController")
public class NewsContoller implements BaseController<NewsDtoRequest, NewsDtoResponse, Long> {
    private final BaseService<NewsDtoRequest, NewsDtoResponse, Long> model;
    private final View<NewsDtoResponse, List<NewsDtoResponse>> view;
    private NewsDtoResponse news;

    @Autowired
    public NewsContoller(@Qualifier("newsService") BaseService<NewsDtoRequest, NewsDtoResponse, Long> model,
                            @Qualifier("newsView") View<NewsDtoResponse, List<NewsDtoResponse>> view) {
        this.model = model;
        this.view = view;
    }
    @CommandHandler
    public List<NewsDtoResponse> readAll() {
        var newsList = model.readAll();
        view.displayAll(newsList);
        return newsList;
    }

    @CommandHandler
    public NewsDtoResponse readById(Long id) {
        this.news = model.readById(id);
        view.display(this.news);
        return this.news;
    }

    @CommandHandler
    public NewsDtoResponse create(NewsDtoRequest createRequest) {
        this.news = model.create(createRequest);
        view.display(this.news);
        return this.news;
    }

    @CommandHandler
    public NewsDtoResponse update(NewsDtoRequest updateRequest) {
        this.news = model.update(updateRequest);
        view.display(this.news);
        return this.news;
    }

    @CommandHandler
    public boolean deleteById(Long id) {
        return model.deleteById(id);
    }
}
