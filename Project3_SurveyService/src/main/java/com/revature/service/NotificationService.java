package com.revature.service;

import java.util.List;

import com.revature.model.Notification;



public interface NotificationService {
	
	public List<Notification> getAllNotification();

	public List<Notification> getNotificationByBatchName(String batchName);
	
	public void createResponse(Notification notification);

	public void updateResponse(Notification notification);

	public void deleteResponse(Notification notification);

}
