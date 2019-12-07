package com.fcellini.challenge.models;

public class TrxRequest {
    private TransactionType type;
    private Double amount;

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType newType) {
        this.type = newType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double newAmount) {
        this.amount = newAmount;
    }
}
