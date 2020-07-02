package com.revature.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.model.Notification;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Override
	public List<Notification> getAllNotification() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Notification> getNotificationByBatchName(String batchName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createNotification(Notification notification) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateNotification(Notification notification) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteNotification(Notification notification) {
		// TODO Auto-generated method stub

	}

//	private NotificationRepo notificationRepo;
//	
//	@Autowired
//	public void setNotificationRepo(NotificationRepo notificationRepo) {
//		this.notificationRepo=notificationRepo;
//	}
//	
//	@Override
//	public List<Notification> getAllNotification() {
//		return notificationRepo.findAll();
//	}
//
//	@Override
//	public List<Notification> getNotificationByBatchName(String batchName) {
//		return notificationRepo.findByBatchName(batchName); 
//	}
//
//	@Override
//	public void createNotification(Notification notification) {
//		notificationRepo.save(notification);
//
//	}
//
//	@Override
//	public void updateNotification(Notification notification) {
//		notificationRepo.findById(notification.getNotificationId()).ifPresent((existingNotification) ->notificationRepo.save(notification));
//
//	}
//
//	@Override
//	public void deleteNotification(Notification notification) {
//		notificationRepo.delete(notification);
//
//	}

}
