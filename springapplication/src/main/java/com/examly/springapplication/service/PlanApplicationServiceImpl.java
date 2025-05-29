package com.examly.springapplication.service;

import com.examly.springapplication.model.PlanApplication;
import com.examly.springapplication.repository.PlanApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanApplicationServiceImpl implements PlanApplicationService {

    @Autowired
    private PlanApplicationRepository planApplicationRepository;

    @Override
    public List<PlanApplication> getAllPlanApplications() {
        return planApplicationRepository.findAll();
    }

    @Override
    public PlanApplication addPlanApplication(PlanApplication planApplication) {
        return planApplicationRepository.save(planApplication);
    }

    @Override
    public List<PlanApplication> getPlanApplicationsByUserId(int userId) {
        return planApplicationRepository.findByUserUserId(userId);
    }

    @Override
    public Optional<PlanApplication> getPlanApplicationById(Long id) {
        return planApplicationRepository.findById(id);
    }

    @Override
    public PlanApplication updatePlanApplication(Long id, PlanApplication planApplication) {
        planApplication.setPlanApplicationId(id);
        return planApplicationRepository.save(planApplication);
    }

    @Override
    public void deletePlanApplication(Long id) {
        planApplicationRepository.deleteById(id);
    }
}

