package com.kumarsudarshan.reactiveprogrammingtutorial.services;

import com.kumarsudarshan.reactiveprogrammingtutorial.domain.BookInfo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class BookInfoService {

    public Flux<BookInfo> getBooks() {
        var books= List.of(
                new BookInfo(1, "Book One", "Author One", "1111111"),
                new BookInfo(2, "Book Two", "Author Two", "2222222"),
                new BookInfo(3, "Book Three", "Author Three", "3333333")
        );
        return Flux.fromIterable(books);
    }

    public Mono<BookInfo> getBookById(long bookId) {
        var book =  new BookInfo(1, "Book One", "Author One", "1111111");
        return Mono.just(book);
    }

}
