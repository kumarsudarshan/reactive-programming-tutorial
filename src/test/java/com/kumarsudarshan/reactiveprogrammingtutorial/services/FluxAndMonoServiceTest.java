package com.kumarsudarshan.reactiveprogrammingtutorial.services;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

class FluxAndMonoServiceTest {

    FluxAndMonoService fluxAndMonoService = new FluxAndMonoService();

    @Test
    void fruitsFlux() {
        var fruitsFlux = fluxAndMonoService.fruitsFlux();
        StepVerifier.create(fruitsFlux)
                .expectNext("mango", "banana", "apple")
                .verifyComplete();
    }

    @Test
    void fruitsMono() {
        var fruitsMono = fluxAndMonoService.fruitsMono();
        StepVerifier.create(fruitsMono)
                .expectNext("mango")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMap() {
        var fruitsFluxMap = fluxAndMonoService.fruitsFluxMap();
        StepVerifier.create(fruitsFluxMap)
                .expectNext("MANGO", "BANANA", "APPLE")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilter() {
        var fruitsFluxFilter = fluxAndMonoService.fruitsFluxFilter();
        StepVerifier.create(fruitsFluxFilter)
                .expectNext("banana")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilterMap() {
        var fruitsFluxFilterMap = fluxAndMonoService.fruitsFluxFilterMap(5);
        StepVerifier.create(fruitsFluxFilterMap)
                .expectNext("BANANA")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFlatMap() {
        var fruitsFluxFlatMap = fluxAndMonoService.fruitsFluxFlatMap();
        StepVerifier.create(fruitsFluxFlatMap)
                .expectNextCount(16)
                .verifyComplete();
    }

    @Test
        void fruitsFluxFlatMapAsync() {
            var fruitsFluxFlatMapAsync = fluxAndMonoService.fruitsFluxFlatMapAsync();
            StepVerifier.create(fruitsFluxFlatMapAsync)
                    .expectNextCount(16)
                    .verifyComplete();
    }

    @Test
    void fruitsMonoFlatMap() {
        var fruitsMonoFlatMap = fluxAndMonoService.fruitsMonoFlatMap();
        StepVerifier.create(fruitsMonoFlatMap)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void fruitsFluxConcatMapAsync() {
        var fruitsFluxConcatMapAsync = fluxAndMonoService.fruitsFluxConcatMapAsync();
        StepVerifier.create(fruitsFluxConcatMapAsync)
                .expectNextCount(16)
                .verifyComplete();
    }

    @Test
    void fruitMonoFlatMapMany() {
        var fruitMonoFlatMapMany = fluxAndMonoService.fruitMonoFlatMapMany();
        StepVerifier.create(fruitMonoFlatMapMany)
                .expectNextCount(5)
                .verifyComplete();
    }

    @Test
    void fruitsFluxTransform() {
        var fruitsFluxTransform = fluxAndMonoService.fruitsFluxTransform(5);
        StepVerifier.create(fruitsFluxTransform)
                .expectNext("banana")
                .verifyComplete();
    }

    @Test
    void fruitsFluxTransformDefaultIfEmpty() {
        var fruitsFluxTransformDefaultIfEmpty = fluxAndMonoService.fruitsFluxTransformDefaultIfEmpty(10);
        StepVerifier.create(fruitsFluxTransformDefaultIfEmpty)
                .expectNext("default")
                .verifyComplete();
    }

    @Test
    void fruitsFluxTransformSwitchIfEmpty() {
        var fruitsFluxTransformSwitchIfEmpty = fluxAndMonoService.fruitsFluxTransformSwitchIfEmpty(8);
        StepVerifier.create(fruitsFluxTransformSwitchIfEmpty)
                .expectNext("pineapple")
                .verifyComplete();
    }

    @Test
    void fruitsFluxConcat() {
        var fruitsFluxConcat = fluxAndMonoService.fruitsFluxConcat();
        StepVerifier.create(fruitsFluxConcat)
                .expectNext("mango", "banana", "orange", "lemon", "tomato")
                .verifyComplete();
    }

    @Test
    void fruitsMonoConcatWith() {
        var fruitsMonoConcatWith = fluxAndMonoService.fruitsMonoConcatWith();
        StepVerifier.create(fruitsMonoConcatWith)
                .expectNext("mango", "lemon")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMerge() {
        var fruitsFluxMerge = fluxAndMonoService.fruitsFluxMerge();
        StepVerifier.create(fruitsFluxMerge)
                .expectNext("mango", "lemon", "banana", "tomato", "orange")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMergeWith() {
        var fruitsFluxMergeWith = fluxAndMonoService.fruitsFluxMergeWith();
        StepVerifier.create(fruitsFluxMergeWith)
                .expectNext("mango", "lemon", "banana", "tomato", "orange")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMergeWithSequential() {
        var fruitsFluxMergeWithSequential = fluxAndMonoService.fruitsFluxMergeWithSequential();
        StepVerifier.create(fruitsFluxMergeWithSequential)
                .expectNext("mango", "banana", "orange", "lemon", "tomato")
                .verifyComplete();
    }

    @Test
    void fruitsFluxZip() {
        var fruitsFluxZip = fluxAndMonoService.fruitsFluxZip();
        StepVerifier.create(fruitsFluxZip)
                .expectNext("mangolemon", "bananatomato")
                .verifyComplete();

    }

    @Test
    void fruitsFluxZipWith() {
        var fruitsFluxZipWith = fluxAndMonoService.fruitsFluxZipWith();
        StepVerifier.create(fruitsFluxZipWith)
                .expectNext("mangolemon", "bananatomato")
                .verifyComplete();
    }

    @Test
    void fruitsFluxZipTuple() {
        var fruitsFluxZipTuple = fluxAndMonoService.fruitsFluxZipTuple();
        StepVerifier.create(fruitsFluxZipTuple)
                .expectNext("mangolemonmojito", "bananatomatolime soda")
                .verifyComplete();
    }

    @Test
    void fruitsMonoZipWith() {
        var fruitsMonoZipWith = fluxAndMonoService.fruitsMonoZipWith();
        StepVerifier.create(fruitsMonoZipWith)
                .expectNext("mangolemon")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilterDoOn() {
        var fruitsFluxFilterDoOn = fluxAndMonoService.fruitsFluxFilterDoOn(5);
        StepVerifier.create(fruitsFluxFilterDoOn)
                .expectNext("banana", "orange")
                .verifyComplete();
    }

    @Test
    void fruitsFluxOnErrorReturn() {
        var fruitsFluxOnErrorReturn = fluxAndMonoService.fruitsFluxOnErrorReturn();
        StepVerifier.create(fruitsFluxOnErrorReturn)
                .expectNext("mango", "banana","orange")
                .verifyComplete();
    }

    @Test
    void fruitsFluxOnErrorContinue() {
        var fruitsFluxOnErrorContinue = fluxAndMonoService.fruitsFluxOnErrorContinue();
        StepVerifier.create(fruitsFluxOnErrorContinue)
                .expectNext("BANANA","ORANGE")
                .verifyComplete();
    }

    @Test
    void fruitsFluxOnErrorMap() {
        var fruitsFluxOnErrorMap = fluxAndMonoService.fruitsFluxOnErrorMap().log();
        StepVerifier.create(fruitsFluxOnErrorMap)
                .expectNext("MANGO")
                .expectError(IllegalStateException.class)
                .verify();
    }

    @Test
    void fruitsFluxDoOnError() {
        var fruitsFluxDoOnError = fluxAndMonoService.fruitsFluxDoOnError();
        StepVerifier.create(fruitsFluxDoOnError)
                .expectNext("MANGO")
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    public void testStreamBatchesResults() {
        Flux<String> stream = Flux.just("1", "2", "3", "4", "5");
        Mono<List<Integer>> s = stream.map(str -> Integer.parseInt(str))
                .collectList();

        final AtomicInteger batchCount = new AtomicInteger();
        final AtomicInteger count = new AtomicInteger();
        s.subscribe(is -> {
            batchCount.incrementAndGet();
            for (int i : is) {
                count.addAndGet(i);
            }
        });

//        assertThat("batchCount is 3", batchCount.get(), is(1));
//        assertThat("count is 15", count.get(), is(15));
        System.out.println("jjk");
    }
}