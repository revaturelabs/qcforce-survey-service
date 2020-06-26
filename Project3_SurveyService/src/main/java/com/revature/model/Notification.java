package com.revature.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "qcforce_survey.notification")
public class Notification implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notification_id")
	private String notificationId; //primary key

	@Column(name = "batch_name")
	private String batchName;

	@Column(name = "notification_timestamp")
	private Timestamp notificationTimestamp;

	public Notification(String notificationId, String batchName, Timestamp notificationTimestamp) {
		super();
		this.notificationId = notificationId;
		this.batchName = batchName;
		this.notificationTimestamp = notificationTimestamp;
	}

	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public Timestamp getNotificationTimestamp() {
		return notificationTimestamp;
	}

	public void setNotificationTimestamp(Timestamp notificationTimestamp) {
		this.notificationTimestamp = notificationTimestamp;
	}

	@Override
	public String toString() {
		return "Notification [notificationId=" + notificationId + ", batchName=" + batchName
				+ ", notificationTimestamp=" + notificationTimestamp + "]";
	}

	
}
