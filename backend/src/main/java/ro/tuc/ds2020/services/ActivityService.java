package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.*;
import ro.tuc.ds2020.dtos.builders.ActivityBuilder;
import ro.tuc.ds2020.dtos.builders.DoctorBuilder;
import ro.tuc.ds2020.entities.Activity;
import ro.tuc.ds2020.entities.Doctor;
import ro.tuc.ds2020.repositories.ActivityRepository;
import ro.tuc.ds2020.repositories.DoctorRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ActivityService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityService.class);
    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public List<ActivityDTO> findActivityes() {
        List<Activity> activityList = activityRepository.findAll();
        return activityList.stream()
                .map(ActivityBuilder::toActivityDTO)
                .collect(Collectors.toList());
    }

    public ActivityDetailsDTO findActivityById(UUID id) {
        Optional<Activity> prosumerOptional = activityRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Activity with id {} was not found in db", id);
            throw new ResourceNotFoundException(Activity.class.getSimpleName() + " with id: " + id);
        }
        return ActivityBuilder.toActivityDetailsDTO(prosumerOptional.get());
    }

    public UUID insert(ActivityDetailsDTO activityDTO) {
        Activity activity = ActivityBuilder.toEntity(activityDTO);
        activity = activityRepository.save(activity);
        LOGGER.debug("Activity with id {} was inserted in db", activity.getId());
        return activity.getId();
    }

    public UUID delete(ActivityDetailsDTO activityDTO) {
        activityRepository.deleteById(activityDTO.getId());
        LOGGER.debug("Doctor plan with id {} was deleted from db", activityDTO.getId());
        return activityDTO.getId();
    }

}
