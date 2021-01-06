package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;
import java.util.UUID;

public class MedicationPlanDTO extends RepresentationModel<MedicationDTO> {
    private UUID id_med_plan;
    private String intervals;
    private String period;

    public MedicationPlanDTO() {
    }

    public MedicationPlanDTO(UUID id, String intervals, String period) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MedicationPlanDTO that = (MedicationPlanDTO) o;
        return Objects.equals(id_med_plan, that.id_med_plan) &&
                Objects.equals(intervals, that.intervals) &&
                Objects.equals(period, that.period);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id_med_plan, intervals, period);
    }

    public String getIntervals() {
        return intervals;
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