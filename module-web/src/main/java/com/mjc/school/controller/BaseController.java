package com.mjc.school.controller;

import com.mjc.school.controller.annotations.CommandBody;
import com.mjc.school.controller.annotations.CommandHandler;

import java.util.List;

public interface BaseController<T, R, K> {

    @CommandHandler("readAll")
    List<R> readAll();

    @CommandHandler(("readById"))
    R readById(K id);

    @CommandHandler("create")
    R create(@CommandBody T createRequest);

    @CommandHandler("update")
    R update(@CommandBody T updateRequest);

    @CommandHandler(("deleteById"))
    boolean deleteById(K id);

}
