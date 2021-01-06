package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.*;
import ro.tuc.ds2020.entities.Activity;
import ro.tuc.ds2020.entities.Doctor;
import ro.tuc.ds2020.entities.Person;

public class ActivityBuilder {

    private ActivityBuilder() {
    }

    public static ActivityDTO toActivityDTO(Activity activity) {
        return new ActivityDTO(activity.getId(), activity.getId_patient(), activity.getActivity(), activity.getStart_date(), activity.getEnd_date());
    }

    public static ActivityDetailsDTO toActivityDetailsDTO(Activity activity) {
        return new ActivityDetailsDTO(activity.getId(), activity.getId_patient(), activity.getActivity(), activity.getStart_date(), activity.getEnd_date());
    }

    public static Activity toEntity(ActivityDetailsDTO activityDetailsDTO) {
        return new Activity(activityDetailsDTO.getId_patient(),
                activityDetailsDTO.getStart_date(),
                activityDetailsDTO.getEnd_date(),
                activityDetailsDTO.getActivity());
    }
}
