package com.revature.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.FormResponse;

@Service
public class MsgReceiver {

	FormResponseService formResponseService;

	@Autowired
	public void setFormResponseService(FormResponseService formResponseService) {
		this.formResponseService = formResponseService;
	}

	@RabbitListener(queues = "FormResponse-Queue")
	public void recievedMessage(FormResponse formResponse) {
		System.out.println(formResponse.getFormId());
		List<String> questions = (List<String>) formResponse.getQuestions();
		List<String> answers = (List<String>) formResponse.getAnswers();
		for (int i = 0; i < answers.size(); i++) {
			if (answers.get(i).toLowerCase().trim().startsWith("week")) {
				formResponse.setWeek(answers.get(i));
			}
			if (questions.get(i).toLowerCase().contains("what batch are you in")) {
				formResponse.setBatch(answers.get(i).replace("/", "_"));
			}
			if (answers.get(i).toLowerCase().trim().startsWith("n/a")) {
				answers.set(i, "1.0");
			}
			if (answers.get(i).toLowerCase().trim().startsWith("strongly disagree")) {
				answers.set(i, "2.0");
			}
			if (answers.get(i).toLowerCase().trim().startsWith("disagree")) {
				answers.set(i, "3.0");
			}
			if (answers.get(i).toLowerCase().trim().startsWith("agree")) {
				answers.set(i, "4.0");
			}
			if (answers.get(i).toLowerCase().trim().startsWith("strongly agree")) {
				answers.set(i, "5.0");
			}
		}
		formResponse.setAnswers(answers);
		// System.out.println(formResponse.getAnswers().toString());
		try {
			formResponseService.save(formResponse);
			FormResponse f = formResponseService.findById(formResponse.getFormId());
			System.out.println("Recovered Successfully: " + f.toString());
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
