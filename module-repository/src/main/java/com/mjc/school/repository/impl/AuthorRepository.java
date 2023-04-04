package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.datasource.DataSource;
import com.mjc.school.repository.datasource.Utils;
import com.mjc.school.repository.exception.NotFoundException;
import lombok.*;

import java.util.List;

import com.mjc.school.repository.model.impl.AuthorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Getter
@Setter
@Repository("authorRepository")
public class AuthorRepository implements BaseRepository<AuthorModel, Long> {

    @Autowired
    private DataSource dataSource;

    @Override
    public List<AuthorModel> readAll() {
        return dataSource.getAuthorsDataSource();
    }

    @Override
    public AuthorModel readById(Long id) {
        try {
            return findById(id);

        }
        catch(NotFoundException e){
            throw e;
        }
    }

    @Override
    public AuthorModel create(AuthorModel entity) {
        dataSource.addAuthorToDataSource(entity);
        return entity;
    }

    @Override
    public AuthorModel update(AuthorModel entity) {
        AuthorModel authorModel = findById(entity.getId());
        authorModel.setName(entity.getName());
        authorModel.setLastUpdateDate(Utils.getRandomDate());
        return authorModel;
    }

    @Override
    public boolean deleteById(Long id) {
        AuthorModel author = findById(id);
        dataSource.getAuthorsDataSource().remove(author);
        return true;
    }

    @Override
    public boolean existById(Long id) {
        try {
            findById(id);
            return true;
        }
        catch(NotFoundException e){
            System.out.println("Author Not Found");
        }
        return false;
    }

    @Override
    public AuthorModel findById(Long id) {
        for (AuthorModel author : dataSource.getAuthorsDataSource()) {
            if (author.getId().equals(id)) {
                return author;
            }
        }
        throw new NotFoundException("Author with given id NOT FOUND");
    }
}



