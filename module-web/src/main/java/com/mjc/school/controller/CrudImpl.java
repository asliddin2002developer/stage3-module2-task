package com.mjc.school.controller;

import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component("crudImpl")
public class CrudImpl {
    @Autowired
    @Qualifier("authorController")
    private BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController;

    @Autowired
    @Qualifier("newsController")
    private BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController;

    private final Scanner scanner = new Scanner(System.in);

    public void implementCrud(){
        menu();
        String command = scanner.nextLine();
        switch(command){
            case "1":
                authorController.readAll();
                System.out.println("SUCCESS");
                break;
            case "2":
                System.out.println("Enter ID: ");
                String var1 = scanner.nextLine();
                authorController.readById(isValidId(var1));
                System.out.println("SUCCESS");
                break;
            case "3":
                System.out.print("Enter author NAME: ");
                String name = scanner.nextLine();
                authorController.create(new AuthorDtoRequest(name));
                System.out.println("SUCCESS");
                break;
            case "4":
                System.out.print("Enter author ID: ");
                long id = this.isValidId(scanner.nextLine());
                System.out.print("Enter author NAME: (update)");
                name = scanner.nextLine();
                authorController.update(new AuthorDtoRequest(id, name));
                System.out.println("SUCCESS");
                break;
            case "5":
                System.out.print("Enter author ID: ");
                id = this.isValidId(scanner.nextLine());
                authorController.deleteById(id);
                System.out.println("SUCCESS");
                break;
            case "6":
                newsController.readAll();
                System.out.println("SUCCESS");
                break;
            case "7":
                System.out.print("Enter news ID: ");
                id = this.isValidId(scanner.nextLine());
                newsController.readById(id);
                System.out.println("SUCCESS");
                break;
            case "8":
                System.out.print("Enter news TITLE: ");
                String title = scanner.nextLine();
                System.out.print("Enter news CONTENT: ");
                String content = scanner.nextLine();
                System.out.print("Enter author ID: ");
                id = this.isValidId(scanner.nextLine());
                var newsDto = new NewsDtoRequest(title, content, id);
                newsController.create(newsDto);
        }
    }

    public Long isValidId(String id){
        try{
            return Long.valueOf(id);
        }catch(Exception e){
            throw new RuntimeException();
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
