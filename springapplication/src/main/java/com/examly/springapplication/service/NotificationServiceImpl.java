package com.examly.springapplication.service;

import com.examly.springapplication.model.Notification;
import com.examly.springapplication.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public Notification addNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getNotificationsByUserId(int userId) {
        return notificationRepository.findByUserUserIdOrderByCreatedAtDesc(userId);
    }

    @Override
    public Optional<Notification> getNotificationById(Long id) {
        return notificationRepository.findById(id);
    }

    @Override
    public Notification updateNotification(Long id, Notification notification) {
        notification.setNotificationId(id);
        return notificationRepository.save(notification);
    }
}

