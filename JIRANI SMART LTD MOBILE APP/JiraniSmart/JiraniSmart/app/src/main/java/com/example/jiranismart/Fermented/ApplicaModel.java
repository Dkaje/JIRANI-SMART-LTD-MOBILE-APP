package com.example.jiranismart.Fermented;

public class ApplicaModel {
    //borrow_id,loan_id,loan_type,rate,id_no,name,phone,maxloan,loan,interest,loan_period,period,total,expected_monthly,
//balance,status,reg_date,pay_date,payment_status,fina_status
    String borrow_id = null;
    String loan_id = null;
    String loan_type = null;
    String rate = null;
    String id_no = null;
    String name = null;
    String phone = null;
    String maxloan = null;
    String loan = null;
    String interest = null;
    String loan_period = null;
    String period = null;
    String total = null;
    String expected_monthly = null;
    String balance = null;
    String status = null;
    String reg_date = null;
    String pay_date = null;
    String payment_status = null;
    String fina_status = null;

    public ApplicaModel(String borrow_id, String loan_id, String loan_type, String rate, String id_no, String name, String phone, String maxloan, String loan, String interest, String loan_period, String period, String total, String expected_monthly, String balance, String status, String reg_date, String pay_date, String payment_status, String fina_status) {
        this.borrow_id = borrow_id;
        this.loan_id = loan_id;
        this.loan_type = loan_type;
        this.rate = rate;
        this.id_no = id_no;
        this.name = name;
        this.phone = phone;
        this.maxloan = maxloan;
        this.loan = loan;
        this.interest = interest;
        this.loan_period = loan_period;
        this.period = period;
        this.total = total;
        this.expected_monthly = expected_monthly;
        this.balance = balance;
        this.status = status;
        this.reg_date = reg_date;
        this.pay_date = pay_date;
        this.payment_status = payment_status;
        this.fina_status = fina_status;
    }

    public String getBorrow_id() {
        return borrow_id;
    }

    public void setBorrow_id(String borrow_id) {
        this.borrow_id = borrow_id;
    }

    public String getLoan_id() {
        return loan_id;
    }

    public void setLoan_id(String loan_id) {
        this.loan_id = loan_id;
    }

    public String getLoan_type() {
        return loan_type;
    }

    public void setLoan_type(String loan_type) {
        this.loan_type = loan_type;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getId_no() {
        return id_no;
    }

    public void setId_no(String id_no) {
        this.id_no = id_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMaxloan() {
        return maxloan;
    }

    public void setMaxloan(String maxloan) {
        this.maxloan = maxloan;
    }

    public String getLoan() {
        return loan;
    }

    public void setLoan(String loan) {
        this.loan = loan;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getLoan_period() {
        return loan_period;
    }

    public void setLoan_period(String loan_period) {
        this.loan_period = loan_period;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getExpected_monthly() {
        return expected_monthly;
    }

    public void setExpected_monthly(String expected_monthly) {
        this.expected_monthly = expected_monthly;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    public String getPay_date() {
        return pay_date;
    }

    public void setPay_date(String pay_date) {
        this.pay_date = pay_date;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public String getFina_status() {
        return fina_status;
    }

    public void setFina_status(String fina_status) {
        this.fina_status = fina_status;
    }

    @Override
    public String toString() {
        return borrow_id + " " + loan_id + " " + loan_type + " " + rate + " " + id_no + " " + name + " " + phone + " " + maxloan + " " + loan + " " + interest + " " + loan_period + " " + period + " " + total + " " + expected_monthly + " " +
                balance + " " + status + " " + reg_date + " " + pay_date + " " + payment_status + " " + fina_status;
    }
}
