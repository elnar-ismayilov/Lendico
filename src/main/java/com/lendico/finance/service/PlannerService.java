package com.lendico.finance.service;

import com.lendico.finance.exception.WrongInputParameter;
import com.lendico.finance.model.Loan;
import com.lendico.finance.model.Plan;

import java.util.List;

public interface PlannerService {

    List<Plan> generatePlan(Loan loan) throws WrongInputParameter;
}
