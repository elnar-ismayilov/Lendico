package com.lendico.finance.model;

import java.time.LocalDateTime;

public class Loan {

    private double loanAmount;
    private double nominalRate;
    private int duration;
    private LocalDateTime startDate;

    public Loan() {
    }

    public Loan(double loanAmount, double nominalRate, int duration, LocalDateTime startDate) {
        this.loanAmount = loanAmount;
        this.nominalRate = nominalRate;
        this.duration = duration;
        this.startDate = startDate;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public double getNominalRate() {
        return nominalRate;
    }

    public void setNominalRate(double nominalRate) {
        this.nominalRate = nominalRate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanAmount=" + loanAmount +
                ", nominalRate=" + nominalRate +
                ", duration=" + duration +
                ", startDate=" + startDate +
                '}';
    }
}
