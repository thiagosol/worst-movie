package com.goldenraspberryawards.worstmovie.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.goldenraspberryawards.worstmovie.dto.ProducerAwardRangeDTO;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@NamedNativeQuery(name = "Producer.findProducersAwardsRange",
        query = "SELECT MP.PRODUCER_ID \"producer\",\n" +
                "(M_NEXT.YEAR - M.YEAR) \"interval\", \n" +
                "M.YEAR \"previous\", \n" +
                "M_NEXT.YEAR \"following\"\n" +
                "FROM MOVIE M\n" +
                "INNER JOIN MOVIE_PRODUCER MP ON MP.MOVIE_ID = M.ID\n" +
                "INNER JOIN MOVIE_PRODUCER MP_NEXT ON MP_NEXT.PRODUCER_ID = MP.PRODUCER_ID \n" +
                "INNER JOIN MOVIE M_NEXT ON M_NEXT.WINNER = 'TRUE' AND MP_NEXT.MOVIE_ID = M_NEXT.ID\n" +
                "WHERE M.WINNER = 'TRUE'  \n" +
                "   AND M_NEXT.YEAR = (SELECT MIN(M_MAX.YEAR) \n" +
                "           FROM MOVIE_PRODUCER MP_MAX\n" +
                "           INNER JOIN MOVIE M_MAX ON MP_MAX.MOVIE_ID =  M_MAX.ID \n" +
                "           WHERE MP_MAX.MOVIE_ID = MP_NEXT.MOVIE_ID \n" +
                "               AND M_MAX.ID = M_NEXT.ID \n" +
                "               AND M_MAX.YEAR > M.YEAR)",
        resultSetMapping = "Mapping.ProducersAwardsRangeDTO")
@SqlResultSetMapping(name = "Mapping.ProducersAwardsRangeDTO",
        classes = @ConstructorResult(targetClass = ProducerAwardRangeDTO.class,
                columns = {@ColumnResult(name = "producer"),
                        @ColumnResult(name = "interval"),
                        @ColumnResult(name = "previous"),
                        @ColumnResult(name = "following")}))
@Entity
public class Producer {

    @Id
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "producers")
    private Set<Movie> movies;

    public Producer() {
    }

    public Producer(String name) {
        this.name = name.trim();
    }

    public Producer(String name, Set<Movie> movies) {
        this.name = name.trim();
        this.movies = movies;
    }

    public static Set<Producer> producerFromLineCsvFile(String lineProducerCsvFile, String delimiter) {
        var names = lineProducerCsvFile.split(delimiter);
        if (names.length > 0 && names[names.length - 1].contains(" and ")) {
            var moreOneName = names[names.length - 1].split(" and ");
            names[names.length - 1] = moreOneName[0];
            var studios = Arrays.stream(names).filter(name -> !name.trim().isEmpty()).map(Producer::new).collect(Collectors.toSet());
            studios.add(new Producer(moreOneName[1]));
            return studios;
        }
        return Arrays.stream(names).filter(name -> !name.trim().isEmpty()).map(Producer::new).collect(Collectors.toSet());
    }

    public String getName() {
        return name;
    }

    public Set<Movie> getWinningMovies() {
        return movies.stream().filter(Movie::isWinner).collect(Collectors.toSet());
    }

    public Set<Movie> getMovies() {
        return Collections.unmodifiableSet(movies);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producer producer = (Producer) o;
        return Objects.equals(name, producer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
