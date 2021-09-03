package com.goldenraspberryawards.worstmovie.dto;

import java.util.List;

public class ProducersAwardsRangeMaxMinDTO {

    private List<ProducerAwardRangeDTO> min;

    private List<ProducerAwardRangeDTO> max;

    public ProducersAwardsRangeMaxMinDTO(){}

    public ProducersAwardsRangeMaxMinDTO(List<ProducerAwardRangeDTO> min, List<ProducerAwardRangeDTO> max) {
        this.min = min;
        this.max = max;
    }

    public List<ProducerAwardRangeDTO> getMin() {
        return min;
    }

    public List<ProducerAwardRangeDTO> getMax() {
        return max;
    }

}
