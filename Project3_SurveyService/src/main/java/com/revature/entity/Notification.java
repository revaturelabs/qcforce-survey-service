package com.revature.entity;

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
@Table(name = "notification", schema = "qcforce_survey")
public class Notification implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id; // PK

	@Column(name = "notification_ts")
	private Timestamp notificationTimestamp;

	@Column(name = "batch_name")
	private String batchName;

	public Notification(String batchName) {
		super();
		this.batchName = batchName;
	}

	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getNotificationTimestamp() {
		return notificationTimestamp;
	}

	public void setNotificationTimestamp(Timestamp notificationTimestamp) {
		this.notificationTimestamp = notificationTimestamp;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	@Override
	public String toString() {
		return "Notification [id=" + id + ", notificationTimestamp=" + notificationTimestamp + ", batchName="
				+ batchName + "]";
	}

}
