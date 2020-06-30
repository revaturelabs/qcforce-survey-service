package com.revature.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.revature.model.Answer;
import com.revature.model.Form;
import com.revature.model.FormResponse;
import com.revature.model.Notification;
import com.revature.model.Question;
import com.revature.model.Response;

@Service
public class MsgReceiver{

	@RabbitListener(queues="FormResponse-Queue")
    public void recievedMessage(FormResponse form) {
		int id = form.getFormId();
        System.out.println("Recieved Message: "+ form.toString());
        System.out.println("Recieved id of the form: "+ id);
        System.out.println("Questions: "+ form.getQuestions().toString());
    }
	
	
	
	public static Timestamp convertStringToTimestamp(String strDate) {
	    try {
	      DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
	       // you can change format of date
	      Date date = formatter.parse(strDate);
	      Timestamp timeStampDate = new Timestamp(date.getTime());

	      return timeStampDate;
	    } catch (ParseException e) {
	      System.out.println("Exception :" + e);
	      return null;
	    }
	  }
	
//	public void recievedMessage(Notification notification) {
//        System.out.println("Recieved Message: "+ notification.toString());
//    }

}
