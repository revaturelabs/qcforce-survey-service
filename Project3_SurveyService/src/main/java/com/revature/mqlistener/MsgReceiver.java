package com.revature.mqlistener;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.entity.Answer;
import com.revature.entity.Form;
import com.revature.entity.Question;
import com.revature.entity.Response;
import com.revature.model.FormResponse;
import com.revature.service.AnswerService;
import com.revature.service.FormService;
import com.revature.service.QuestionService;
import com.revature.service.ResponseService;

/**
 * @author Anastasia Miagkii, Monica Datta, Zachary Reagin, Wei Wu, Jose Canela,
 *         Andres Toledo
 *
 */
@Service
@Transactional
public class MsgReceiver {

	private FormService formService;
	private AnswerService answerService;
	private QuestionService questionService;
	private ResponseService responseService;

	/**
	 * @param formService
	 * @param answerService
	 * @param questionService
	 * @param responseService
	 */
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

		if (responseService.checkIfResponseExist(formResponse.getFormResponseId()) == false) {
			// Get the form by its source id
			Form form = formService.getFormBySource(formResponse.getSourceId());
			// Instantiate a new response
			Response response = new Response();
			// Set the response's id
			response.setId(formResponse.getFormResponseId());
			// Set the response's form (foreign key form_id in response table)
			response.setForm(form);
			// Set the response's timestamp
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

				// Set the fields for the answer
				answer.setQuestion(question);
				answer.setResponse(response);
				answer.setAnswerString(formResponse.getAnswers().get(i));
				answer.setWeight(convertWeight(formResponse.getAnswers().get(i)));

				// Persist the answer
				answerService.createAnswer(answer);

			}
			System.out.println("Completed");
		}
	}

	public static Timestamp convertStringToTimestamp(String strDate) {
		try {
			DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
			Date date = formatter.parse(strDate);
			Timestamp timeStampDate = new Timestamp(date.getTime());

			return timeStampDate;
		} catch (ParseException e) {
			System.out.println("Exception :" + e);
			return null;
		}
	}

	public double convertWeight(String answer) {
		double weight = -100.0;
		// System.out.println("Answer = " + answer);
		answer = answer.toLowerCase().trim();
		if (answer.equals("strongly disagree")) {
			weight = normalize(1.0, 1.0, 4.0);
		} else if (answer.equals("disagree")) {
			weight = normalize(2.0, 1.0, 4.0);
		} else if (answer.equals("agree")) {
			weight = normalize(3.0, 1.0, 4.0);
		} else if (answer.equals("strongly agree")) {
			weight = normalize(4.0, 1.0, 4.0);
		} else if (answer.equals("1")) {
			weight = 1.0;
		} else if (answer.equals("2")) {
			weight = 2.0;
		} else if (answer.equals("3")) {
			weight = 3.0;
		} else if (answer.equals("4")) {
			weight = 4.0;
		} else if (answer.equals("5")) {
			weight = 5.0;
		} else if (answer.equals("yes")) {
			weight = 1.0;
		} else if (answer.equals("no")) {
			weight = 0.0;
		} else if (answer.equals("too fast")) {
			weight = 1.0;
		} else if (answer.equals("good")) {
			weight = 0.0;
		} else if (answer.equals("too slow")) {
			weight = -1.0;
		}
		// System.out.println("Weight = " + weight);
		return weight;
	}

	public static double normalize(double value, double min, double max) {
		return 5 - (1 - ((value - min) / (max - min))) * 4;
	}

}
