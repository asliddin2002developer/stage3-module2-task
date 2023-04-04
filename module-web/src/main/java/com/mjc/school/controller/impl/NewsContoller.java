package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("newsController")
public class NewsContoller implements BaseController<NewsDtoRequest, NewsDtoResponse, Long> {

    @Autowired
    @Qualifier("newsService")
    private BaseService<NewsDtoRequest, NewsDtoResponse, Long> model;

    @Autowired
    @Qualifier("newsView")
    private View<NewsDtoResponse, List<NewsDtoResponse>> view;
    private NewsDtoResponse news;

    @Override
    public List<NewsDtoResponse> readAll() {
        var newsList = model.readAll();
        view.displayAll(newsList);
        return newsList;
    }

    @Override
    public NewsDtoResponse readById(Long id) {
        this.news = model.readById(id);
        view.display(this.news);
        return this.news;
    }

    @Override
    public NewsDtoResponse create(NewsDtoRequest createRequest) {
        this.news = model.create(createRequest);
        view.display(this.news);
        return this.news;
    }

    @Override
    public NewsDtoResponse update(NewsDtoRequest updateRequest) {
        this.news = model.update(updateRequest);
        view.display(this.news);
        return this.news;
    }

    @Override
    public boolean deleteById(Long id) {
        return model.deleteById(id);
    }
}
