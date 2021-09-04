package com.goldenraspberryawards.worstmovie.service;

import com.goldenraspberryawards.worstmovie.dto.ProducersAwardsRangeMaxMinDTO;

public interface ProducerService {

    ProducersAwardsRangeMaxMinDTO getProducersAwardsRange();

    ProducersAwardsRangeMaxMinDTO getProducersAwardsRangeWithNativeQuery();
}
