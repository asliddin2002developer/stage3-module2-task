package com.mjc.school.repository.datasource;

import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.repository.model.impl.NewsModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Component("dataSource")
public class DataSource {
    private final List<NewsModel> newsDataList;
    private final List<AuthorModel> authorDataList;

    public DataSource(){
        authorDataList = new ArrayList<>();
        newsDataList = new ArrayList<>();
        fillAuthorsDataList();
        fillNewsDataList();
    }


    public void fillNewsDataList(){
        Random r = new Random();
        for (int i = 1; i <= 20; i++) {
            this.newsDataList.add(new NewsModel(
                    Utils.getRandomContentByFilePath("news"),
                    Utils.getRandomContentByFilePath("content"),
                    (long) r.nextInt(authorDataList.size()),
                    Utils.getRandomDate(),
                    Utils.getRandomDate()

            ));

        }
    }

    public void fillAuthorsDataList(){
        for(int i=1; i<=20; i++){
            this.authorDataList.add(new AuthorModel(
                    Utils.getRandomContentByFilePath("authors")
            ));
        }
    }

    public List<NewsModel> getNewsDataList(){
        return newsDataList;
    }
    public List<AuthorModel> getAuthorDataList(){
        return this.authorDataList;
    }

    public void addNewsToDataList(NewsModel news){
        newsDataList.add(news);
    }
    public void addAuthorToDataList(AuthorModel author){
        authorDataList.add(author);
    }



}
