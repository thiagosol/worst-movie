package com.goldenraspberryawards.worstmovie.command;

import com.goldenraspberryawards.worstmovie.model.Movie;
import com.goldenraspberryawards.worstmovie.model.Producer;
import com.goldenraspberryawards.worstmovie.model.Studio;
import com.goldenraspberryawards.worstmovie.repository.MovieRepository;
import com.goldenraspberryawards.worstmovie.repository.ProducerRepository;
import com.goldenraspberryawards.worstmovie.repository.StudioRepository;
import com.goldenraspberryawards.worstmovie.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class DataLoaderCommand implements CommandLineRunner {

    @Value("${goldenraspberryawards.worstmovie.loadmoviesfromfile.csv.path}")
    private String fileCsv;

    @Value("${goldenraspberryawards.worstmovie.loadmoviesfromfile.csv.delimiter}")
    private String delimiterFileCsv;

    @Value("${goldenraspberryawards.worstmovie.loadmoviesfromfile.csv.first-column-name}")
    private String firstColumnNameFileCsv;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private StudioRepository studioRepository;

    @Autowired
    private ProducerRepository producerRepository;

    @Override
    public void run(String... args) throws Exception {
        insertMoviesIntoDatabaseFromCsvFile();
    }

    private void insertMoviesIntoDatabaseFromCsvFile() throws IOException {
        var movies = FileUtil.readFileFromPath(fileCsv)
                .stream()
                .filter(line -> !line.toUpperCase().startsWith(firstColumnNameFileCsv.toUpperCase()))
                .map(line -> Movie.movieFromLineCsvFile(line, delimiterFileCsv))
                .collect(Collectors.toList());

        var producers = movies.stream().map(Movie::getProducers).reduce(new HashSet<Producer>(), (acc, element) -> {
            acc.addAll(element);
            return acc;
        });

        var studios = movies.stream().map(Movie::getStudios).reduce(new HashSet<Studio>(), (acc, element) -> {
            acc.addAll(element);
            return acc;
        });

        studioRepository.saveAll(studios);
        producerRepository.saveAll(producers);
        movieRepository.saveAll(movies);
    }
}
