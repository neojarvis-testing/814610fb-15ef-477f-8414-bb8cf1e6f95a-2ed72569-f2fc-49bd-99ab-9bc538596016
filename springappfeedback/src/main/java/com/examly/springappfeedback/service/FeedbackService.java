package com.examly.springappfeedback.service;

import com.examly.springappfeedback.model.Feedback;
import java.util.List;

public interface FeedbackService {
    List<Feedback> getAllFeedbacks();
    Feedback addFeedback(Feedback feedback);
    List<Feedback> getFeedbacksByUserId(int userId);
    void deleteFeedback(Long feedbackId);
}

