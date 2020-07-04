package com.revature.service;

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

@Service
public class MsgReceiver {

	private FormResponseRepo formResponseRepo;

	private FormRepo formRepository;

	private Form form;

	private boolean init = false;

	@Autowired
	public void setFormRepository(FormRepo formRepository) {
		this.formRepository = formRepository;
	}

	@Autowired
	public void setFormResponseRepo(FormResponseRepo formResponseRepo) {
		this.formResponseRepo = formResponseRepo;
	}

	public void init() {
		form = new Form();
		form.setId(1);
		formRepository.save(form);
		System.out.println("Updated form");
	}

	@RabbitListener(queues = "FormResponse-Queue")
	public void recievedMessage(FormResponse formResponse) {
		// Map to form temporary to form 1
		System.out.println(formResponse.getFormId());
		// Map Ids
		if (!init) {
			form = new Form();
			form.setId(1);
			formRepository.save(form);
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
				weights.set(i,-100.0);
			}
			else if (questions.get(i).toLowerCase().contains("what batch are you in")) {
				formResponse.setBatch(answers.get(i).replace("/", "_"));
				weights.set(i,-100.0);
			}
			else if (answers.get(i).toLowerCase().trim().startsWith("n/a")) {
				weights.set(i,1.0);
			}
			else if (answers.get(i).toLowerCase().trim().startsWith("strongly disagree")) {
				weights.set(i,2.0);
			}
			else if (answers.get(i).toLowerCase().trim().startsWith("disagree")) {
				weights.set(i, 3.0);
			}
			else if (answers.get(i).toLowerCase().trim().startsWith("agree")) {
				weights.set(i, 4.0);
			}
			else if (answers.get(i).toLowerCase().trim().startsWith("strongly agree")) {
				weights.set(i, 5.0);
			}
			else if (answers.get(i).toLowerCase().trim().startsWith("yes")) {
				weights.set(i, 1.0);
			}
			else if (answers.get(i).toLowerCase().trim().startsWith("no")) {
				weights.set(i, 0.0);
			}
			else if(answers.get(i).toLowerCase().trim().startsWith("too slow")) {
				weights.set(i, -1.0);
			}
			else if(answers.get(i).toLowerCase().trim().startsWith("good")) {
				weights.set(i, 0.0);
			}
			else if(answers.get(i).toLowerCase().trim().startsWith("too fast")) {
				weights.set(i, 1.0);
			}
			else{
				weights.set(i, -100.0);
			}
		}
		formResponse.setAnswers(answers);
		// System.out.println(formResponse.getBatch());
		try {
			formRepository.save(form);
			formResponseRepo.save(formResponse);
			FormResponse f = formResponseRepo.findByResponseId(formResponse.getFormId());
			// System.out.println("Recovered Successfully: " + f.toString());
		} catch (Exception e) {

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
			return null;
		}
	}

}
