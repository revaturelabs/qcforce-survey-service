package com.revature.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.Notification;

@Repository
public interface NotificationRepo extends JpaRepository<Notification,Integer> {

}
