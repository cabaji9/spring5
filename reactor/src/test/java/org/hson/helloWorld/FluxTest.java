package org.hson.helloWorld;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by Hyun Woo Son on 10/24/18
 **/
@Slf4j
public class FluxTest {

    @Test
    public void createAFlux_just() {
        Flux<String> fruitFlux = Flux.just("Apple", "banana", "coco");

        fruitFlux.subscribe(f -> log.info("here have some fruit {}", f));

        StepVerifier.create(fruitFlux).expectNext("Apple").expectNext("banana").expectNext("coco").verifyComplete();

    }

    @Test
    public void createAFlux_fromArray() {
        String[] fruits = new String[]{
                "Apple", "banana", "coco"
        };
        Flux<String> fruitFlux = Flux.fromArray(fruits);
        StepVerifier.create(fruitFlux).expectNext("Apple").expectNext("banana").expectNext("coco").verifyComplete();
    }

    @Test
    public void createAFlux_fromList() {
        List<String> fruits = Arrays.asList("Apple", "banana", "coco");

        Flux<String> fruitFlux = Flux.fromIterable(fruits);
        StepVerifier.create(fruitFlux).expectNext("Apple").expectNext("banana").expectNext("coco").verifyComplete();
    }

    @Test
    public void createAFlux_fromStream() {
        Stream<String> fruitStream = Stream.of("Apple", "banana", "coco");
        Flux<String> fruitFlux = Flux.fromStream(fruitStream);
        StepVerifier.create(fruitFlux).expectNext("Apple").expectNext("banana").expectNext("coco").verifyComplete();
    }

    @Test
    public void creatAFlux_range() {
        Flux<Integer> intervalFlux = Flux.range(1, 5);
        intervalFlux.subscribe(f -> log.info("here is the counter {}", f));

        StepVerifier.create(intervalFlux).expectNext(1).expectNext(2)
                .expectNext(3).expectNext(4).expectNext(5).verifyComplete();
    }

    @Test
    public void createAFlux_interval() {
        Flux<Long> intervalFlux = Flux.interval(Duration.ofSeconds(1)).take(5);

        intervalFlux.subscribe(f -> log.info("here is the counter {}, time {}", f, new Date()));
        StepVerifier.create(intervalFlux).expectNext(0L).
                expectNext(1L).
                expectNext(2L).
                expectNext(3L).
                expectNext(4L).verifyComplete();
    }


    @Test
    public void mergeFluxes() {
        Flux<String> characterFlux = Flux.just("Garfield", "Kojak", "Barbossa").delayElements(Duration.ofMillis(500));
        Flux<String> floodFlux = Flux.just("Lasagna", "Lollipops", "Apples").delaySubscription(Duration.ofMillis(250)).delayElements(Duration.ofMillis(500));

        Flux<String> mergedFlux = characterFlux.mergeWith(floodFlux);

        mergedFlux.subscribe(f -> log.info("here is {},time {}", f, new Date()));

        StepVerifier.create(mergedFlux).expectNext("Garfield").
                expectNext("Lasagna").
                expectNext("Kojak").
                expectNext("Lollipops").
                expectNext("Barbossa").
                expectNext("Apples").verifyComplete();
    }


    @Test
    public void zipFluxes() {
        Flux<String> characterFlux = Flux.just("Garfield", "Kojak", "Barbossa").delayElements(Duration.ofMillis(500));
        Flux<String> floodFlux = Flux.just("Lasagna", "Lollipops", "Apples").delaySubscription(Duration.ofMillis(250)).delayElements(Duration.ofMillis(500));

        Flux<Tuple2<String, String>> zippedFlux = Flux.zip(characterFlux, floodFlux);

        zippedFlux.subscribe(f -> log.info("here is {},time {}", f, new Date()));

        StepVerifier.create(zippedFlux).
                expectNextMatches(p -> p.getT1().equals("Garfield") && p.getT2().equals("Lasagna")).
                expectNextMatches(p -> p.getT1().equals("Kojak") && p.getT2().equals("Lollipops")).
                expectNextMatches(p -> p.getT1().equals("Barbossa") && p.getT2().equals("Apples"))
                .verifyComplete();
    }


    @Test
    public void zipFluxesToObject() {
        Flux<String> characterFlux = Flux.just("Garfield", "Kojak", "Barbossa").delayElements(Duration.ofMillis(500));
        Flux<String> floodFlux = Flux.just("Lasagna", "Lollipops", "Apples").delaySubscription(Duration.ofMillis(250)).delayElements(Duration.ofMillis(500));

        Flux<String> zippedFlux = Flux.zip(characterFlux, floodFlux,(c,f) -> c + " eats " + f);

        zippedFlux.subscribe(f -> log.info("here is {},time {}", f, new Date()));

        StepVerifier.create(zippedFlux).
              expectNext("Garfield eats Lasagna").
                expectNext("Kojak eats Lollipops").
                expectNext("Barbossa eats Apples")
                .verifyComplete();
    }

    @Test
    public void firstFlux(){
        Flux<String> slowFlux = Flux.just("tortoise","snail","sloth").delaySubscription(Duration.ofMillis(100));
        Flux<String> fastFlux = Flux.just("hare","cheetah","squirrel");
        Flux<String> firstFlux = Flux.first(slowFlux,fastFlux);

        StepVerifier.create(firstFlux).expectNext("hare").expectNext("cheetah").expectNext("squirrel")
                .verifyComplete();

    }

    @Test
    public void skipAFew(){

        Flux<String> skipFlux = Flux.just("one","two","three","four").skip(3);
        StepVerifier.create(skipFlux).expectNext("four").verifyComplete();
    }


    @Test
    public void skipAFewSeconds(){

        Flux<String> skipFlux = Flux.just("one","two","three","four").delayElements(Duration.ofSeconds(1))
                .skip(Duration.ofSeconds(4));
        StepVerifier.create(skipFlux).expectNext("four").verifyComplete();
    }



    @Test
    public void take(){
        Flux<String> skipFlux = Flux.just("one","two","three","four")
                .take(2);
        StepVerifier.create(skipFlux).expectNext("one").expectNext("two").verifyComplete();
    }

    @Test
    public void takeByTime(){
        Flux<String> skipFlux = Flux.just("one","two","three","four","five").delayElements(Duration.ofSeconds(1)).
                take(Duration.ofMillis(2500));
        StepVerifier.create(skipFlux).expectNext("one").expectNext("two").
                verifyComplete();
    }


    @Test
    public void filter(){
        Flux<String> skipFlux = Flux.just("one","two","three","four","five").
               filter(n -> !n.contains("h"));
        StepVerifier.create(skipFlux).expectNext("one").expectNext("two").expectNext("four").expectNext("five").
                verifyComplete();
    }


    @Test
    public void distinct(){
        Flux<String> skipFlux = Flux.just("one","two","three","four","five","five","three").
               distinct();
        StepVerifier.create(skipFlux).expectNext("one").expectNext("two").expectNext("three").expectNext("four").expectNext("five").
                verifyComplete();
    }


    @Test
    public void map(){

        Flux<Player> playerFlux = Flux.just("Michael Jordan","Scottie Pippen").map(n -> {String[] split = n.split("\\s");
        return new Player(split[0],split[1]);
        });

        playerFlux.subscribe(f -> log.info("here is {}", f));

        StepVerifier.create(playerFlux).expectNext(new Player("Michael","Jordan")).
                expectNext(new Player("Scottie","Pippen")).verifyComplete();

    }


    @Test
    public void flatMap(){

        Flux<Player> playerFlux = Flux.just("Michael Jordan","Scottie Pippen").map(n -> {String[] split = n.split("\\s");
            return new Player(split[0],split[1]);
        }).subscribeOn(Schedulers.parallel());

        playerFlux.subscribe(f -> log.info("here is {}", f));

        List<Player> playerList = Arrays.asList(new Player("Michael","Jordan"),
                new Player("Scottie","Pippen") );

        StepVerifier.create(playerFlux).expectNextMatches(p -> playerList.contains(p)).
                expectNextMatches(p -> playerList.contains(p)).verifyComplete();
    }


    @Test
    public void buffer(){

        Flux<String> fruitFlux = Flux.just("apple","orange","banana","kiwi");
        Flux<List<String>> bufferedFlux = fruitFlux.buffer(3);

        bufferedFlux.subscribe(f -> log.info("here is {}", f));

        StepVerifier.create(bufferedFlux).expectNext(Arrays.asList("apple","orange","banana")).
                expectNext(Arrays.asList("kiwi")).verifyComplete();

    }


    @Test
    public void bufferFlatMap(){

        Flux<String> fruitFlux = Flux.just("apple","orange","banana","kiwi");
        fruitFlux.buffer(3).flatMap(x -> Flux.fromIterable(x).map(y -> y.toUpperCase()).subscribeOn(Schedulers.parallel()).log()).subscribe(f -> log.info("here is {}", f));

    }

    @Test
    public void collectList(){
        Flux<String> fruitFlux = Flux.just("apple","orange","banana","kiwi");
        fruitFlux.subscribe(f -> log.info("here is {}", f));
        Mono<List<String>> fruitListMono = fruitFlux.collectList();
        StepVerifier.create(fruitListMono).expectNext(Arrays.asList("apple","orange","banana","kiwi")).verifyComplete();
    }

    @Test
    public void collectMap(){

        Flux<String> fruitFlux = Flux.just("apple","orange","banana","kiwi");

        final Counter counter = new Counter();
        counter.setI(0);
        Mono<Map<Integer,String>> fruitFluxMonoMap = fruitFlux.collectMap(fruit -> counter.obtainAndAdd()).log();

       // fruitFluxMonoMap.subscribe(f -> log.info("here is {}", f));
        StepVerifier.create(fruitFluxMonoMap).expectNextMatches(map ->
             map.size() == 4 && map.get(0).equals("apple")
                    && map.get(1).equals("orange")
                    && map.get(2).equals("banana")
                    && map.get(3).equals("kiwi")
        ).verifyComplete();


    }

    @Test
    public void all(){
        Flux<String> animalFlux = Flux.just("aadvark","elephant","koala","eagle","kangaroo");

        Mono<Boolean> hasAMono = animalFlux.all(a -> a.contains("a")).log();
        StepVerifier.create(hasAMono).expectNext(true).verifyComplete();

        Mono<Boolean> hasKMono = animalFlux.all(a -> a.contains("k")).log();
        StepVerifier.create(hasKMono).expectNext(false).verifyComplete();

    }


    @Test
    public void any(){
        Flux<String> animalFlux = Flux.just("aadvark","elephant","koala","eagle","kangaroo");

        Mono<Boolean> hasAMono = animalFlux.any(a -> a.contains("t")).log();
        StepVerifier.create(hasAMono).expectNext(true).verifyComplete();

        Mono<Boolean> hasKMono = animalFlux.any(a -> a.contains("z")).log();
        StepVerifier.create(hasKMono).expectNext(false).verifyComplete();

    }

}

@Data
class Player{

    private final String name;
    private final String lastName;

}

@Data
class Counter{

    private int i;

    public int obtainAndAdd(){
        return i++;
    }
}


