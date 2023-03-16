package com.example.jiranismart.Fermented;

public class LoanModel {
    //id,loan_type,r_period,rate,amount,reg_date
    String id = null;
    String loan_type = null;
    String r_period = null;
    String rate = null;
    String amount = null;
    String reg_date = null;

    public LoanModel(String id, String loan_type, String r_period, String rate, String amount, String reg_date) {
        this.id = id;
        this.loan_type = loan_type;
        this.r_period = r_period;
        this.rate = rate;
        this.amount = amount;
        this.reg_date = reg_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoan_type() {
        return loan_type;
    }

    public void setLoan_type(String loan_type) {
        this.loan_type = loan_type;
    }

    public String getR_period() {
        return r_period;
    }

    public void setR_period(String r_period) {
        this.r_period = r_period;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    @Override
    public String toString() {
        return id + " " + loan_type + " " + r_period + " " + rate + " " + amount + " " + reg_date;
    }
}
