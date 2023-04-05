package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.exception.ValidatorException;

import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;

import com.mjc.school.service.mapper.AuthorMapper;
import com.mjc.school.service.validation.impl.AuthorValidator;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("authorService")
public class AuthorService implements BaseService<AuthorDtoRequest, AuthorDtoResponse, Long > {

        @Autowired()
        @Qualifier("authorRepository")
        private BaseRepository<AuthorModel, Long> authorRepository;
        @Autowired
        private AuthorValidator errorValidator;
        private final AuthorMapper mapper = Mappers.getMapper(AuthorMapper.class);

    @Override
    public List<AuthorDtoResponse> readAll() {
        var authorList = authorRepository.readAll();
        return authorList.stream()
                .map(mapper::modelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorDtoResponse readById(Long id) {
        return mapper.modelToDto(authorRepository.readById(id));
    }

    @Override
    public AuthorDtoResponse create(AuthorDtoRequest createRequest) {
        //validate
        if (errorValidator.isValidParams(createRequest)) {
            AuthorModel author = mapper.dtoToModel(createRequest);
            authorRepository.create(author);
            return mapper.modelToDto(author);
        }
        throw new ValidatorException("Author length should be 3-15 characters");

    }

    @Override
    public AuthorDtoResponse update(AuthorDtoRequest updateRequest) {
        AuthorModel author = authorRepository.update(mapper.dtoToModelUpdate(updateRequest));
        return mapper.modelToDto(author);
    }
    @Override
    public boolean deleteById(Long id) {
        return authorRepository.deleteById(id);
    }


}
