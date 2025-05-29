package com.examly.springapplication.repository;

import com.examly.springapplication.model.PlanApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlanApplicationRepository extends JpaRepository<PlanApplication, Long> {
    List<PlanApplication> findByUserUserId(int userId);
}
