package com.examly.springapplication.service;

import com.examly.springapplication.model.Notification;
import java.util.List;
import java.util.Optional;

public interface NotificationService {
    List<Notification> getAllNotifications();
    Notification addNotification(Notification notification);
    List<Notification> getNotificationsByUserId(int userId);
    Optional<Notification> getNotificationById(Long id);
    Notification updateNotification(Long id, Notification notification);
}
