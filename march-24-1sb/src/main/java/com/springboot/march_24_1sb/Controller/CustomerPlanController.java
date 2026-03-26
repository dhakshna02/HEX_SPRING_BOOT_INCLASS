package com.springboot.march_24_1sb.Controller;

import com.springboot.march_24_1sb.Service.CustomerPlanService;
import com.springboot.march_24_1sb.Service.CustomerService;
import com.springboot.march_24_1sb.dto.CustomerPlanDto;
import com.springboot.march_24_1sb.dto.getCustomerByPlanIdDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer/plan")
@AllArgsConstructor
public class CustomerPlanController {
    private  final CustomerPlanService customerPlanService;

    @PostMapping("/save/{customerid}/{planid}")
    public ResponseEntity<?> savePlan(@Valid @RequestBody CustomerPlanDto customerPlanDto,
                                      @PathVariable(value = "customerid") long customerid ,
                                      @PathVariable(value = "planid") long planid){
        customerPlanService.saveCustomerWithPlan(customerPlanDto,customerid,planid);


        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping("/get-customer-plan/{id}")
    public List<getCustomerByPlanIdDto> getCustomerDetailsWithPlanId(@PathVariable long id ){

        return  customerPlanService.getCustomerDetailsWithPlanId(id);
    }
}
