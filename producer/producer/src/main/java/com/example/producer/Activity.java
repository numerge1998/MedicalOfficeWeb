package com.example.producer;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.UUID;

public class Activity  implements Serializable {

    private UUID id;
    private UUID id_patient;
    private long start_date;
    private long end_date;
    private String activity;

    public Activity() {
    }

    public Activity(@JsonProperty("id")UUID id_patient,
                    @JsonProperty("start") long start_date,
                    @JsonProperty("end") long end_date,
                    @JsonProperty("activity") String activity) {
        this.id_patient = id_patient;
        this.start_date = start_date;
        this.end_date = end_date;
        this.activity = activity;
    }

    public UUID getId_patient() {
        return id_patient;
    }

    public Activity setId_patient(UUID id_patient) {
        this.id_patient = id_patient;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public Activity setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getActivity() {
        return activity;
    }

    public Activity setActivity(String activity) {
        this.activity = activity;
        return this;
    }

    public long getStart_date() {
        return start_date;
    }

    public Activity setStart_date(long start_date) {
        this.start_date = start_date;
        return this;
    }

    public long getEnd_date() {
        return end_date;
    }

    public Activity setEnd_date(long end_date) {
        this.end_date = end_date;
        return this;
    }
}
