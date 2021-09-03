package com.goldenraspberryawards.worstmovie.repository;

import com.goldenraspberryawards.worstmovie.dto.ProducerAwardRangeDTO;
import com.goldenraspberryawards.worstmovie.model.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {

    public Set<Producer> findByMoviesWinnerTrue();

    @Query(nativeQuery = true)
    public List<ProducerAwardRangeDTO> findProducersAwardsRange();
}
