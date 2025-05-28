package com.examly.springapplication.repository;

import com.examly.springapplication.model.SavingsPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingsPlanRepository extends JpaRepository<SavingsPlan, Long> {
}

