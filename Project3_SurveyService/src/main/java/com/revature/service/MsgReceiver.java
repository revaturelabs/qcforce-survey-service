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
	
	private FormService formService;
	private AnswerService answerService;
	private QuestionService questionService;
	private ResponseService responseService;
	
	@Autowired
	public void setFormService(FormService formService) {
		this.formService = formService;
	}
	
	@Autowired
	public void setAnswerService(AnswerService answerService) {
		this.answerService = answerService;
	}

	@Autowired
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	@Autowired
	public void setResponseService(ResponseService responseService) {
		this.responseService = responseService;
	}


	@RabbitListener(queues="FormResponse-Queue")
    public void recievedMessage(FormResponse formResponse) {
		
        System.out.println("Recieved Message: "+ formResponse.toString());
        System.out.println("Recieved id of the form: "+ formResponse.getFormId());
        System.out.println("Questions: "+ formResponse.getQuestions().toString());
        System.out.println("Answers: "+ formResponse.getAnswers().toString());
        
        //Get the form by its source id
        Form form = formService.getFormBySource(formResponse.getSourceId());
        //Instantiate a new response
        Response response = new Response();
        
        //Set the response's id
        int id = formResponse.getFormId();
        response.setResponseId(id);
        //Set the response's form
        response.setForm(form);
        //set the response's timestamp
        response.setSubmittedResponseTs(convertStringToTimestamp(formResponse.getTimestamp()));
        
        // Set the batch of the response
        int batchIndex = formResponse.getQuestions().indexOf("What batch are you in?");
        response.setBatchName(formResponse.getAnswers().get(batchIndex));
        
        // Set the week of the response
        int weekIndex = formResponse.getQuestions().indexOf("What was your most recently completed week of training? (Extended batches start with Week A, normal batches start with Week 1)");
        response.setWeek((formResponse.getAnswers().get(weekIndex)));
        
        //Persist response
        responseService.createResponse(response);
        
        //Add response to a list of responses
        List<Response> responseList = new ArrayList<Response>();
        responseList.add(response);
        
        //Instantiate a list of questions and answers
        List<Question> questionList = new ArrayList<Question>();
        List<Answer> answerList = new ArrayList<Answer>();
        
        //Cycling the the questions/answers
        for(int i = 0; i< formResponse.getQuestions().size(); i++) {
        	//Create new question
        	Question question = new Question();
        	//Create new answer
        	Answer answer = new Answer();
        	
        	//Set the fields for the question
        	//question.setQuestionId(i+1);
        	question.setQuestionString(formResponse.getQuestions().get(i));
        	question.setForm(form);
        	//Persist the question
        	questionService.createQuestion(question);
        	
        	//Add question to a list of questions
        	questionList.add(question);
        
        	
        	/*
        	 * POSSIBLY ADD CODE RELATING TO OLD QUESTIONS (LATER)
        	 */
        	
        	//Set the fields for the answer
        	//answer.setAnswerId(i+1);
        	answer.setQuestion(question);
        	answer.setResponse(response);
        	answer.setAnswerString(formResponse.getAnswers().get(i));
        	//Persist the answer
        	answerService.createAnswer(answer);
        	
        	//Add answer to a list of answers
        	answerList.add(answer);
        	
        	//Set the question's list of answers
        	question.setAnswers(answerList);
        	//Set the answer's question
        	answer.setQuestion(question);
        	//Update the question
        	questionService.updateQuestion(question);
        	//Update the answer
        	answerService.updateAnswer(answer);
        	
        }
        //Set the form's list of questions
        form.setQuestions(questionList);
        //Set the form's list of response
        form.setResponses(responseList);
        //Set the response's list of answers
        response.setAnswers(answerList);
       
        //Update the form
        formService.updateForm(form);
        //Update the response
        responseService.updateResponse(response);

        
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
