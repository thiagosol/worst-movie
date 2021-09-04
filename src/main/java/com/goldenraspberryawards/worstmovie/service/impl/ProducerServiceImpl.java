package com.goldenraspberryawards.worstmovie.service.impl;

import com.goldenraspberryawards.worstmovie.dto.ProducerAwardRangeDTO;
import com.goldenraspberryawards.worstmovie.dto.ProducersAwardsRangeMaxMinDTO;
import com.goldenraspberryawards.worstmovie.model.Movie;
import com.goldenraspberryawards.worstmovie.model.Producer;
import com.goldenraspberryawards.worstmovie.repository.ProducerRepository;
import com.goldenraspberryawards.worstmovie.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProducerServiceImpl implements ProducerService {

    @Autowired
    private ProducerRepository producerRepository;

    public ProducersAwardsRangeMaxMinDTO getProducersAwardsRange() {
        var producersWithMoviesWin = producerRepository.findByMoviesWinnerTrue();
        var producersWithMoreThanOneMovieWin = getProducersWithMoreThanOneMovieWin(producersWithMoviesWin);

        List<ProducerAwardRangeDTO> producersAwardsRanges = new ArrayList<>();
        producersWithMoreThanOneMovieWin.forEach(producer ->
                producer.getWinningMovies().forEach(movie -> {
                    var yearMovieWin = movie.getYear();
                    var yearNextMovieWinOptional = producer.getWinningMovies().stream()
                            .map(Movie::getYear)
                            .filter(nextMovieYear -> nextMovieYear != yearMovieWin && nextMovieYear > yearMovieWin).min(Integer::compareTo);
                    yearNextMovieWinOptional.ifPresent(yearNextMovieWin -> {
                        var producerName = producer.getName();
                        var interval = yearNextMovieWin - yearMovieWin;
                        producersAwardsRanges.add(new ProducerAwardRangeDTO(producerName, interval, yearMovieWin, yearNextMovieWin));
                    });
                })
        );

        var minInterval = producersAwardsRanges.stream().map(ProducerAwardRangeDTO::getInterval).min(Integer::compare).orElse(0);
        var maxInterval = producersAwardsRanges.stream().map(ProducerAwardRangeDTO::getInterval).max(Integer::compare).orElse(0);
        var producersAwardsRangesMin = producersAwardsRanges.stream()
                .filter(interval -> interval.getInterval() == minInterval.intValue()).collect(Collectors.toList());
        var producersAwardsRangesMax = producersAwardsRanges.stream()
                .filter(interval -> interval.getInterval() == maxInterval.intValue()).collect(Collectors.toList());
        return new ProducersAwardsRangeMaxMinDTO(producersAwardsRangesMin, producersAwardsRangesMax);
    }

    public ProducersAwardsRangeMaxMinDTO getProducersAwardsRangeWithNativeQuery() {
        var producerAwardRanges = producerRepository.findProducersAwardsRange();
        var minInterval = producerAwardRanges.stream().map(ProducerAwardRangeDTO::getInterval).min(Integer::compare).orElse(0);
        var maxInterval = producerAwardRanges.stream().map(ProducerAwardRangeDTO::getInterval).max(Integer::compare).orElse(0);
        var producersAwardsRangesMin = producerAwardRanges.stream()
                .filter(interval -> interval.getInterval() == minInterval.intValue()).collect(Collectors.toList());
        var producersAwardsRangesMax = producerAwardRanges.stream()
                .filter(interval -> interval.getInterval() == maxInterval.intValue()).collect(Collectors.toList());
        return new ProducersAwardsRangeMaxMinDTO(producersAwardsRangesMin, producersAwardsRangesMax);
    }

    private Set<Producer> getProducersWithMoreThanOneMovieWin(Set<Producer> producersWithMoviesWin) {
        return producersWithMoviesWin.stream()
                .filter(producer -> producer.getWinningMovies().size() > 1).collect(Collectors.toSet());
    }
}
