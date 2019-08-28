package com.company.levelupservice.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class LevelUp {

    private int id;
    @Min(1)
    private int customerId;
    @Min(0)
    private int points;
    @NotNull
    @JsonSerialize(using = LocalDateSerializer.class)
    // handles "yyyy-MM-dd" input just fine (note: "yyyy-M-d" format will not work)
    @JsonDeserialize(using = LocalDateDeserializer.class)
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
