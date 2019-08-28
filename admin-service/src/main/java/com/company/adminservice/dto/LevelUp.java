package com.company.adminservice.dto;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Objects;

public class LevelUp {
    private int id;
    @NotEmpty
    private int customerId;
    @NotEmpty
    private int points;
    @NotEmpty
    private LocalDate memberDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public LocalDate getMemberDate() {
        return memberDate;
    }

    public void setMemberDate(LocalDate memberDate) {
        this.memberDate = memberDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LevelUp levelUp = (LevelUp) o;
        return id == levelUp.id &&
                customerId == levelUp.customerId &&
                points == levelUp.points &&
                memberDate.equals(levelUp.memberDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, points, memberDate);
    }
}
