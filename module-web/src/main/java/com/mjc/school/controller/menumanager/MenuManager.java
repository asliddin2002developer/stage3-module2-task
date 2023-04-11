package com.mjc.school.controller.menumanager;

import com.mjc.school.controller.command.CommandDispatcher;
import com.mjc.school.controller.BaseController;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("menuManager")
public class MenuManager {
    private final BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController;
    private final BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController;
    private final CommandDispatcher commandDispatcher;
    private final InputHandler inputHandler;
    private final Menu menu;


    @Autowired
    public MenuManager(@Qualifier("authorController") BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController,
                       @Qualifier("newsController") BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController,
                       @Qualifier("commandDispatcher")CommandDispatcher commandDispatcher,
                       @Qualifier("inputHandler") InputHandler inputHandler,
                       @Qualifier("menu") Menu menu) {
        this.authorController = authorController;
        this.newsController = newsController;
        this.commandDispatcher = commandDispatcher;
        this.inputHandler = inputHandler;
        this.menu = menu;
    }


    public void run() {
        long authorId;
        long newsId;
        String name;
        String title;
        String content;
        BaseController<?, ?, ?> controller = null; // controller which is passed to commandDispatcher as an argument
        while (true) {
            menu.display();
            String choice = inputHandler.ask("");
            List<Object> commandParams = new ArrayList<>();
            switch (choice) {
                case Menu.READ_ALL_AUTHORS -> controller = authorController;

                case Menu.READ_AUTHOR_BY_ID -> {
                    controller = authorController;
                    authorId = inputHandler.isValidId(inputHandler.ask("Enter author ID: "));
                    commandParams.add(authorId);
                }
                case Menu.CREATE_AUTHOR -> {
                    controller = authorController;
                    name = inputHandler.ask("Enter author NAME: ");
                    commandParams.add(new AuthorDtoRequest(name));
                }
                case Menu.UPDATE_AUTHOR -> {
                    controller = authorController;
                    authorId = inputHandler.isValidId(inputHandler.ask("Enter author ID: "));
                    name = inputHandler.ask("Enter author NAME: (update)");
                    commandParams.add(new AuthorDtoRequest(authorId, name));
                }
                case Menu.DELETE_AUTHOR_BY_ID -> {
                    controller = authorController;
                    authorId = inputHandler.isValidId(inputHandler.ask("Enter author ID: "));
                    commandParams.add(authorId);
                }
                case Menu.READ_ALL_NEWS -> controller = newsController;

                case Menu.READ_NEWS_BY_ID -> {
                    controller = newsController;
                    newsId = inputHandler.isValidId(inputHandler.ask("Enter news ID: : "));
                    commandParams.add(newsId);
                }
                case Menu.CREATE_NEWS -> {
                    controller = newsController;
                    title = inputHandler.ask("Enter news TITLE: ");
                    content = inputHandler.ask("Enter news CONTENT: ");
                    authorId = inputHandler.isValidId(inputHandler.ask("Enter author ID: : "));
                    commandParams.add(new NewsDtoRequest(title, content, authorId));
                }
                case Menu.UPDATE_NEWS -> {
                    controller = newsController;
                    newsId = inputHandler.isValidId(inputHandler.ask("Enter news ID: : "));
                    title = inputHandler.ask("Enter news TITLE: ");
                    content = inputHandler.ask("Enter news CONTENT: ");
                    authorId = inputHandler.isValidId(inputHandler.ask("Enter author ID: : "));
                    commandParams.add(new NewsDtoRequest(newsId, title, content, authorId));
                }
                case Menu.DELETE_NEWS_BY_ID -> {
                    controller = newsController;
                    newsId = inputHandler.isValidId(inputHandler.ask("Enter news ID: : "));
                    commandParams.add(newsId);
                }
                case Menu.EXIT -> System.exit(0);
            }

            try {
                commandDispatcher.dispatch(controller, choice, commandParams);
            } catch (Exception e) {
                System.out.println("Failed to execute command: " + choice);
                e.printStackTrace();
            }
        }
    }
}

