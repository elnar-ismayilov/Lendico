package com.lendico.finance.service;

import com.lendico.finance.exception.WrongInputParameter;
import com.lendico.finance.model.Loan;
import com.lendico.finance.model.Plan;
import org.junit.Test;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PlannerServiceImplTest {

    private final PlannerServiceImpl plannerService=new PlannerServiceImpl();
    @Test(expected = WrongInputParameter.class)
    public void can_handle_null_loan() throws WrongInputParameter {

        PlannerServiceImpl plannerService=new PlannerServiceImpl();
        // test if loan is null
        Loan loan = new Loan();
        plannerService.generatePlan(loan);
    }

    @Test(expected = WrongInputParameter.class)
    public void can_handle_wrong_loan_amount() throws WrongInputParameter {
        // test if loan amount is 0 or wrong
        Loan loan = new Loan();
        loan.setLoanAmount(-1);
        loan.setDuration(24);
        loan.setNominalRate(5);
        loan.setStartDate(LocalDateTime.now());
        plannerService.generatePlan(loan);
    }

    @Test(expected = WrongInputParameter.class)
    public void can_handle_wrong_duration() throws WrongInputParameter {
        // test if duration is wrong
        Loan loan = new Loan();
        loan.setLoanAmount(5000);
        loan.setDuration(-24);
        loan.setNominalRate(5);
        loan.setStartDate(LocalDateTime.now());
        plannerService.generatePlan(loan);
    }

    @Test(expected = WrongInputParameter.class)
    public void can_handle_null_start_date() throws WrongInputParameter {
        // test if start date is null
        Loan loan = new Loan();
        loan.setLoanAmount(5000);
        loan.setDuration(24);
        loan.setNominalRate(5);
        plannerService.generatePlan(loan);
    }

    @Test(expected = WrongInputParameter.class)
    public void can_handle_wrong_nominal_rate() throws WrongInputParameter {
        // test if nominal rate is wrong
        Loan loan = new Loan();
        loan.setLoanAmount(5000);
        loan.setDuration(24);
        loan.setNominalRate(-5);
        loan.setStartDate(LocalDateTime.now());
        plannerService.generatePlan(loan);
    }

    @Test
    public void can_generate_plan_for_2_years() throws Exception {
        Loan loan = new Loan();
        loan.setLoanAmount(5000);
        loan.setDuration(24);
        loan.setNominalRate(5);
        loan.setStartDate( LocalDateTime.parse("2018-01-01T00:00:01"));
        List<Plan> planList=plannerService.generatePlan(loan);

        assertNotNull("Plan should not be null.", planList);

        assertEquals("Two years loan in 24 plans.", planList.size(), 24);


        // Check first  plan
        Plan plan=planList.get(0);
        assertEquals(doubleRoundToTwo(plan.getBorrowerPaymentAmount()), 219.36,0);
        assertEquals("Last date .", plan.getDate(), LocalDateTime.parse("2018-01-01T00:00:01"));
        assertEquals(doubleRoundToTwo(plan.getInitialOutstandingPrincipal()), 5000,0);
        assertEquals( doubleRoundToTwo(plan.getInterest()), 20.83,0);
        assertEquals(doubleRoundToTwo( plan.getPrincipal()), 198.52,0);
        assertEquals( doubleRoundToTwo(plan.getRemainingOutstandingPrincipal()), 4801.48,0);

        // Check last plan
        plan=planList.get(planList.size()-1);
        assertEquals(doubleRoundToTwo(plan.getBorrowerPaymentAmount()), 219.36,0);
        assertEquals("Last date .", plan.getDate(), LocalDateTime.parse("2019-12-01T00:00:01"));
        assertEquals(doubleRoundToTwo(plan.getInitialOutstandingPrincipal()), 218.45,0);
        assertEquals( doubleRoundToTwo(plan.getInterest()), 0.91,0);
        assertEquals(doubleRoundToTwo( plan.getPrincipal()), 218.45,0);
        assertEquals( doubleRoundToTwo(plan.getRemainingOutstandingPrincipal()), 0,0);

    }


    @Test
    public void can_generate_plan_for_6_months() throws Exception {
        Loan loan = new Loan();
        loan.setLoanAmount(5000);
        loan.setDuration(12);
        loan.setNominalRate(5);
        loan.setStartDate( LocalDateTime.parse("2018-01-01T00:00:01"));
        List<Plan> planList=plannerService.generatePlan(loan);

        assertNotNull("Plan should not be null.", planList);

        assertEquals("Two years loan in 24 plans.", planList.size(), 12);

        // Check first  plan
        Plan plan=planList.get(0);
        assertEquals(doubleRoundToTwo(plan.getBorrowerPaymentAmount()), 428.04,0);
        assertEquals("Last date .", plan.getDate(), LocalDateTime.parse("2018-01-01T00:00:01"));
        assertEquals(doubleRoundToTwo(plan.getInitialOutstandingPrincipal()), 5000,0);
        assertEquals( doubleRoundToTwo(plan.getInterest()), 20.83,0);
        assertEquals(doubleRoundToTwo( plan.getPrincipal()), 407.2,0);
        assertEquals( doubleRoundToTwo(plan.getRemainingOutstandingPrincipal()), 4592.8,0);

        // Check last plan
        plan=planList.get(planList.size()-1);
        assertEquals(doubleRoundToTwo(plan.getBorrowerPaymentAmount()), 428.04,0);
        assertEquals("Last date .", plan.getDate(), LocalDateTime.parse("2018-12-01T00:00:01"));
        assertEquals(doubleRoundToTwo(plan.getInitialOutstandingPrincipal()), 426.26,0);
        assertEquals( doubleRoundToTwo(plan.getInterest()), 1.78,0);
        assertEquals(doubleRoundToTwo( plan.getPrincipal()), 426.26,0);
        assertEquals( doubleRoundToTwo(plan.getRemainingOutstandingPrincipal()), 0,0);

    }

    private  double doubleRoundToTwo(double d) throws NumberFormatException {
        DecimalFormat df2 = new DecimalFormat(".##");
        return Double.valueOf(df2.format(d));
    }
}
