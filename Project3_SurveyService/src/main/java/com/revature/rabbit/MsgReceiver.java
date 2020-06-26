package com.revature.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.revature.model.Form;

@Service
public class MsgReceiver{

	@RabbitListener(queues="FormResponse-Queue")
    public void recievedMessage(Form form) {
        System.out.println("Recieved Message: "+ form.toString());
    }

}
