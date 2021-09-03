package com.goldenraspberryawards.worstmovie.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Studio {

    @Id
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "studios")
    private Set<Movie> movies;

    public Studio() {
    }

    public Studio(String name, Set<Movie> movies) {
        this.name = name.trim();
        this.movies = movies;
    }

    public Studio(String name) {
        this.name = name.trim();
    }

    public static Set<Studio> studioFromLineCsvFile(String lineStudioCsvFile, String delimiter) {
        var names = lineStudioCsvFile.split(delimiter);
        if (names.length > 0 && names[names.length - 1].contains(" and ")) {
            var moreOneName = names[names.length - 1].split(" and ");
            names[names.length - 1] = moreOneName[0];
            var studios = Arrays.stream(names).filter(name -> !name.trim().isEmpty()).map(Studio::new).collect(Collectors.toSet());
            studios.add(new Studio(moreOneName[1]));
            return studios;
        }
        return Arrays.stream(names).filter(name -> !name.trim().isEmpty()).map(Studio::new).collect(Collectors.toSet());
    }

    public String getName() {
        return name;
    }

    public Set<Movie> getMovies() {
        return Collections.unmodifiableSet(movies);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Studio studio = (Studio) o;
        return Objects.equals(name, studio.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
