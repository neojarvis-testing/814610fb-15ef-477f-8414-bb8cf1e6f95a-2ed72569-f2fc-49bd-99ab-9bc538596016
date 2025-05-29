package com.examly.springapplication.controller;

import com.examly.springapplication.model.SavingsPlan;
import com.examly.springapplication.service.SavingsPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/savingsplans")
@CrossOrigin(origins = "*")
public class SavingsPlanController {

    @Autowired
    private SavingsPlanService savingsPlanService;

    @GetMapping
    public ResponseEntity<List<SavingsPlan>> getAllSavingsPlans() {
        try {
            List<SavingsPlan> savingsPlans = savingsPlanService.getAllSavingsPlans();
            return ResponseEntity.ok(savingsPlans);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> addSavingsPlan(@RequestBody SavingsPlan savingsPlan) {
        try {
            savingsPlanService.addSavingsPlan(savingsPlan);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Savings plan added successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Error adding savings plan");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/{savingsPlanId}")
    public ResponseEntity<?> getSavingsPlanById(@PathVariable Long savingsPlanId) {
        try {
            Optional<SavingsPlan> savingsPlan = savingsPlanService.getSavingsPlanById(savingsPlanId);
            if (savingsPlan.isPresent()) {
                return ResponseEntity.ok(savingsPlan.get());
            } else {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("message", "Savings plan not found");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            }
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Error retrieving savings plan");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PutMapping("/{savingsPlanId}")
    public ResponseEntity<Map<String, String>> updateSavingsPlan(@PathVariable Long savingsPlanId, @RequestBody SavingsPlan savingsPlan) {
        try {
            savingsPlanService.updateSavingsPlan(savingsPlanId, savingsPlan);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Savings plan updated successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Error updating savings plan");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @DeleteMapping("/{savingsPlanId}")
    public ResponseEntity<Map<String, String>> deleteSavingsPlan(@PathVariable Long savingsPlanId) {
        try {
            savingsPlanService.deleteSavingsPlan(savingsPlanId);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Savings plan deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Error deleting savings plan");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
