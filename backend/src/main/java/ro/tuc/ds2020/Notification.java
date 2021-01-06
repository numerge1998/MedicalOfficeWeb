package ro.tuc.ds2020;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class Notification {
    private final SimpMessageSendingOperations mess;

    @Autowired
    public Notification (SimpMessageSendingOperations mess){
        this.mess = mess;
    }

    public void sendMessage(Message message){
        mess.convertAndSend("/topic/tracker", message);
    }
}
