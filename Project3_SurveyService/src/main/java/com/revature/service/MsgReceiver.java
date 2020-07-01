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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Answer;
import com.revature.model.Form;
import com.revature.model.FormResponse;
import com.revature.model.Notification;
import com.revature.model.Question;
import com.revature.model.Response;
import com.revature.repo.FormRepo;

@Service
public class MsgReceiver{
	FormService formservice;
	
	@Autowired
	public void setFormService(FormService formService) {
		this.formservice = formService;
	}
	
	@RabbitListener(queues="FormResponse-Queue")
    public void recievedMessage(FormResponse formResponse) {
		int id = formResponse.getFormId();
        System.out.println("Recieved Message: "+ formResponse.toString());
        System.out.println("Recieved id of the form: "+ id);
        System.out.println("Questions: "+ formResponse.getQuestions().toString());
        Form form = formservice.getFormBySource(formResponse.getSourceId());
        // Form : id , source id
        //getFormBySourceId(id); if null make new form.
        //form
        //Form response will have retrieve form id
        Response response = new Response();
        response.setResponseId(id);
        response.setForm(form);
        response.setSubmittedResponseTs(convertStringToTimestamp(formResponse.getTimestamp()));
        response.setBatchName(formResponse.getAnswers().get(4));
        
       // Question question = new Question();
       // Answer answer = new Answer();
        for(int i = 0; i< formResponse.getQuestions().size(); i++) {
        	
        	Question question = new Question();
        	question.setQuestionId(i+1);
        	question.setQuestionString(formResponse.getQuestions().get(i));
        	question.setResponse(response);
        	
        	
        	
        	
        	
            Answer answer = new Answer();
        }
        
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
