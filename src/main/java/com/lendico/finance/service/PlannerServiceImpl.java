package com.lendico.finance.service;

import com.lendico.finance.exception.WrongInputParameter;
import com.lendico.finance.model.Loan;
import com.lendico.finance.model.Plan;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.lendico.finance.util.Constants.*;

@Service
public class PlannerServiceImpl implements PlannerService {

    private static final Logger logger = LogManager.getLogger(PlannerServiceImpl.class);


 //Generate plan for loan
    @Override
    public List<Plan> generatePlan(Loan loan) throws WrongInputParameter {
        logger.info(loan.toString());
        checkInputParameters(loan);
        List<Plan> planList = new ArrayList<>();
        // calculation of annuity
        double monthlyRate = loan.getNominalRate() / MONTHS_IN_YEAR / PERCENTILE;
        double annuity =
                (loan.getLoanAmount() * monthlyRate) /
                        (1 - Math.pow(1 + monthlyRate, -loan.getDuration()));
        logger.info("Calculated annuity:"+annuity);
        double loanValue = loan.getLoanAmount();
        // loop for creating plans
        for (int i = 0; i < loan.getDuration(); i++) {
            Plan plan = new Plan();
            double interest = loan.getNominalRate() * DAYS_OF_MONTH * loanValue / (DAYS_IN_YEAR * PERCENTILE);
            double principal = annuity - (interest>loanValue?loanValue:interest);
            plan.setInterest(interest);
            plan.setPrincipal(principal);
            plan.setBorrowerPaymentAmount(principal + interest);
            plan.setDate(loan.getStartDate().plusMonths(i));
            plan.setInitialOutstandingPrincipal(loanValue);
            loanValue = loanValue - principal;
            plan.setRemainingOutstandingPrincipal(loanValue>=0?loanValue:0);
            planList.add(plan);
        }

        return planList;
    }

// check input parameters
    private void checkInputParameters(Loan loan) throws WrongInputParameter {
      if (loan.getLoanAmount()<=0 ){
          logger.error("Loan amount can not be 0 or negative!!!");
          throw new WrongInputParameter("Loan amount can not be 0 or negative!!!");
      }

        if (loan.getDuration()<=0 ){
            logger.error("Wrong duration!!!");
            throw new WrongInputParameter("Wrong duration!!!");
        }

        if (loan.getNominalRate()<=0 ){
            logger.error("Nominal rate can not be 0 or negative!!!");
            throw new WrongInputParameter("Nominal rate can not be 0 or negative!!!");
        }

        if (loan.getStartDate()==null ){
            logger.error("Start date can not be empty!!!");
            throw new WrongInputParameter("Start date can not be empty!!!");
        }


    }
}
