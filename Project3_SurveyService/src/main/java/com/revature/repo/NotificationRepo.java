package com.revature.repo;

import java.util.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.revature.model.Notification;

@Repository
public interface NotificationRepo extends JpaRepository<Notification,Integer> {

	Stream<Notification> findByBatchName(String batchName); //it can be Stream or List

}
