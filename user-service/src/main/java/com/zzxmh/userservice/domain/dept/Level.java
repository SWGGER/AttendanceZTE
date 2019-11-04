package com.zzxmh.userservice.domain.dept;

public class Level {
    private Integer levelId;

    private String levelName;

    private Double maxSal;

    private Double floatRate;

    private String state;

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName == null ? null : levelName.trim();
    }

    public Double getMaxSal() {
        return maxSal;
    }

    public void setMaxSal(Double maxSal) {
        this.maxSal = maxSal;
    }

    public Double getFloatRate() {
        return floatRate;
    }

    public void setFloatRate(Double floatRate) {
        this.floatRate = floatRate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}