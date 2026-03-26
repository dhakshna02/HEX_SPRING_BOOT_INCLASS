package com.springboot.march_24_1sb.Repository;

import com.springboot.march_24_1sb.Service.PlanService;
import com.springboot.march_24_1sb.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan,Long> {
}
