package com.mjc.school.controller.menumanager;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("menuManager")
public class MenuManager {
    private final BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController;
    private final BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController;
    private final InputHandler inputHandler;
    private final Menu menu;


    @Autowired
    public MenuManager(@Qualifier("authorController") BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController,
                       @Qualifier("newsController") BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController,
                       @Qualifier("inputHandler") InputHandler inputHandler, @Qualifier("menu") Menu menu) {
        this.authorController = authorController;
        this.newsController = newsController;
        this.inputHandler = inputHandler;
        this.menu = menu;
    }


    public void run() {
        long authorId;
        long newsId;
        String name;
        String title;
        String content;
        while (true) {
            menu.display();
            String choice = inputHandler.ask("");
            switch (choice) {
                case Menu.READ_ALL_AUTHORS -> authorController.readAll();

                case Menu.READ_AUTHOR_BY_ID -> {
                    authorId = inputHandler.isValidId(inputHandler.ask("Enter author ID: "));
                    authorController.readById(authorId);
                }
                case Menu.CREATE_AUTHOR -> {
                    name = inputHandler.ask("Enter author NAME: ");
                    authorController.create(new AuthorDtoRequest(name));
                }
                case Menu.UPDATE_AUTHOR -> {
                    authorId = inputHandler.isValidId(inputHandler.ask("Enter author ID: "));
                    name = inputHandler.ask("Enter author NAME: (update)");
                    authorController.update(new AuthorDtoRequest(authorId, name));
                }
                case Menu.DELETE_AUTHOR_BY_ID -> {
                    authorId = inputHandler.isValidId(inputHandler.ask("Enter author ID: "));
                    authorController.deleteById(authorId);
                }
                case Menu.READ_ALL_NEWS -> newsController.readAll();

                case Menu.READ_NEWS_BY_ID -> {
                    newsId = inputHandler.isValidId(inputHandler.ask("Enter news ID: : "));
                    newsController.readById(newsId);
                }
                case Menu.CREATE_NEWS -> {
                    title = inputHandler.ask("Enter news TITLE: ");
                    content = inputHandler.ask("Enter news CONTENT: ");
                    authorId = inputHandler.isValidId(inputHandler.ask("Enter author ID: : "));
                    newsController.create(new NewsDtoRequest(title, content, authorId));
                }
                case Menu.UPDATE_NEWS -> {
                    newsId = inputHandler.isValidId(inputHandler.ask("Enter news ID: : "));
                    title = inputHandler.ask("Enter news TITLE: ");
                    content = inputHandler.ask("Enter news CONTENT: ");
                    authorId = inputHandler.isValidId(inputHandler.ask("Enter news ID: : "));
                    newsController.update(new NewsDtoRequest(newsId, title, content, authorId));
                }
                case Menu.DELETE_NEWS_BY_ID -> {
                    newsId = inputHandler.isValidId(inputHandler.ask("Enter news ID: : "));
                    newsController.deleteById(newsId);
                }
                case Menu.EXIT -> System.exit(0);
            }
        }
    }
}