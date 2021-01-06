package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;
import java.util.UUID;

public class ActivityDTO extends RepresentationModel<ActivityDTO> {
    private UUID id;
    private UUID id_patient;
    private String activity;
    private long start_date;
    private long end_date;

    public ActivityDTO() {
    }

    public ActivityDTO(UUID id, UUID id_patient, String activity, long start_date, long end_date) {
        this.id = id;
        this.id_patient = id_patient;
        this.activity = activity;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public UUID getId() {
        return id;
    }

    public ActivityDTO setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getActivity() {
        return activity;
    }

    public ActivityDTO setActivity(String activity) {
        this.activity = activity;
        return this;
    }

    public long getStart_date() {
        return start_date;
    }

    public ActivityDTO setStart_date(long start_date) {
        this.start_date = start_date;
        return this;
    }

    public long getEnd_date() {
        return end_date;
    }

    public ActivityDTO setEnd_date(long end_date) {
        this.end_date = end_date;
        return this;
    }

    public UUID getId_patient() {
        return id_patient;
    }

    public ActivityDTO setId_patient(UUID id_patient) {
        this.id_patient = id_patient;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ActivityDTO that = (ActivityDTO) o;
        return start_date == that.start_date &&
                end_date == that.end_date &&
                Objects.equals(id, that.id) &&
                Objects.equals(activity, that.activity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, activity, start_date, end_date);
    }
}
