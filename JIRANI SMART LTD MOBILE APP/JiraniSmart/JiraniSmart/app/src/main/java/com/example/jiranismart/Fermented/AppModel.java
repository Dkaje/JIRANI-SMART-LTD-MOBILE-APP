package com.example.jiranismart.Fermented;

public class AppModel {
    //id,borrow_id,loan_id,id_no,name,phone,loan,status,fina_status,reg_date/
    String id = null;
    String borrow_id = null;
    String loan_id = null;
    String id_no = null;
    String name = null;
    String phone = null;
    String loan = null;
    String status = null;
    String auction = null;
    String total = null;
    String amount = null;
    String balance = null;
    String mpesa = null;
    String fina_status = null;
    String reg_date = null;

    public AppModel(String id, String borrow_id, String loan_id, String id_no, String name, String phone, String loan, String status, String auction, String total, String amount, String balance, String mpesa, String fina_status, String reg_date) {
        this.id = id;
        this.borrow_id = borrow_id;
        this.loan_id = loan_id;
        this.id_no = id_no;
        this.name = name;
        this.phone = phone;
        this.loan = loan;
        this.status = status;
        this.auction = auction;
        this.total = total;
        this.amount = amount;
        this.balance = balance;
        this.mpesa = mpesa;
        this.fina_status = fina_status;
        this.reg_date = reg_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getLoan() {
        return loan;
    }

    public void setLoan(String loan) {
        this.loan = loan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuction() {
        return auction;
    }

    public void setAuction(String auction) {
        this.auction = auction;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getMpesa() {
        return mpesa;
    }

    public void setMpesa(String mpesa) {
        this.mpesa = mpesa;
    }

    public String getFina_status() {
        return fina_status;
    }

    public void setFina_status(String fina_status) {
        this.fina_status = fina_status;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    @Override
    public String toString() {
        return borrow_id + " " + loan_id + " " + id + " " + id_no + " " + name + " " + phone + " " + loan + " " +
                status + " " + mpesa + " " + reg_date + " " + fina_status;
    }
}
