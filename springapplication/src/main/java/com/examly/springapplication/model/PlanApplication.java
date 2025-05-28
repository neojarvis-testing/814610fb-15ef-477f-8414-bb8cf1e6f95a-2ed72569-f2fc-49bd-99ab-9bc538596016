package com.examly.springapplication.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "plan_applications")
public class PlanApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planApplicationId;

    private double appliedAmount;
    private String status;
    private LocalDateTime applicationDate;
    private String remarks;

    @Column(columnDefinition = "LONGTEXT")
    private String proofDocument;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "savings_plan_id")
    private SavingsPlan savingsPlan;

    public PlanApplication() {}

    // Getters and Setters
    public Long getPlanApplicationId() { return planApplicationId; }
    public void setPlanApplicationId(Long planApplicationId) { this.planApplicationId = planApplicationId; }

    public double getAppliedAmount() { return appliedAmount; }
    public void setAppliedAmount(double appliedAmount) { this.appliedAmount = appliedAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getApplicationDate() { return applicationDate; }
    public void setApplicationDate(LocalDateTime applicationDate) { this.applicationDate = applicationDate; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public String getProofDocument() { return proofDocument; }
    public void setProofDocument(String proofDocument) { this.proofDocument = proofDocument; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public SavingsPlan getSavingsPlan() { return savingsPlan; }
    public void setSavingsPlan(SavingsPlan savingsPlan) { this.savingsPlan = savingsPlan; }
}