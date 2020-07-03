package com.revature.listener;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
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

	@RabbitListener(queues = "FormResponse-Queue")
	public void recievedMessage(FormResponse formResponse) {

		try {
			System.out.println("Recieved Message: " + formResponse.toString());
			System.out.println("Recieved id of the form: " + formResponse.getFormId());
			System.out.println("Questions: " + formResponse.getQuestions().toString());
			System.out.println("Answers: " + formResponse.getAnswers().toString());

			// Get the form by its source id
			Form form = formService.getFormBySource(formResponse.getSourceId());
			// Instantiate a new response
			Response response = new Response();

			// Set the response's id
			response.setResponseId(formResponse.getFormId());
			// Set the response's form
			response.setForm(form);
			// set the response's timestamp
			response.setSubmittedResponseTs(convertStringToTimestamp(formResponse.getTimestamp()));

			// Persist response
			responseService.saveResponse(response);

			// Add response to a list of responses
			form.addResponse(response);

			// Cycling the the questions/answers
			for (int i = 0; i < formResponse.getQuestions().size(); i++) {
				// Create new question
				Question question = new Question();
				// Create new answer
				Answer answer = new Answer();

				// Set the fields for the question
				// question.setQuestionId(i+1);
				question.setQuestionString(formResponse.getQuestions().get(i));
				question.setForm(form);
				// Persist the question
				questionService.createQuestion(question);

				/*
				 * POSSIBLY ADD CODE RELATING TO OLD QUESTIONS (LATER)
				 */

				// Set the fields for the answer
				// answer.setAnswerId(i+1);
				answer.setQuestion(question);
				answer.setResponse(response);
				answer.setAnswerString(formResponse.getAnswers().get(i));
				// Persist the answer
				answerService.createAnswer(answer);

				// Set the question's list of answers
				question.addAnswers(answer);
				// Set the answer's question
				answer.setQuestion(question);
				// Update the question
				questionService.updateQuestion(question);
				// Update the answer
				answerService.updateAnswer(answer);

				// Set the form's list of questions
				form.addQuestion(question);
				// Set the response's list of answers
				response.addAnswers(answer);
			}

			// Update the form
			formService.updateForm(form);
			// Update the response
			responseService.updateResponse(response);
		} catch (Exception e) {
			e.printStackTrace();
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
