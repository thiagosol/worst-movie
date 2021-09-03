package com.goldenraspberryawards.worstmovie.repository;

import com.goldenraspberryawards.worstmovie.model.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudioRepository extends JpaRepository<Studio, Long> {


}
