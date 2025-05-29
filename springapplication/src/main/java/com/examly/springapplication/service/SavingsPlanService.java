package com.examly.springapplication.service;

import com.examly.springapplication.model.SavingsPlan;
import java.util.List;
import java.util.Optional;

public interface SavingsPlanService {
    List<SavingsPlan> getAllSavingsPlans();

    SavingsPlan addSavingsPlan(SavingsPlan savingsPlan);

    Optional<SavingsPlan> getSavingsPlanById(Long id);

    SavingsPlan updateSavingsPlan(Long id, SavingsPlan savingsPlan);

    void deleteSavingsPlan(Long id);
}
