package com.mjc.school.repository.model.impl;

import com.mjc.school.repository.model.BaseEntity;
import lombok.*;


import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;


@Getter
@Setter
public class NewsModel implements BaseEntity<Long> {
    private static AtomicLong count = new AtomicLong(1);
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private Long authorId;

    public NewsModel(String title, String content, Long authorId, LocalDateTime createDate, LocalDateTime lastUpdateDate){
        this.id = count.getAndIncrement();
        this.title = title;
        this.content = content;
        this.createDate =  createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.authorId = authorId;
    }

    public static long getCount() {
        return count.getAndIncrement();
    }

    @Override
    public String toString() {
        return "NewsModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", lastUpdateDate=" + lastUpdateDate +
                ", authorId=" + authorId +
                '}';
    }
}
