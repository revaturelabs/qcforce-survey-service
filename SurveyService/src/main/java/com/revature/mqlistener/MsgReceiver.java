package com.revature.mqlistener;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.logger.AppLogger;
import com.revature.model.Form;
import com.revature.model.FormResponse;
import com.revature.repo.FormRepo;
import com.revature.repo.FormResponseRepo;

/**
 * Class for consuming messages from the Rabbit messaging queue.
 * 
 *  @author Anastasia Miagkii
 *  @author Andres Toledo
 *  @author Jose Canela
 *  @author Monica Datta
 *  @author Wei Wu 
 *  @author Zachary Reagin 
 */
@Service
public class MsgReceiver {

	/**
	 * An instance of FormResponseRepo.
	 */
	private FormResponseRepo formResponseRepo;

	/**
	 * An instance of FormRepo.
	 */
	private FormRepo formRepository;

	/**
	 * An instance of Form
	 */
	private Form form;

	@Autowired
	public void setFormRepository(FormRepo formRepository) {
		this.formRepository = formRepository;
	}

	@Autowired
	public void setFormResponseRepo(FormResponseRepo formResponseRepo) {
		this.formResponseRepo = formResponseRepo;
	}

	/**
	 * Consumes messages from the FormResponse queue and provides weight to the list of answers
	 * @param formResponse provides form Response
	 */
	@RabbitListener(queues = "FormResponse-Queue")
	public void recievedMessage(FormResponse formResponse) {
		AppLogger.log.info("recievedMessage: "+formResponse.getFormId());
		// Maps form responses to a form (survey template)
		form = new Form();
		//There is currently only one form
		form.setId(1);
		formRepository.save(form);
		AppLogger.log.info("recievedMessage: Updated form with Id" +form.getId());
		//Set the questions of the form object (survey template)
		form.setQuestions(formResponse.getQuestions());
		/*
		 * Map the form response consumed from the queue to a slightly different looking
		 * survey service form response object
		 */
		formResponse.setResponseId(formResponse.getFormId());
		formResponse.setFormId(1);
		List<String> questions = new ArrayList<String>(form.getQuestions());
		List<String> answers = formResponse.getAnswers();
		List<Double> weights = new ArrayList<Double>();
		//For every answer assign a weight to it
		for (int i = 0; i < answers.size(); i++) {
			if (answers.get(i).toLowerCase().trim().startsWith("week")) {
				formResponse.setWeek(answers.get(i));
				weights.add(-100.0);
			} else if (questions.get(i).toLowerCase().contains("what batch are you in")) {
				formResponse.setBatch(answers.get(i).replace("/", "_"));
				weights.add(-100.0);
			} else if (answers.get(i).toLowerCase().trim().equals("1")) {
				weights.add(1.0);
			} else if (answers.get(i).toLowerCase().trim().equals("2")) {
				weights.add(2.0);
			} else if (answers.get(i).toLowerCase().trim().equals("3")) {
				weights.add(3.0);
			} else if (answers.get(i).toLowerCase().trim().equals("4")) {
				weights.add(4.0);
			} else if (answers.get(i).toLowerCase().trim().equals("5")) {
				weights.add(5.0);
			} else if (answers.get(i).toLowerCase().trim().equals("n/a")) {
				weights.add(1.0);
			} else if (answers.get(i).toLowerCase().trim().equals("strongly disagree")) {
				weights.add(2.0);
			} else if (answers.get(i).toLowerCase().trim().equals("disagree")) {
				weights.add(3.0);
			} else if (answers.get(i).toLowerCase().trim().equals("agree")) {
				weights.add(4.0);
			} else if (answers.get(i).toLowerCase().trim().equals("strongly agree")) {
				weights.add(5.0);
			} else if (answers.get(i).toLowerCase().trim().equals("yes")) {
				weights.add(1.0);
			} else if (answers.get(i).toLowerCase().trim().equals("no")) {
				weights.add(0.0);
			} else if (answers.get(i).toLowerCase().trim().equals("too slow")) {
				weights.add(-1.0);
			} else if (answers.get(i).toLowerCase().trim().equals("good")) {
				weights.add(0.0);
			} else if (answers.get(i).toLowerCase().trim().equals("too fast")) {
				weights.add(1.0);
			} else {
				weights.add(-100.0);
			}
		}
		//Set the weights and answers for a survey service's form response objec
		formResponse.setWeights(weights);
		formResponse.setAnswers(answers);
		try {
			//Save or update the form (survey template) and form response being mapped
			formRepository.save(form);
			AppLogger.log.info("recievedMessage: Form (Id: "+form.getId()+") Saved");
			formResponseRepo.save(formResponse);
			AppLogger.log.info("recievedMessage: saved form response (Id: " + formResponse.getResponseId());

		} catch (Exception e) {
			AppLogger.log.error("receivedMessage: "+e.getMessage());
		}
	}

	/**
	 * Converts String into TimeStamp
	 * @param strDate is string of date
	 * @return timeStampDate provides time and date
	 */
	public static Timestamp convertStringToTimestamp(String strDate) {
		try {
			DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
			Date date = formatter.parse(strDate);
			Timestamp timeStampDate = new Timestamp(date.getTime());
			return timeStampDate;
		} catch (ParseException e) {
			AppLogger.log.error("convertStringToTimestamp: "+ e.getMessage());
			return null;
		}
	}

}
