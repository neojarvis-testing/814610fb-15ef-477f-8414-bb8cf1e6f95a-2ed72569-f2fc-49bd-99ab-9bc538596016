package com.examly.springapplication.controller;

import com.examly.springapplication.model.Notification;
import com.examly.springapplication.model.User;
import com.examly.springapplication.service.NotificationService;
import com.examly.springapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/notifications")
    public ResponseEntity<List<Notification>> getAllNotifications() {
        try {
            List<Notification> notifications = notificationService.getAllNotifications();
            return ResponseEntity.ok(notifications);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/notification")
    public ResponseEntity<Map<String, String>> addNotification(@RequestBody Notification notification) {
        try {
            User user = userRepository.findById(notification.getUser().getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            notification.setUser(user);
            notificationService.addNotification(notification);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Notification added successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Error adding notification");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PutMapping("/notification/{notificationId}")
    public ResponseEntity<Map<String, String>> updateNotification(@PathVariable Long notificationId, @RequestBody Notification notification) {
        try {
            notificationService.updateNotification(notificationId, notification);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Notification updated successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Error updating notification");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}

