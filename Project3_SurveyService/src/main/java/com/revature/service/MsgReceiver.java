package com.revature.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		String ans = "";
		String ques = "";
		for (int i = 0; i < formResponse.getAnswers().size(); i++) {
			ans += formResponse.getAnswers().get(i) + "::";
			ques += formResponse.getQuestions().get(i) + "::";
		}
		formResponse.setAnswersString(ans);
		formResponse.setQuestionsString(ques);
		formResponseService.save(formResponse);
		FormResponse f = formResponseService.findById(formResponse.getFormId());
		System.out.println("Recovered Successfully: " + f.toString());
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
