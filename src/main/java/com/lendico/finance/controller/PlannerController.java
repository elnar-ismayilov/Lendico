package com.lendico.finance.controller;

import com.lendico.finance.exception.WrongInputParameter;
import com.lendico.finance.model.Loan;
import com.lendico.finance.model.Plan;
import com.lendico.finance.service.PlannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/generate-plan")
public class PlannerController {


    private final PlannerService plannerService;

    @Autowired
    public PlannerController(final PlannerService plannerService)
    {
        this.plannerService = plannerService;
    }

    @PostMapping
    public List<Plan> generatePlan(@Valid @RequestBody Loan loan) throws WrongInputParameter {
        return plannerService.generatePlan(loan);
    }
}
