package ro.tuc.ds2020.dtos;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

public class MedicationPlansDetailsDTO {

    private UUID id_med_plan;
    @NotNull
    private String intervals;
    @NotNull
    private String period;

    public MedicationPlansDetailsDTO() {
    }

    public MedicationPlansDetailsDTO( String intervals, String period) {
        this.intervals = intervals;
        this.period = period;
    }

    public MedicationPlansDetailsDTO(UUID id, String intervals, String period) {
        this.id_med_plan = id;
        this.intervals = intervals;
        this.period = period;
    }

    public UUID getId() {
        return id_med_plan;
    }

    public void setId(UUID id) {
        this.id_med_plan = id;
    }

    public String getIntervals() {
        return intervals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicationPlansDetailsDTO that = (MedicationPlansDetailsDTO) o;
        return Objects.equals(id_med_plan, that.id_med_plan) &&
                Objects.equals(intervals, that.intervals) &&
                Objects.equals(period, that.period);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_med_plan, intervals, period);
    }

    public void setIntervals(String intervals) {
        this.intervals = intervals;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
