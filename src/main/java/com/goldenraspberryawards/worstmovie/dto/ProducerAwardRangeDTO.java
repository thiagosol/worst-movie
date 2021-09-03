package com.goldenraspberryawards.worstmovie.dto;

public class ProducerAwardRangeDTO {

    private String producer;

    private int interval;

    private int previousWin;

    private int followingWin;

    public ProducerAwardRangeDTO() {}
    public ProducerAwardRangeDTO(String producer, int interval, int previousWin, int followingWin) {
        this.producer = producer;
        this.interval = interval;
        this.previousWin = previousWin;
        this.followingWin = followingWin;
    }

    public String getProducer() {
        return producer;
    }

    public int getInterval() {
        return interval;
    }

    public int getPreviousWin() {
        return previousWin;
    }

    public int getFollowingWin() {
        return followingWin;
    }
}
