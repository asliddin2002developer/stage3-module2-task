package com.mjc.school.controller;

import com.mjc.school.controller.annotations.CommandBody;
import com.mjc.school.controller.annotations.CommandHandler;
import com.mjc.school.controller.annotations.CommandParam;

import java.util.List;

public interface BaseController<T, R, K> {

    @CommandHandler
    List<R> readAll();

    @CommandHandler
    R readById(@CommandParam("id") K id);

    @CommandHandler
    R create(@CommandBody T createRequest);

    @CommandHandler
    R   update(@CommandBody T updateRequest);

    @CommandHandler
    boolean deleteById(@CommandParam("id") K id);

}
