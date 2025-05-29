package com.examly.springapplication.service;

import com.examly.springapplication.model.PlanApplication;
import java.util.List;
import java.util.Optional;

public interface PlanApplicationService {
    List<PlanApplication> getAllPlanApplications();
    PlanApplication addPlanApplication(PlanApplication planApplication);
    List<PlanApplication> getPlanApplicationsByUserId(int userId);
    Optional<PlanApplication> getPlanApplicationById(Long id);
    PlanApplication updatePlanApplication(Long id, PlanApplication planApplication);
    void deletePlanApplication(Long id);
}
