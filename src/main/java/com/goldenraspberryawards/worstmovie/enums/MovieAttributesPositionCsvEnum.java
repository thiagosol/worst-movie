package com.goldenraspberryawards.worstmovie.enums;

public enum MovieAttributesPositionCsvEnum {
    YEAR(0),
    TITLE(1),
    STUDIOS(2, ","),
    PRODUCERS(3, ","),
    WINNER(4);

    private int position;

    private String delimiter;

    MovieAttributesPositionCsvEnum(int position) {
        this.position = position;
    }

    MovieAttributesPositionCsvEnum(int position, String delimiter) {
        this.position = position;
        this.delimiter = delimiter;
    }

    public int getPosition() {
        return position;
    }

    public String getDelimiter() {
        return delimiter;
    }
}
