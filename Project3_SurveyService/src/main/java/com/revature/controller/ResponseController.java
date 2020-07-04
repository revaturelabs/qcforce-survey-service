package com.revature.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entity.Answer;
import com.revature.entity.Response;
import com.revature.model.ResponseObject;
import com.revature.service.ResponseService;

@RestController
public class ResponseController {

	private ResponseService responseService;

	@Autowired
	public void setResponseService(ResponseService responseService) {
		this.responseService = responseService;
	}

	@GetMapping("/response")
	public List<Response> getAllForms() {
		return responseService.getAllResponses();
	}

	/*
	 * @GetMapping("/response/form/{formId}") public List<Response>
	 * getResponseById(@PathVariable("formId") int formId) { return (List<Response>)
	 * responseService.getResponseById(formId); }
	 */

	@GetMapping("/response/batch/{batchName}")
	public List<ResponseObject> getResponseByBatchName(@PathVariable("batchName") String batchName) {
		List<ResponseObject> ro = new ArrayList<ResponseObject>();
		List<Response> res = responseService.getByBatch(batchName);

		// Cycling through all Response objects for a particular week
		for (Response r : res) {
			String week = "";
			for (Answer ans : r.getAnswers()) {
				// If the answer relates to a batch, set the DTO's batch
				if (ans.getQuestion().getQuestionString().equals(
						"What was your most recently completed week of training? (Extended batches start with Week A, normal batches start with Week 1)")) {
					week = ans.getAnswerString();
				}
			}
			// Cycling through all the answers for each Response object
			for (Answer ans : r.getAnswers()) {
				// Create a new DTO obj
				ResponseObject tempRO = new ResponseObject();
				// Set DTO obj's week
				tempRO.setWeek(week);
				tempRO.setBatch(batchName);

				// If the answerString relates to the week then move to the next answer
				if (ans.getQuestion().getQuestionString().equals("What batch are you in?")
						|| ans.getQuestion().getQuestionString().equals(
								"What was your most recently completed week of training? (Extended batches start with Week A, normal batches start with Week 1)")) {
					continue;
				} else {
					// Set the answer that doesn't relate to week
					// tempRO.setAnswer(ans.getAnswerString());
					if (ans.getWeight() != -100) {
						tempRO.setAnswer(ans.getWeight());

						// Set the question that doesn't relate to week
						tempRO.setQuestion(ans.getQuestion().getQuestionString());
						// Prints out the tempRO's question and answer
						System.out.println("Question: " + tempRO.getQuestion() + " Answer: " + tempRO.getAnswer());
						// Add the tempRO to the list of DTO objects after each answer
						ro.add(tempRO);
					}

				}

			}

			// System.out.println(tempRO);
			// System.out.println(ro.toString());
		}
		System.out.println(ro);
		return ro;
	}

	@GetMapping("/response/week/{week}")
	public List<ResponseObject> getResponseByWeek(@PathVariable("week") String week) {

		List<ResponseObject> ro = new ArrayList<ResponseObject>();
		List<Response> res = responseService.getByWeek(week);

		// Cycling through all Response objects for a particular week
		for (Response r : res) {
			String batchName = "";
			for (Answer ans : r.getAnswers()) {
				// If the answer relates to a batch, set the DTO's batch
				if (ans.getQuestion().getQuestionString().equals("What batch are you in?")) {
					batchName = ans.getAnswerString();
				}
			}
			// Cycling through all the answers for each Response object
			for (Answer ans : r.getAnswers()) {
				// Create a new DTO obj
				ResponseObject tempRO = new ResponseObject();
				// Set DTO obj's week
				tempRO.setWeek(week);
				tempRO.setBatch(batchName);

				// If the answerString relates to the week then move to the next answer
				if (ans.getQuestion().getQuestionString().equals("What batch are you in?")
						|| ans.getQuestion().getQuestionString().equals(
								"What was your most recently completed week of training? (Extended batches start with Week A, normal batches start with Week 1)")) {
					continue;
				} else {
					// Set the answer that doesn't relate to week
					// tempRO.setAnswer(ans.getAnswerString());
					if (ans.getWeight() != -100) {
						tempRO.setAnswer(ans.getWeight());

						// Set the question that doesn't relate to week
						tempRO.setQuestion(ans.getQuestion().getQuestionString());
						// Prints out the tempRO's question and answer
						System.out.println("Question: " + tempRO.getQuestion() + " Answer: " + tempRO.getAnswer());
						// Add the tempRO to the list of DTO objects after each answer
						ro.add(tempRO);
					}

				}

			}

			// System.out.println(tempRO);
			// System.out.println(ro.toString());
		}
		System.out.println(ro);
		return ro;
	}

	@GetMapping("/response/{batchName}/{week}")
	public List<ResponseObject> getResponseByWeek(@PathVariable("batchName") String batchName,
			@PathVariable("week") String week) {
		List<ResponseObject> ro = new ArrayList<ResponseObject>();
		List<Response> res = responseService.getByWeekAndBatch(batchName, week);

		// Cycling through all Response objects for a particular batch and week
		for (Response r : res) {

			// Cycling through all the answers for each Response object
			for (Answer ans : r.getAnswers()) {
				// Create a new DTO obj
				ResponseObject tempRO = new ResponseObject();
				// Set DTO obj's batch and week
				tempRO.setBatch(batchName);
				tempRO.setWeek(week);
				// If the answerString relates to the batch or the week then move to the next
				// answer
				if (ans.getQuestion().getQuestionString().equals("What batch are you in?")
						|| ans.getQuestion().getQuestionString().equals(
								"What was your most recently completed week of training? (Extended batches start with Week A, normal batches start with Week 1)")) {
					continue;
				} else {
					// Set the answer that doesn't relate to batch or week
					// tempRO.setAnswer(ans.getAnswerString());
					if (ans.getWeight() != -100) {
						tempRO.setAnswer(ans.getWeight());

						// Set the question that doesn't relate to batch or week
						tempRO.setQuestion(ans.getQuestion().getQuestionString());
						// Prints out the tempRO's question and answer
						System.out.println("Question: " + tempRO.getQuestion() + " Answer: " + tempRO.getAnswer());
						// Add the tempRO to the list of DTO objects after each answer
						ro.add(tempRO);
					}
				}

			}

			// System.out.println(tempRO);
			// System.out.println(ro.toString());
		}
		System.out.println(ro);
		return ro;
	}

	@GetMapping("/response/all")
	public List<ResponseObject> getAllResponses() {
		List<ResponseObject> ro = new ArrayList<ResponseObject>();
		List<Response> res = responseService.getAllResponses();

		// Cycling through all Response objects for a particular batch and week
		for (Response r : res) {
			String batchName = "";
			for (Answer ans : r.getAnswers()) {
				// If the answer relates to a batch, set the DTO's batch
				if (ans.getQuestion().getQuestionString().equals("What batch are you in?")) {
					batchName = ans.getAnswerString();
				}
			}
			String week = "";
			for (Answer ans : r.getAnswers()) {
				// If the answer relates to a batch, set the DTO's batch
				if (ans.getQuestion().getQuestionString().equals(
						"What was your most recently completed week of training? (Extended batches start with Week A, normal batches start with Week 1)")) {
					week = ans.getAnswerString();
				}
			}
			// Cycling through all the answers for each Response object
			for (Answer ans : r.getAnswers()) {
				// Create a new DTO obj
				ResponseObject tempRO = new ResponseObject();
				// Set DTO obj's batch and week
				tempRO.setBatch(batchName);
				tempRO.setWeek(week);
				// If the answerString relates to the batch or the week then move to the next
				// answer
				if (ans.getQuestion().getQuestionString().equals("What batch are you in?")
						|| ans.getQuestion().getQuestionString().equals(
								"What was your most recently completed week of training? (Extended batches start with Week A, normal batches start with Week 1)")) {
					continue;
				} else {
					// Set the answer that doesn't relate to batch or week
					// tempRO.setAnswer(ans.getAnswerString());
					if (ans.getWeight() != -100) {
						tempRO.setAnswer(ans.getWeight());

						// Set the question that doesn't relate to batch or week
						tempRO.setQuestion(ans.getQuestion().getQuestionString());
						// Prints out the tempRO's question and answer
						System.out.println("Question: " + tempRO.getQuestion() + " Answer: " + tempRO.getAnswer());
						// Add the tempRO to the list of DTO objects after each answer
						ro.add(tempRO);
					}
				}

			}

			// System.out.println(tempRO);
			// System.out.println(ro.toString());
		}
		System.out.println(ro);
		return ro;

	}

}