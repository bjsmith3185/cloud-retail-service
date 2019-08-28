package com.company.levelupqueue.util.feign.Payload;

import com.company.levelupqueue.model.LevelUp;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ArraySerializerBase;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;
import java.util.Objects;

public class Payload {
    private int id;
    private int customerId;
    private int points;
    @JsonSerialize(using = LocalDateSerializer.class)
    // handles "yyyy-MM-dd" input just fine (note: "yyyy-M-d" format will not work)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate memberDate;

    public Payload(int id, int customerId, int points, LocalDate memberDate) {
        this.id = id;
        this.customerId = customerId;
        this.points = points;
        this.memberDate = memberDate;
    }

    public Payload() {
    }

    @Override
    public String toString() {
        return "Payload{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", points=" + points +
                ", memberDate=" + memberDate +
                '}';
    }

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
    public int hashCode() {
        return Objects.hash(id, customerId, points, memberDate);
    }
}
