package com.revature.controller;

import java.util.List;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.revature.model.Notification;
import com.revature.service.NotificationService;

@RestController
public class NotificationController {
	
	private NotificationService notificationService;

	@Autowired
	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}
	
	@GetMapping("/notification")
	public List<Notification> getAllForms() {
		return notificationService.getAllNotification();
	}
	
	@GetMapping("/notification/{batchName}")
	public List<Notification> getNotificationByBatchName(@PathVariable("batchName")String batchName) {
		return notificationService.getNotificationByBatchName(batchName);
	}
	
	@PostMapping("/notification")
	public String createNotification(@RequestBody Notification notification) {
		notificationService.createNotification(notification);
		return "Notification successfully created";
	}
	
	@PutMapping("/notification")
	public String updateForm(@RequestBody Notification notification) {
		notificationService.updateNotification(notification);
		return "Notification successfully updated";
	}
	
	@DeleteMapping("/notification/{id}")
	public String deleteNotification(@PathParam("notificationId") int notificationId) {
		Notification notification = new Notification();
		notification.setNotificationId(notificationId);
		notificationService.deleteNotification(notification);
		return "Form successfully deleted";
	}

}
