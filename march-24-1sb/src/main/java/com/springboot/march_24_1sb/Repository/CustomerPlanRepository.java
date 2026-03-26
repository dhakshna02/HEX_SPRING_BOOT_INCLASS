package com.springboot.march_24_1sb.Repository;

import com.springboot.march_24_1sb.dto.getCustomerByPlanIdDto;
import com.springboot.march_24_1sb.model.CustomerPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerPlanRepository extends JpaRepository<CustomerPlan,Long> {


   @Query("""
           select cp.customer.id,
                  cp.customer.name,
                  cp.customer.email,
                  cp.customer.city,
                  cp.createdAt,
                  cp.endDate,
                  cp.plan.planName
                  from CustomerPlan cp  where cp.plan.id = ?1
           
           """)
    List<getCustomerByPlanIdDto> getCustomerDetailsWithPlanId(long id);
}
