package com.examly.springapplication.service;

import com.examly.springapplication.model.Inquiry;
import com.examly.springapplication.repository.InquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InquiryServiceImpl implements InquiryService {

    @Autowired
    private InquiryRepository inquiryRepository;

    @Override
    public List<Inquiry> getAllInquiries() {
        return inquiryRepository.findAll();
    }

    @Override
    public Inquiry addInquiry(Inquiry inquiry) {
        return inquiryRepository.save(inquiry);
    }

    @Override
    public Optional<Inquiry> getInquiryById(Long id) {
        return inquiryRepository.findById(id);
    }

    @Override
    public List<Inquiry> getInquiriesByUserId(int userId) {
        return inquiryRepository.findByUserUserId(userId);
    }

    @Override
    public Inquiry updateInquiry(Long id, Inquiry inquiry) {
        inquiry.setInquiryId(id);
        return inquiryRepository.save(inquiry);
    }

    @Override
    public void deleteInquiry(Long id) {
        inquiryRepository.deleteById(id);
    }
}
