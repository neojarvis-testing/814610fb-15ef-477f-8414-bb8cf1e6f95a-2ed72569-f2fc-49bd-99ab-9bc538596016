package com.examly.springapplication.controller;

import com.examly.springapplication.model.Inquiry;
import com.examly.springapplication.model.User;
import com.examly.springapplication.service.InquiryService;
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
@RequestMapping("/api/inquiries")
@CrossOrigin(origins = "*")
public class InquiryController {

    @Autowired
    private InquiryService inquiryService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<Inquiry>> getAllInquiries() {
        try {
            List<Inquiry> inquiries = inquiryService.getAllInquiries();
            return ResponseEntity.ok(inquiries);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{inquiryId}")
    public ResponseEntity<?> getInquiryById(@PathVariable Long inquiryId) {
        try {
            Optional<Inquiry> inquiry = inquiryService.getInquiryById(inquiryId);
            if (inquiry.isPresent()) {
                return ResponseEntity.ok(inquiry.get());
            } else {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("message", "Inquiry not found");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            }
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Error retrieving inquiry");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> addInquiry(@RequestBody Inquiry inquiry) {
        try {
            User user = userRepository.findById(inquiry.getUser().getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            inquiry.setUser(user);
            inquiryService.addInquiry(inquiry);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Inquiry added successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Error adding inquiry");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Inquiry>> getInquiriesByUserId(@PathVariable int userId) {
        try {
            List<Inquiry> inquiries = inquiryService.getInquiriesByUserId(userId);
            return ResponseEntity.ok(inquiries);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/{inquiryId}")
    public ResponseEntity<Map<String, String>> updateInquiry(@PathVariable Long inquiryId, @RequestBody Inquiry inquiry) {
        try {
            inquiryService.updateInquiry(inquiryId, inquiry);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Inquiry updated successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Error updating inquiry");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @DeleteMapping("/{inquiryId}")
    public ResponseEntity<Map<String, String>> deleteInquiry(@PathVariable Long inquiryId) {
        try {
            inquiryService.deleteInquiry(inquiryId);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Inquiry deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Error deleting inquiry");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}

