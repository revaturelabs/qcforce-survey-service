package com.revature.mqlistener;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.revature.entity.Answer;
import com.revature.entity.Form;
import com.revature.entity.Question;
import com.revature.entity.Response;
import com.revature.model.FormResponse;
import com.revature.service.AnswerService;
import com.revature.service.FormService;
import com.revature.service.QuestionService;
import com.revature.service.ResponseService;

@Service
public class MsgReceiver {

	private FormService formService;
	private AnswerService answerService;
	private QuestionService questionService;
	private ResponseService responseService;

	public MsgReceiver(FormService formService, AnswerService answerService, QuestionService questionService,
			ResponseService responseService) {
		super();
		this.formService = formService;
		this.answerService = answerService;
		this.questionService = questionService;
		this.responseService = responseService;
	}

	@RabbitListener(queues = "FormResponse-Queue")
	public void recievedMessage(FormResponse formResponse) {
		System.out.println("Recieved id of the form: " + formResponse.getFormResponseId());

		// Get the form by its source id
		Form form = formService.getFormBySource(formResponse.getSourceId());
		// Instantiate a new response
		Response response = new Response();
		// Set the response's id
		response.setId(formResponse.getFormResponseId());
		// Set the response's form
		response.setForm(form);
		// set the response's timestamp
		response.setSubmittedResponseTs(convertStringToTimestamp(formResponse.getTimestamp()));

		// Persist response
		responseService.saveResponse(response);

		// Cycling the the questions/answers
		for (int i = 0; i < formResponse.getQuestions().size(); i++) {

			// Create new question
			Question question = new Question();
			// Create new answer
			Answer answer = new Answer();
			// Set the fields for the question

			question.setQuestionString(formResponse.getQuestions().get(i));
			question.setForm(form);
			// Persist the question

			question = questionService.getOrCreateQuestion(question);

			/*
			 * POSSIBLY ADD CODE RELATING TO OLD QUESTIONS (LATER)
			 */

			answer.setQuestion(question);
			answer.setResponse(response);
			answer.setAnswerString(formResponse.getAnswers().get(i));
			// Persist the answer
			answerService.createAnswer(answer);

		}
		System.out.println("Completed");
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

}
