package ro.tuc.ds2020.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.ActivityDTO;
import ro.tuc.ds2020.dtos.ActivityDetailsDTO;
import ro.tuc.ds2020.dtos.DoctorDTO;
import ro.tuc.ds2020.dtos.DoctorDetailsDTO;
import ro.tuc.ds2020.services.ActivityService;
import ro.tuc.ds2020.services.DoctorService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/activity")
public class ActivityController {

    private final ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping()
    public ResponseEntity<List<ActivityDTO>> getActivityes() {
        List<ActivityDTO> dtos = activityService.findActivityes();
        for (ActivityDTO dto : dtos) {
            Link personLink = linkTo(methodOn(ActivityController.class)
                    .getActivity(dto.getId())).withRel("activityDetails");
            dto.add(personLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody ActivityDetailsDTO activityDTO) {
        UUID activityID = activityService.insert(activityDTO);
        return new ResponseEntity<>(activityID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ActivityDetailsDTO> getActivity(@PathVariable("id") UUID activityId) {
        ActivityDetailsDTO dto = activityService.findActivityById(activityId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UUID> deleteActivity(@PathVariable("id") UUID activityId) {
        ActivityDetailsDTO dto2 = activityService.findActivityById(activityId);
        UUID activityID = activityService.delete(dto2);
        return new ResponseEntity<>(activityID, HttpStatus.OK);
    }

    //TODO: UPDATE per resource

}
