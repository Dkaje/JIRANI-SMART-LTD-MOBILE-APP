package com.example.jiranismart.Fermented;

public class AliModel {
    //reg_id,borrow_id,mpesa,id_no,name,phone,amount,current_period,total,summed,status,remarks,reg_date
    String reg_id = null;
    String borrow_id = null;
    String mpesa = null;
    String id_no = null;
    String name = null;
    String phone = null;
    String interest = null;
    String period = null;
    String expected = null;
    String amount = null;
    String current_period = null;
    String total = null;
    String summed = null;
    String status = null;
    String remarks = null;
    String reg_date = null;

    public AliModel(String reg_id, String borrow_id, String mpesa, String id_no, String name, String phone, String interest, String period, String expected, String amount, String current_period, String total, String summed, String status, String remarks, String reg_date) {
        this.reg_id = reg_id;
        this.borrow_id = borrow_id;
        this.mpesa = mpesa;
        this.id_no = id_no;
        this.name = name;
        this.phone = phone;
        this.interest = interest;
        this.period = period;
        this.expected = expected;
        this.amount = amount;
        this.current_period = current_period;
        this.total = total;
        this.summed = summed;
        this.status = status;
        this.remarks = remarks;
        this.reg_date = reg_date;
    }

    public String getReg_id() {
        return reg_id;
    }

    public void setReg_id(String reg_id) {
        this.reg_id = reg_id;
    }

    public String getBorrow_id() {
        return borrow_id;
    }

    public void setBorrow_id(String borrow_id) {
        this.borrow_id = borrow_id;
    }

    public String getMpesa() {
        return mpesa;
    }

    public void setMpesa(String mpesa) {
        this.mpesa = mpesa;
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

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getExpected() {
        return expected;
    }

    public void setExpected(String expected) {
        this.expected = expected;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrent_period() {
        return current_period;
    }

    public void setCurrent_period(String current_period) {
        this.current_period = current_period;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getSummed() {
        return summed;
    }

    public void setSummed(String summed) {
        this.summed = summed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    @Override
    public String toString() {
        return borrow_id + " " + reg_id + " " + mpesa + " " + id_no + " " + name + " " + phone + " " +
                current_period + " " + status + " " + reg_date + " " + total + " " + remarks;
    }
}
