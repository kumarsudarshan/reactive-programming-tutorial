package com.kumarsudarshan.reactiveprogrammingtutorial.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookInfo {
    private long bookId;
    private String title;
    private String author;
    private String ISBN;
}