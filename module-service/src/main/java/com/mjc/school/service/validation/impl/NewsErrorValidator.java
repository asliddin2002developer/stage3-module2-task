package com.mjc.school.service.validation.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.exception.NotFoundException;
import com.mjc.school.repository.exception.ValidatorException;
import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.enums.ConstantValidators;
import com.mjc.school.service.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("newsErrorValidator")
public class NewsErrorValidator implements Validator<NewsDtoRequest> {
    private final BaseRepository<AuthorModel, Long> authorRepository;

    @Autowired
    public NewsErrorValidator(@Qualifier("authorRepository") BaseRepository<AuthorModel, Long> authorRepository){
        this.authorRepository = authorRepository;
    }


    @Override
    public boolean isValidParams(NewsDtoRequest news){
        String title = news.getTitle();
        String content = news.getContent();
        Long authorId = news.getAuthorId();


        if (title.length() < 5 || news.getTitle().length() >= 30) {
            throw new ValidatorException(ConstantValidators.TITLE_LENGTH_VALIDATOR.getMessage());
        } else if (content.length() < 5 || news.getContent().length() >= 255) {
            throw new ValidatorException(ConstantValidators.CONTENT_LENGTH_VALIDATOR.getMessage());
        }else if (authorRepository.findById(authorId).getId() == null) {
            throw new NotFoundException(ConstantValidators.AUTHOR_NOT_FOUND_VALIDATOR.getMessage());
        }
        return true;
    }

}
