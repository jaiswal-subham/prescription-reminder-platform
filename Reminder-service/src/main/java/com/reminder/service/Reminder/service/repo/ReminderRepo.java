package com.reminder.service.Reminder.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.reminder.service.Reminder.service.entity.ReminderEntity;
import java.util.Optional;

@Repository
public interface ReminderRepo extends JpaRepository<ReminderEntity,String> {

    Optional<ReminderEntity> findByReminderEventId(String id);
}
