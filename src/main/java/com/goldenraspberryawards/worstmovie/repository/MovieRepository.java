package com.goldenraspberryawards.worstmovie.repository;

import com.goldenraspberryawards.worstmovie.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

}
