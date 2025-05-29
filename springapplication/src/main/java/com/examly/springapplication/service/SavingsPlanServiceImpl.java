package com.examly.springapplication.service;

import com.examly.springapplication.model.SavingsPlan;
import com.examly.springapplication.repository.SavingsPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SavingsPlanServiceImpl implements SavingsPlanService {

    @Autowired
    private SavingsPlanRepository savingsPlanRepository;

    @Override
    public List<SavingsPlan> getAllSavingsPlans() {
        return savingsPlanRepository.findAll();
    }

    @Override
    public SavingsPlan addSavingsPlan(SavingsPlan savingsPlan) {
        return savingsPlanRepository.save(savingsPlan);
    }

    @Override
    public Optional<SavingsPlan> getSavingsPlanById(Long id) {
        return savingsPlanRepository.findById(id);
    }

    @Override
    public SavingsPlan updateSavingsPlan(Long id, SavingsPlan savingsPlan) {
        savingsPlan.setSavingsPlanId(id);
        return savingsPlanRepository.save(savingsPlan);
    }

    @Override
    public void deleteSavingsPlan(Long id) {
        savingsPlanRepository.deleteById(id);
    }
}
