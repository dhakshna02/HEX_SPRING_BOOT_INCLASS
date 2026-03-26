package com.springboot.march_24_1sb.Service;

import com.springboot.march_24_1sb.Repository.CustomerPlanRepository;
import com.springboot.march_24_1sb.dto.CustomerPlanDto;
import com.springboot.march_24_1sb.dto.getCustomerByPlanIdDto;
import com.springboot.march_24_1sb.model.Customer;
import com.springboot.march_24_1sb.model.CustomerPlan;
import com.springboot.march_24_1sb.model.Plan;
import com.springboot.march_24_1sb.util.CustomerPlanUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerPlanService {
    private final CustomerService customerService;
    private final PlanService planService;
    private final CustomerPlanRepository customerPlanRepository;



    public void saveCustomerWithPlan(@Valid CustomerPlanDto customerPlanDto, long customerid, long planid) {

        // check the customer exist already
        Customer customer = customerService.getCustomerByIdIntEnt(customerid);

        // check the plan exist
        Plan plan = planService.getPlanById(planid);

        // get the end date this can be done by calling the util
        LocalDate enddate = CustomerPlanUtil.getEndDate(customerPlanDto.createdAt(),plan.getDays());
        // first change the dto to entity and  add the customer details and plan detils to the entitye

       CustomerPlan customerPlan = new CustomerPlan();

       customerPlan.setCreatedAt(customerPlanDto.createdAt());
       customerPlan.setEndDate(enddate);
       customerPlan.setDiscount(customerPlanDto.discount());
       customerPlan.setCoupon(customerPlanDto.coupon());
       customerPlan.setCustomer(customer);
       customerPlan.setPlan(plan);

       customerPlanRepository.save(customerPlan);

    }

    public List<getCustomerByPlanIdDto> getCustomerDetailsWithPlanId(long id) {
        return  customerPlanRepository.getCustomerDetailsWithPlanId(id);
    }
}
