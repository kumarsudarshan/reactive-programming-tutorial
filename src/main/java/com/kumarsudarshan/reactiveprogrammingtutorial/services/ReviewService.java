package com.kumarsudarshan.reactiveprogrammingtutorial.services;

import com.kumarsudarshan.reactiveprogrammingtutorial.domain.Review;
import reactor.core.publisher.Flux;

import java.util.List;

public class ReviewService {
    public Flux<Review> getReviews(long bookId) {
        var reviewList = List.of(
                new Review(1, bookId, 9.1, "Good Content"),
                new Review(2, bookId, 8.6, "Worth Reading")
        );
        return Flux.fromIterable(reviewList);
    }
}
