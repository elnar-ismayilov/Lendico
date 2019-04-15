package com.lendico.finance.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lendico.finance.util.DecimalJsonSerializer;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

import static com.lendico.finance.util.Constants.CLIENT_NUMBER_FORMAT;

public class Plan {

    private double borrowerPaymentAmount;
    private LocalDateTime date;
    private double initialOutstandingPrincipal;
    private double interest;
    private double principal;
    private double remainingOutstandingPrincipal;

    public Plan() {
    }

    public Plan(double borrowerPaymentAmount, LocalDateTime date, double initialOutstandingPrincipal, double interest, double principal, double remainingOutstandingPrincipal) {
        this.borrowerPaymentAmount = borrowerPaymentAmount;
        this.date = date;
        this.initialOutstandingPrincipal = initialOutstandingPrincipal;
        this.interest = interest;
        this.principal = principal;
        this.remainingOutstandingPrincipal = remainingOutstandingPrincipal;
    }
    @JsonSerialize(using=DecimalJsonSerializer.class)
    public double getBorrowerPaymentAmount() {
        return borrowerPaymentAmount;
    }

    public void setBorrowerPaymentAmount(double borrowerPaymentAmount) {
        this.borrowerPaymentAmount = borrowerPaymentAmount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @JsonSerialize(using=DecimalJsonSerializer.class)
    public double getInitialOutstandingPrincipal() {
        return initialOutstandingPrincipal;
    }

    public void setInitialOutstandingPrincipal(double initialOutstandingPrincipal) {
        this.initialOutstandingPrincipal = initialOutstandingPrincipal;
    }

    @JsonSerialize(using=DecimalJsonSerializer.class)
    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    @JsonSerialize(using=DecimalJsonSerializer.class)
    public double getPrincipal() {
        return principal;
    }

    public void setPrincipal(double principal) {
        this.principal = principal;
    }

    @JsonSerialize(using=DecimalJsonSerializer.class)
    public double getRemainingOutstandingPrincipal() {
        return remainingOutstandingPrincipal;
    }

    public void setRemainingOutstandingPrincipal(double remainingOutstandingPrincipal) {
        this.remainingOutstandingPrincipal = remainingOutstandingPrincipal;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "borrowerPaymentAmount=" + borrowerPaymentAmount +
                ", date=" + date +
                ", initialOutstandingPrincipal=" + initialOutstandingPrincipal +
                ", interest=" + interest +
                ", principal=" + principal +
                ", remainingOutstandingPrincipal=" + remainingOutstandingPrincipal +
                '}';
    }

}
