package com.examly.springapplication.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "savings_plans")
public class SavingsPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long savingsPlanId;

    private String name;
    private BigDecimal goalAmount;
    private int timeFrame;
    private String riskLevel;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String status;

    public SavingsPlan() {}

    // Getters and Setters
    public Long getSavingsPlanId() { return savingsPlanId; }
    public void setSavingsPlanId(Long savingsPlanId) { this.savingsPlanId = savingsPlanId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getGoalAmount() { return goalAmount; }
    public void setGoalAmount(BigDecimal goalAmount) { this.goalAmount = goalAmount; }

    public int getTimeFrame() { return timeFrame; }
    public void setTimeFrame(int timeFrame) { this.timeFrame = timeFrame; }

    public String getRiskLevel() { return riskLevel; }
    public void setRiskLevel(String riskLevel) { this.riskLevel = riskLevel; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
