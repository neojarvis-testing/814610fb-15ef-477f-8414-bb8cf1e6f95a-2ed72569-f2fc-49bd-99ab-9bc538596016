package com.examly.springapplication.service;

import com.examly.springapplication.model.Inquiry;
import java.util.List;
import java.util.Optional;

public interface InquiryService {
    List<Inquiry> getAllInquiries();
    Inquiry addInquiry(Inquiry inquiry);
    Optional<Inquiry> getInquiryById(Long id);
    List<Inquiry> getInquiriesByUserId(int userId);
    Inquiry updateInquiry(Long id, Inquiry inquiry);
    void deleteInquiry(Long id);
}

