package com.mjc.school.repository.datasource;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.Objects;
import java.util.Random;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component("utils")
public class Utils {

    public static String getRandomContentByFilePath(String fileName){
        Random random = new Random();
        int numLines = 30;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            InputStream inputStream = classLoader.getResourceAsStream(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream), UTF_8));
            String resultLine;
            int curLine=1;
            int desiredLine = random.nextInt(numLines);
            while ((resultLine=reader.readLine()) != null){
                if(curLine == desiredLine){
                    break;
                }
                curLine++;
            }
            return resultLine;

        }catch(IOException e){
            e.printStackTrace();
            return "";
        }
    }

    public static LocalDateTime getRandomDate() {
        Random random = new Random();
        int endDay = 30;
        LocalDate day = LocalDate.now().plusDays(random.nextInt(endDay));
        int hour = random.nextInt(24);
        int minute = random.nextInt(60);
        int second = random.nextInt(60);
        LocalTime time = LocalTime.of(hour, minute, second);
        return LocalDateTime.of(day, time);
    }
}
