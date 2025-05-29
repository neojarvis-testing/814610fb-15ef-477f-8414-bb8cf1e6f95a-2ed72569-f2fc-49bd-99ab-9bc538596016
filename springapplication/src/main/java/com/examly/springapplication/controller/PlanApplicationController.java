package com.examly.springapplication.controller;

import com.examly.springapplication.model.PlanApplication;
import com.examly.springapplication.model.User;
import com.examly.springapplication.model.SavingsPlan;
import com.examly.springapplication.service.PlanApplicationService;
import com.examly.springapplication.repository.UserRepository;
import com.examly.springapplication.repository.SavingsPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/planapplications")
@CrossOrigin(origins = "*")
public class PlanApplicationController {

    @Autowired
    private PlanApplicationService planApplicationService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SavingsPlanRepository savingsPlanRepository;

    @GetMapping
    public ResponseEntity<List<PlanApplication>> getAllPlanApplications() {
        try {
            List<PlanApplication> planApplications = planApplicationService.getAllPlanApplications();
            return ResponseEntity.ok(planApplications);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> addPlanApplication(@RequestBody PlanApplication planApplication) {
        try {
            User user = userRepository.findById(planApplication.getUser().getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            SavingsPlan savingsPlan = savingsPlanRepository.findById(planApplication.getSavingsPlan().getSavingsPlanId())
                    .orElseThrow(() -> new RuntimeException("Savings plan not found"));

            planApplication.setUser(user);
            planApplication.setSavingsPlan(savingsPlan);
            planApplicationService.addPlanApplication(planApplication);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Plan application added successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Error adding plan application");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PlanApplication>> getPlanApplicationsByUserId(@PathVariable int userId) {
        try {
            List<PlanApplication> planApplications = planApplicationService.getPlanApplicationsByUserId(userId);
            return ResponseEntity.ok(planApplications);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{applicationId}")
    public ResponseEntity<?> getPlanApplicationById(@PathVariable Long applicationId) {
        try {
            Optional<PlanApplication> planApplication = planApplicationService.getPlanApplicationById(applicationId);
            if (planApplication.isPresent()) {
                return ResponseEntity.ok(planApplication.get());
            } else {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("message", "Plan application not found");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            }
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Error retrieving plan application");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PutMapping("/{applicationId}")
    public ResponseEntity<Map<String, String>> updatePlanApplication(@PathVariable Long applicationId, @RequestBody PlanApplication planApplication) {
        try {
            User user = userRepository.findById(planApplication.getUser().getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            SavingsPlan savingsPlan = savingsPlanRepository.findById(planApplication.getSavingsPlan().getSavingsPlanId())
                    .orElseThrow(() -> new RuntimeException("Savings plan not found"));

            planApplication.setUser(user);
            planApplication.setSavingsPlan(savingsPlan);
            planApplicationService.updatePlanApplication(applicationId, planApplication);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Plan application updated successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Error updating plan application");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @DeleteMapping("/{applicationId}")
    public ResponseEntity<Map<String, String>> deletePlanApplication(@PathVariable Long applicationId) {
        try {
            planApplicationService.deletePlanApplication(applicationId);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Plan application deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Error deleting plan application");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
