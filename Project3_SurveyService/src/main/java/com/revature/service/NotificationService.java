package com.revature.service;

import java.util.List;

import com.revature.entity.Notification;



public interface NotificationService {
	
	public List<Notification> getAllNotification();

	public List<Notification> getNotificationByBatchName(String batchName);
	
	public void createNotification(Notification notification);

	public void updateNotification(Notification notification);

	public void deleteNotification(Notification notification);

}
