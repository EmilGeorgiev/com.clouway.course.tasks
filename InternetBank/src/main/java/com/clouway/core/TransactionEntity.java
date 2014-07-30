package com.clouway.core;

import java.util.Date;

public class TransactionEntity {

    private String type;
    private String userName;
    private Double amount;
    private Date date;

    public TransactionEntity(String type, String userName, Double amount, Date date) {
        this.type = type;
        this.userName = userName;
        this.amount = amount;
        this.date = date;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public String getUserName() {
        return userName;
    }

    public Double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransactionEntity that = (TransactionEntity) o;

        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
