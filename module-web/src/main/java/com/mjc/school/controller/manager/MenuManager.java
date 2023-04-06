package com.mjc.school.controller.manager;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component("menuManager")
public class MenuManager {
    @Autowired
    @Qualifier("authorController")
    private BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController;

    @Autowired
    @Qualifier("newsController")
    private BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController;

    private final Scanner scanner = new Scanner(System.in);

    public void manageCrud(){
        menu();
        String command = scanner.nextLine();
        switch(command){
            case "1":
                authorController.readAll();
                break;
            case "2":
                long authorId = this.isValidId(ask("Enter author ID: "));
                authorController.readById(authorId);
                break;
            case "3":
                String name = ask("Enter author NAME: ");
                authorController.create(new AuthorDtoRequest(name));
                break;
            case "4":
                authorId = this.isValidId(ask("Enter author ID: "));
                name = ask("Enter author NAME: (update)");
                authorController.update(new AuthorDtoRequest(authorId, name));
                break;
            case "5":
                authorId = this.isValidId(ask("Enter author ID: "));
                authorController.deleteById(authorId);
                break;
            case "6":
                newsController.readAll();
                break;
            case "7":
                long newsId = this.isValidId(ask("Enter news ID: "));
                newsController.readById(newsId);
                break;
            case "8":
                String title = ask("Enter news TITLE: ");
                String content = ask("Enter news CONTENT: ");
                authorId = this.isValidId(ask("Enter author ID: "));
                newsController.create(new NewsDtoRequest(title, content, authorId));
                break;
            case "9":
                newsId = this.isValidId("Enter new ID: ");
                title = ask("Enter news TITLE: ");
                content = ask("Enter news CONTENT: ");
                authorId = this.isValidId(ask("Enter author ID: "));
                newsController.update(new NewsDtoRequest(newsId, title, content, authorId));
                break;
            case "10":
                newsId = this.isValidId(ask("Enter news ID: "));
                newsController.deleteById(newsId);
                break;
            case "0":
                System.exit(0);
        }
    }

    public String ask(String message){
        System.out.print(message);
        return scanner.nextLine();

    }

    public Long isValidId(String id){
        try{
            return Long.valueOf(id);
        }catch(Exception e){
            throw new RuntimeException("ID is not valid!");
        }
    }

    public void menu(){
        System.out.println("Welcome to the Main Menu");
        System.out.println("1. Read all authors");
        System.out.println("2. Read author by ID");
        System.out.println("3. Create new author");
        System.out.println("4. Update author");
        System.out.println("5. Delete author by ID");
        System.out.println("6. Read all news");
        System.out.println("7. Read news by ID");
        System.out.println("8. Create new news");
        System.out.println("9. Update news");
        System.out.println("10. Delete news by ID");
        System.out.println("0. Exit\n");
        System.out.print("Please enter your choice:  ");
    }
}
