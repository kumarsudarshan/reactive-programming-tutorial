package com.kumarsudarshan.reactiveprogrammingtutorial.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class FluxAndMonoService {

    public Flux<String> fruitsFlux() {
        return Flux.fromIterable(List.of("mango", "banana", "apple"));
//        return Flux.fromIterable(List.of("mango", "banana", "apple")).log(); // To see logs
        /*
            00:57:04.355 [main] INFO reactor.Flux.Iterable.1 - | onSubscribe([Synchronous Fuseable] FluxIterable.IterableSubscription)
            00:57:04.363 [main] INFO reactor.Flux.Iterable.1 - | request(unbounded)
            00:57:04.366 [main] INFO reactor.Flux.Iterable.1 - | onNext(mango)
            Flux -> Fruit = mango
            00:57:04.399 [main] INFO reactor.Flux.Iterable.1 - | onNext(banana)
            Flux -> Fruit = banana
            00:57:04.400 [main] INFO reactor.Flux.Iterable.1 - | onNext(apple)
            Flux -> Fruit = apple
            00:57:04.402 [main] INFO reactor.Flux.Iterable.1 - | onComplete()
         */
    }

    public Mono<String> fruitsMono() {
        return Mono.just("mango");
//        return Mono.just("mango").log();
        /*
            01:00:13.676 [main] INFO reactor.Mono.Just.1 - | onSubscribe([Synchronous Fuseable] Operators.ScalarSubscription)
            01:00:13.676 [main] INFO reactor.Mono.Just.1 - | request(unbounded)
            01:00:13.676 [main] INFO reactor.Mono.Just.1 - | onNext(mango)
            Mono -> Fruit = mango
            01:00:13.676 [main] INFO reactor.Mono.Just.1 - | onComplete()
         */
    }

    // Use map
    public Flux<String> fruitsFluxMap() {
        return Flux.fromIterable(List.of("mango", "banana", "apple"))
                .map(String::toUpperCase);
    }

    // use filter
    public Flux<String> fruitsFluxFilter() {
        return Flux.fromIterable(List.of("mango", "banana", "apple"))
                .filter(s -> s.length() > 5);
    }

    // use filter and map
    public Flux<String> fruitsFluxFilterMap(int number) {
        return Flux.fromIterable(List.of("mango", "banana", "apple"))
                .filter(s -> s.length() > number)
                .map(String::toUpperCase);
    }

    // use flatMap
    public Flux<String> fruitsFluxFlatMap() {
        return Flux.fromIterable(List.of("mango", "banana", "apple"))
                .flatMap(s -> Flux.just(s.split("")))
                .log();
    }

    // use flatMap Async
    public Flux<String> fruitsFluxFlatMapAsync() {
        return Flux.fromIterable(List.of("mango", "banana", "apple"))
                .flatMap(s -> Flux.just(s.split(""))
                        .delayElements(Duration.ofMillis(new Random().nextInt(1000))))
                .log();
    }

    // use Mono flat map
    public Mono<List<String>> fruitsMonoFlatMap() {
        return Mono.just("mango")
                .flatMap(s -> Mono.just(List.of(s.split(""))))
                .log();
    }

    // use concatMap Async - preserves the order (flatMap does not preserve the order)
    public Flux<String> fruitsFluxConcatMapAsync() {
        return Flux.fromIterable(List.of("mango", "banana", "apple"))
                .concatMap(s -> Flux.just(s.split(""))
                        .delayElements(Duration.ofMillis(new Random().nextInt(1000))))
                .log();
    }

    // use flatMapMany - convert mono to flux
    public Flux<String> fruitMonoFlatMapMany() {
        return Mono.just("mango")
                .flatMapMany(s -> Flux.just(s.split("")))
                .log();
    }

    // use transform
    public Flux<String> fruitsFluxTransform(int number) {
        Function<Flux<String>, Flux<String>> filteredData =
                data -> data.filter(s -> s.length() > number);
        return Flux.fromIterable(List.of("mango", "banana", "apple"))
                .transform(filteredData)
                .log();
    }

    // use transform and default if empty
    public Flux<String> fruitsFluxTransformDefaultIfEmpty(int number) {
        Function<Flux<String>, Flux<String>> filteredData =
                data -> data.filter(s -> s.length() > number);
        return Flux.fromIterable(List.of("mango", "banana", "apple"))
                .transform(filteredData)
                .defaultIfEmpty("default")
                .log();
    }

    // use transform and switch if empty
    public Flux<String> fruitsFluxTransformSwitchIfEmpty(int number) {
        Function<Flux<String>, Flux<String>> filteredData =
                data -> data.filter(s -> s.length() > number);
        return Flux.fromIterable(List.of("mango", "banana", "apple"))
                .transform(filteredData)
                .switchIfEmpty(Flux.just("pineapple", "grapes")
                        .transform(filteredData))
                .log();
    }

    // use flux concat
    public Flux<String> fruitsFluxConcat() {
        var fruitsFlux = Flux.just("mango", "banana", "orange");
        var veggiesFlux = Flux.just("lemon", "tomato");

        return Flux.concat(fruitsFlux, veggiesFlux);
    }

    // use mono, flux concat - this will work with both
    public Flux<String> fruitsMonoConcatWith() {
        var fruitsMono = Mono.just("mango");
        var veggiesMono = Mono.just("lemon");

        return fruitsMono.concatWith(veggiesMono);
    }

    // use merge - this uses same as concat but asynchronously
    public Flux<String> fruitsFluxMerge() {
        var fruitsFlux = Flux.just("mango", "banana", "orange")
                .delayElements(Duration.ofMillis(50));
        var veggiesFlux = Flux.just("lemon", "tomato")
                .delayElements(Duration.ofMillis(75));
        ;

        return Flux.merge(fruitsFlux, veggiesFlux);
        // this will give output as mango, lemon, banana, tomato, orange (bcoz of delay in durations)
    }

    // use merge with
    public Flux<String> fruitsFluxMergeWith() {
        var fruitsFlux = Flux.just("mango", "banana", "orange")
                .delayElements(Duration.ofMillis(50));
        var veggiesFlux = Flux.just("lemon", "tomato")
                .delayElements(Duration.ofMillis(75));
        ;

        return fruitsFlux.mergeWith(veggiesFlux);
    }

    // use merge with sequential
    public Flux<String> fruitsFluxMergeWithSequential() {
        var fruitsFlux = Flux.just("mango", "banana", "orange")
                .delayElements(Duration.ofMillis(50));
        var veggiesFlux = Flux.just("lemon", "tomato")
                .delayElements(Duration.ofMillis(75));
        ;

        return Flux.mergeSequential(fruitsFlux, veggiesFlux);
    }

    // use flux zip
    public Flux<String> fruitsFluxZip() {
        var fruitsFlux = Flux.just("mango", "banana", "orange");
        var veggiesFlux = Flux.just("lemon", "tomato");

        return Flux.zip(fruitsFlux, veggiesFlux,
                (first, second) -> first + second).log();
    }

    // use flux zip with
    public Flux<String> fruitsFluxZipWith() {
        var fruitsFlux = Flux.just("mango", "banana", "orange");
        var veggiesFlux = Flux.just("lemon", "tomato");

        return fruitsFlux.zipWith(veggiesFlux,
                (first, second) -> first + second).log();
    }

    // use flux zip tuple
    public Flux<String> fruitsFluxZipTuple() {
        var fruitsFlux = Flux.just("mango", "banana", "orange");
        var veggiesFlux = Flux.just("lemon", "tomato");
        var drinksFlux = Flux.just("mojito", "lime soda");

        return Flux.zip(fruitsFlux, veggiesFlux, drinksFlux)
                .map(objects -> objects.getT1() + objects.getT2() + objects.getT3());
    }

    // use flux zip with mono
    public Mono<String> fruitsMonoZipWith() {
        var fruitsMono = Mono.just("mango");
        var veggiesMono = Mono.just("lemon");

        return fruitsMono.zipWith(veggiesMono,
                (first, second) -> first + second).log();
    }

    // use doOn Callbacks
    public Flux<String> fruitsFluxFilterDoOn(int number) {
        return Flux.fromIterable(List.of("mango", "banana", "orange"))
                .filter(s -> s.length() > number)
                .doOnNext(s -> {
                    System.out.println("s = " + s);
                })
                .doOnSubscribe(subscription -> {
                    System.out.println("subscription.toString() = " + subscription.toString());
                })
                .doOnComplete(() -> System.out.println("Complete"));
    }

    // use On error return
    public Flux<String> fruitsFluxOnErrorReturn() {
        return Flux.just("mango", "banana")
                .concatWith(Flux.error(
                        new RuntimeException("Exception Occured")
                ))
                .onErrorReturn("orange");
    }

    // use On error continue
    public Flux<String> fruitsFluxOnErrorContinue() {
        return Flux.just("mango", "banana", "orange")
                .map(s -> {
                    if (s.equalsIgnoreCase("mango"))
                        throw new RuntimeException("Exception Occurred: " + s);
                    return s.toUpperCase();
                })
                .onErrorContinue((e, f) -> {
                    System.out.println("e = " + e);
                    System.out.println("f = " + f);
                });
    }

    // use On error map
    public Flux<String> fruitsFluxOnErrorMap() {
        return Flux.just("mango", "banana", "orange")
                .map(s -> {
                    if (s.equalsIgnoreCase("banana"))
                        throw new RuntimeException("Exception Occurred: " + s);
                    return s.toUpperCase();
                })
                .onErrorMap(throwable -> {
                    System.out.println("Throwable = " + throwable);
                    return new IllegalStateException("From onErrorMap");
                });
    }
}
