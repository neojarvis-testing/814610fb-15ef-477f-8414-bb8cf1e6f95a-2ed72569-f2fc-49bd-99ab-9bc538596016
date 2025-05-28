package com.examly.springapplication.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @Column(columnDefinition = "TEXT")
    private String message;

    private boolean isRead = false;
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "inquiry_id")
    private Inquiry inquiry;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "plan_application_id")
    private PlanApplication planApplication;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "savings_plan_id")
    private SavingsPlan savingsPlan;

    public Notification() {}

    // Getters and Setters
    public Long getNotificationId() { return notificationId; }
    public void setNotificationId(Long notificationId) { this.notificationId = notificationId; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public boolean isRead() { return isRead; }
    public void setRead(boolean read) { isRead = read; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Inquiry getInquiry() { return inquiry; }
    public void setInquiry(Inquiry inquiry) { this.inquiry = inquiry; }

    public PlanApplication getPlanApplication() { return planApplication; }
    public void setPlanApplication(PlanApplication planApplication) { this.planApplication = planApplication; }

    public SavingsPlan getSavingsPlan() { return savingsPlan; }
    public void setSavingsPlan(SavingsPlan savingsPlan) { this.savingsPlan = savingsPlan; }
}
