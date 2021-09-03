package com.goldenraspberryawards.worstmovie.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Collections;
import java.util.Set;

import static com.goldenraspberryawards.worstmovie.enums.MovieAttributesPositionCsvEnum.PRODUCERS;
import static com.goldenraspberryawards.worstmovie.enums.MovieAttributesPositionCsvEnum.STUDIOS;
import static com.goldenraspberryawards.worstmovie.enums.MovieAttributesPositionCsvEnum.TITLE;
import static com.goldenraspberryawards.worstmovie.enums.MovieAttributesPositionCsvEnum.WINNER;
import static com.goldenraspberryawards.worstmovie.enums.MovieAttributesPositionCsvEnum.YEAR;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int year;

    private String title;

    @ManyToMany
    @JoinTable(
            name = "movie_studio",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "studio_id")}
    )
    private Set<Studio> studios;

    @ManyToMany
    @JoinTable(
            name = "movie_producer",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "producer_id")}
    )
    private Set<Producer> producers;

    private boolean winner;

    public Movie() {
    }

    public Movie(Long id, int year, String title, Set<Studio> studios, Set<Producer> producers, boolean winner) {
        this.id = id;
        this.year = year;
        this.title = title;
        this.studios = studios;
        this.producers = producers;
        this.winner = winner;
    }

    private Movie(String lineMovieCsvFile, String delimiter) {
        var attributes = lineMovieCsvFile.split(delimiter);
        if (attributes.length > YEAR.getPosition()) this.year = Integer.parseInt(attributes[YEAR.getPosition()]);
        if (attributes.length > TITLE.getPosition()) this.title = attributes[TITLE.getPosition()];
        if (attributes.length > STUDIOS.getPosition())
            setStudiosFromLineCsvFile(attributes[STUDIOS.getPosition()], STUDIOS.getDelimiter());
        if (attributes.length > PRODUCERS.getPosition())
            setProducersFromLineCsvFile(attributes[PRODUCERS.getPosition()], PRODUCERS.getDelimiter());
        if (attributes.length > WINNER.getPosition())
            this.winner = attributes[WINNER.getPosition()].equalsIgnoreCase("YES");
    }

    private void setStudiosFromLineCsvFile(String lineStudioCsvFile, String delimiter) {
        this.studios = Studio.studioFromLineCsvFile(lineStudioCsvFile, ",");
    }

    private void setProducersFromLineCsvFile(String lineProducerCsvFile, String delimiter) {
        this.producers = Producer.producerFromLineCsvFile(lineProducerCsvFile, ",");
    }

    public static Movie movieFromLineCsvFile(String lineMovieCsvFile, String delimiter) {
        return new Movie(lineMovieCsvFile, delimiter);
    }

    public Long getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }

    public boolean isWinner() {
        return winner;
    }

    public Set<Producer> getProducers() {
        return Collections.unmodifiableSet(producers);
    }

    public Set<Studio> getStudios() {
        return Collections.unmodifiableSet(studios);
    }
}
