package ro.tuc.ds2020.dtos;

import ro.tuc.ds2020.dtos.validators.annotation.AgeLimit;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

public class ActivityDetailsDTO {

    private UUID id;
    @NotNull
    private UUID id_patient;
    @NotNull
    private String activity;
    @NotNull
    private long start_date;
    @NotNull
    private long end_date;


    public ActivityDetailsDTO() {
    }

    public ActivityDetailsDTO(UUID id_patient, String activity, long start_date, long end_date) {
        this.id_patient = id_patient;
        this.activity = activity;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public ActivityDetailsDTO(UUID id, UUID id_patient, String activity, long start_date, long end_date) {
        this.id = id;
        this.id_patient = id_patient;
        this.activity = activity;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public UUID getId() {
        return id;
    }

    public ActivityDetailsDTO setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getActivity() {
        return activity;
    }

    public ActivityDetailsDTO setActivity(String activity) {
        this.activity = activity;
        return this;
    }

    public long getStart_date() {
        return start_date;
    }

    public ActivityDetailsDTO setStart_date(long start_date) {
        this.start_date = start_date;
        return this;
    }

    public UUID getId_patient() {
        return id_patient;
    }

    public ActivityDetailsDTO setId_patient(UUID id_patient) {
        this.id_patient = id_patient;
        return this;
    }

    public long getEnd_date() {
        return end_date;
    }

    public ActivityDetailsDTO setEnd_date(long end_date) {
        this.end_date = end_date;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityDetailsDTO that = (ActivityDetailsDTO) o;
        return start_date == that.start_date &&
                end_date == that.end_date &&
                Objects.equals(id, that.id) &&
                Objects.equals(activity, that.activity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, activity, start_date, end_date);
    }
}
