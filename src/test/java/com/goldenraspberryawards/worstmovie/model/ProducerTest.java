package com.goldenraspberryawards.worstmovie.model;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProducerTest {

    private static final String NAME = "Producer 1";
    private static final Set<Movie> MOVIES = Set.of(new Movie(1l, 2020, "Title", new HashSet<>(), new HashSet<>(), true)) ;
    private static final String LINE_WITH_AND = "Producer 1, Producer 2 and Producer 3";
    private static final String LINE_WITHOUT_AND = "Producer 1, Producer 2";
    private static final String DELIMITER = ",";

    private Producer producer = new Producer();

    @Test
    public void createProducerTest(){
        this.producer = new Producer(NAME, MOVIES);
        assertNotNull(producer);
        assertNotNull(producer.getName());
        assertFalse(producer.getWinningMovies().isEmpty());
        assertFalse(producer.getMovies().isEmpty());
    }

    @Test
    public void createProducerFromLineCsvWithoutAndTest(){
        var producers = Producer.producerFromLineCsvFile(LINE_WITHOUT_AND, DELIMITER);
        assertFalse(producers.isEmpty());
    }

    @Test
    public void createProducerFromLineCsvWithAndTest(){
        var producers = Producer.producerFromLineCsvFile(LINE_WITH_AND, DELIMITER);
        assertFalse(producers.isEmpty());
    }

}