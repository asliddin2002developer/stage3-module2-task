package com.mjc.school.menumanager;

import org.springframework.stereotype.Component;

@Component("menu")
public class Menu {
    public static final String READ_ALL_AUTHORS = "1";
    public static final String READ_AUTHOR_BY_ID = "2";
    public static final String CREATE_AUTHOR = "3";
    public static final String UPDATE_AUTHOR = "4";
    public static final String DELETE_AUTHOR_BY_ID = "5";
    public static final String READ_ALL_NEWS = "6";
    public static final String READ_NEWS_BY_ID = "7";
    public static final String CREATE_NEWS = "8";
    public static final String UPDATE_NEWS = "9";
    public static final String DELETE_NEWS_BY_ID = "10";
    public static final String EXIT = "0";

    public void display() {
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