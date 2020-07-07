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

	/**
	 * A boolean variable
	 */
	private boolean init = false;

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
	 * @param formResponse
	 */
	@RabbitListener(queues = "FormResponse-Queue")
	public void recievedMessage(FormResponse formResponse) {
		// Map to form temporary to form 1
		System.out.println(formResponse.getFormId());
		// Map Ids
		if (!init) {
			form = new Form();
			form.setId(1);
			formRepository.save(form);
			init = true;
			System.out.println("Updated form");
		}
		formRepository.save(form);
		form.setQuestions(formResponse.getQuestions());
		formResponse.setResponseId(formResponse.getFormId());
		formResponse.setFormId(1);
		List<String> questions = new ArrayList<String>(form.getQuestions());
		List<String> answers = formResponse.getAnswers();
		List<Double> weights = new ArrayList<Double>();

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
		formResponse.setWeights(weights);
		formResponse.setAnswers(answers);
		// System.out.println(formResponse.getBatch());
		try {
			formRepository.save(form);
			formResponseRepo.save(formResponse);
			// System.out.println("Recovered Successfully: " + f.toString());
		} catch (Exception e) {

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
			// you can change format of date
			Date date = formatter.parse(strDate);
			Timestamp timeStampDate = new Timestamp(date.getTime());
			return timeStampDate;
		} catch (ParseException e) {
			return null;
		}
	}

}
