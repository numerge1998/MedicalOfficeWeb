package com.example.producer;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.UUID;

public class PracticalTipMessage implements Serializable{
    private UUID id;
    private Long startDate;
    private Long end_date;
    private String activity;

    public PracticalTipMessage(@JsonProperty("id") UUID id,
                               @JsonProperty("start") Long startDate,
                               @JsonProperty("end") Long end_date,
                               @JsonProperty("activity") String activity) {
        this.id = id;
        this.startDate = startDate;
        this.end_date = end_date;
        this.activity = activity;
    }

    public UUID getId() {
        return id;
    }

    public PracticalTipMessage setId(UUID id) {
        this.id= id;
        return this;
    }

    public Long getStart() {
        return startDate;
    }

    public PracticalTipMessage setStart(Long start) {
        this.startDate = start;
        return this;
    }

    public Long getEnd() {
        return end_date;
    }

    public PracticalTipMessage setEnd(Long end) {
        this.end_date = end;
        return this;
    }

    public String getActivity() {
        return activity;
    }

    public PracticalTipMessage setActivity(String activity) {
        this.activity = activity;
        return this;
    }

    @Override
    public String toString() {
        return "PracticalTipMessage{" +
                "id_patient=" + id +
                ", start_date=" + startDate +
                ", end_date=" + end_date +
                ", activity='" + activity + '\'' +
                '}';
    }
}
