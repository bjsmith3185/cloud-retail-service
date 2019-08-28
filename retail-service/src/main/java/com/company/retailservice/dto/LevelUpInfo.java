package com.company.retailservice.dto;

import java.util.Objects;

public class LevelUpInfo {

    private int currentOrderPoints;
    private int totalPoints;

    // getters / setters

    public int getCurrentOrderPoints() {
        return currentOrderPoints;
    }

    public void setCurrentOrderPoints(int currentOrderPoints) {
        this.currentOrderPoints = currentOrderPoints;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }


    // equals / hash

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LevelUpInfo that = (LevelUpInfo) o;
        return currentOrderPoints == that.currentOrderPoints &&
                totalPoints == that.totalPoints;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentOrderPoints, totalPoints);
    }


    // to string


    @Override
    public String toString() {
        return "LevelUpInfo{" +
                "currentOrderPoints=" + currentOrderPoints +
                ", totalPoints=" + totalPoints +
                '}';
    }
}
