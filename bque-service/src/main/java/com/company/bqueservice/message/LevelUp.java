package com.company.bqueservice.message;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;
import java.util.Objects;

public class LevelUp {

    private int id;
    private int customerId;
    private int points;
    // handles "yyyy-MM-dd" input just fine (note: "yyyy-M-d" format will not work)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate memberDate;

    // getters / setters

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

    // equals / hash

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

    // to string


    @Override
    public String toString() {
        return "LevelUp{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", points=" + points +
                ", memberDate=" + memberDate +
                '}';
    }
}
