package com.mjc.school.repository.impl;


import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.datasource.DataSource;
import com.mjc.school.repository.datasource.Utils;
import com.mjc.school.repository.exception.NotFoundException;
import com.mjc.school.repository.model.impl.NewsModel;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Getter
@Setter
@Repository("newsRepository")
public class NewsRepository implements BaseRepository<NewsModel, Long> {
    @Autowired
    private DataSource dataSource;

    @Override
    public List<NewsModel> readAll() {
        return dataSource.getNewsDataSource();
    }

    @Override
    public NewsModel readById(Long id) {
        return findById(id);
    }

    @Override
    public NewsModel create(NewsModel entity) {
        entity.setId(NewsModel.getCount());
        entity.setCreateDate(Utils.getRandomDate());
        entity.setLastUpdateDate(Utils.getRandomDate());
        dataSource.addNewsToDataSource(entity);
        return entity;
    }
    @Override
    public NewsModel update(NewsModel entity) {
        NewsModel news = findById(entity.getId());
        news.setTitle(entity.getTitle());
        news.setContent(entity.getContent());
        news.setLastUpdateDate(Utils.getRandomDate());
        news.setAuthorId(entity.getAuthorId());
        return news;
    }
    @Override
    public boolean deleteById(Long id) {
        NewsModel news = findById(id);
        dataSource.getNewsDataSource().remove(news);
        return true;
    }
    @Override
    public boolean existById(Long id) {
        try{
            findById(id);
            return true;
        }catch (NotFoundException e){
            return false;
        }
    }

    @Override
    public NewsModel findById(Long id){
        for (NewsModel news : dataSource.getNewsDataSource()){
            if (news.getId().equals(id)){
                return news;
            }
        }
        throw new NotFoundException("News with given id NOT FOUND");
    }
}
