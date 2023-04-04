package com.mjc.school.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NewsDtoRequest {
        private Long id;
        private String title;
        private String content;
        private Long authorId;

        public NewsDtoRequest(String title, String content, Long authorId){
            this.title = title;
            this.content = content;
            this.authorId = authorId;
        }

        @Override
        public String toString() {
            return "NewsDto{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    ", authorId=" + authorId +
                    '}';
        }
}


