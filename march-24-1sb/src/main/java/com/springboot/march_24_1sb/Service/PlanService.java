package com.springboot.march_24_1sb.Service;

import com.springboot.march_24_1sb.Repository.PlanRepository;
import com.springboot.march_24_1sb.exception.ResourceNotFound;
import com.springboot.march_24_1sb.model.Plan;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlanService {
    private final PlanRepository planRepository;

    public Plan getPlanById(long planid) {
        return planRepository.findById(planid).orElseThrow(()-> new ResourceNotFound("Invalid plan id"));
    }
}
