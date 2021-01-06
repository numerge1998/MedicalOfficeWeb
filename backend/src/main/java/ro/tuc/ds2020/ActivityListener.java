package ro.tuc.ds2020;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.entities.Activity;
import ro.tuc.ds2020.repositories.ActivityRepository;

@Service
public class ActivityListener {

    @Autowired
    Notification message1;

    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityListener(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @RabbitListener(queues = "default_parser_q")
    public void consumeDefaultMessage(Activity activ) {
        //System.out.println("id: " + activ.getId_patient() + " activity: " + activ.getActivity() + " start: " + activ.getStart_date() + " end: " + activ.getEnd_date() + "");
        //log.info("Rabbit message with default config :{} {} {} {}", activ.getId_patient(), activ.getActivity(), activ.getStart_date(), activ.getEnd_date());
        if(activ.getEnd_date() - activ.getStart_date() > 25200000 && activ.getActivity().equals("Sleeping")) {
            Message mes = new Message("Sleep", activ.getActivity());
            message1.sendMessage(mes);
           // activityRepository.save(activ);
            System.out.println("id: " + activ.getId_patient() + " activity: " + activ.getActivity() + " start: " + activ.getStart_date() + " end: " + activ.getStart_date() + "");
        }
            if(activ.getEnd_date() - activ.getStart_date() > 18000000 && activ.getActivity().equals("Leaving")) {
                Message mes = new Message("Leave", activ.getActivity());
                message1.sendMessage(mes);
                System.out.println("id: " + activ.getId_patient() + " activity: " + activ.getActivity() + " start: " + activ.getStart_date() + " end: " + activ.getStart_date() + "");
            }
            if(activ.getEnd_date() - activ.getStart_date() > 1800000 && (activ.getActivity().equals("Showering") || activ.getActivity().equals("Toileting") || activ.getActivity().equals("Grooming"))) {
                Message mes = new Message("Bathroom", activ.getActivity());
                message1.sendMessage(mes);
                System.out.println("id: " + activ.getId_patient() + " activity: " + activ.getActivity() + " start: " + activ.getStart_date() + " end: " + activ.getStart_date() + "");
            }
    }
}

