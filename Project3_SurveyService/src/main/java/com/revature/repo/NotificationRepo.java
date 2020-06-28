package com.revature.repo;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.revature.model.Notification;

@Repository
@Transactional
public interface NotificationRepo extends JpaRepository<Notification,Integer> {

	@Query("SELECT notif FROM Notification notif WHERE notif.batchName = :batchName")
	List<Notification> findByBatchName(@Param ("batchName") String batchName); //it can be Stream or List

}
