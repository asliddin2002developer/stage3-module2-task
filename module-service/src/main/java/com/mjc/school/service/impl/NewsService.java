package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.mapper.NewsMapper;
import com.mjc.school.service.validation.impl.NewsValidator;

import org.mapstruct.factory.Mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("newsService")
public class NewsService implements BaseService<NewsDtoRequest, NewsDtoResponse, Long> {

    @Autowired
    @Qualifier("newsRepository")
    private BaseRepository<NewsModel, Long> newsRepository;
    @Autowired
    private NewsValidator ERROR_VALIDATOR;
    private final NewsMapper mapper = Mappers.getMapper(NewsMapper.class);


    @Override
    public List<NewsDtoResponse> readAll() {
        List<NewsModel> newsList = newsRepository.readAll();
        return newsList.stream()
                .map(mapper::modelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public NewsDtoResponse readById(Long id) {
        NewsModel newsModel = newsRepository.findById(id);
        return mapper.modelToDto(newsModel);
    }

    @Override
    public NewsDtoResponse create(NewsDtoRequest createRequest) {
        //validate
        if (ERROR_VALIDATOR.isValidParams(createRequest)) {
            NewsModel news = mapper.dtoToModel(createRequest);
            newsRepository.create(news);
            System.out.println("NEWS WAS SUCCESSFULLY CREATED");
            return mapper.modelToDto(news);
        }
        throw new RuntimeException();
    }

    @Override
    public NewsDtoResponse update(NewsDtoRequest updateRequest) {
        if (ERROR_VALIDATOR.isValidParams(updateRequest)) {
            NewsModel news = newsRepository.update(mapper.dtoToModel(updateRequest));
            System.out.println("UPDATE WAS SUCCESSFULL");
            return mapper.modelToDto(news);
        }
        throw new RuntimeException();
    }

    @Override
    public boolean deleteById(Long id) {
        if (newsRepository.existById(id)){
            return newsRepository.deleteById(id);
        }
        return false;
    }
}
