package com.revature.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.revature.model.Notification;

//@Repository
//@Transactional
public interface NotificationRepo {

	@Query("SELECT notif FROM Notification notif WHERE notif.batchName = :batchName")
	List<Notification> findByBatchName(@Param("batchName") String batchName); // it can be Stream or List

}
