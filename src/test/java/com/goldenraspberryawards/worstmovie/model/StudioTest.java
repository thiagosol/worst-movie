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
public class StudioTest {

    private static final String NAME = "Producer 1";
    private static final Set<Movie> MOVIES = Set.of(new Movie(1l, 2020, "Title", new HashSet<>(), new HashSet<>(), true)) ;
    private static final String LINE_WITH_AND = "Studio 1, Studio 2 and Studio 3";
    private static final String LINE_WITHOUT_AND = "Studio 1, Studio 2";
    private static final String DELIMITER = ",";

    private Studio studio = new Studio();

    @Test
    public void createStudioTest(){
        this.studio = new Studio(NAME, MOVIES);
        assertNotNull(studio);
        assertNotNull(studio.getName());
        assertFalse(studio.getMovies().isEmpty());
    }

    @Test
    public void createStudioFromLineCsvWithoutAndTest(){
        var studios = Studio.studioFromLineCsvFile(LINE_WITHOUT_AND, DELIMITER);
        assertFalse(studios.isEmpty());
    }

    @Test
    public void createStudioFromLineCsvWithAndTest(){
        var studios = Studio.studioFromLineCsvFile(LINE_WITH_AND, DELIMITER);
        assertFalse(studios.isEmpty());
    }

}