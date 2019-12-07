package com.fcellini.challenge.models;

import java.time.Instant;
import java.util.UUID;

public class Transaction {
    private UUID id;
    private TransactionType type;
    private Double amount;
    private Instant effectiveDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID newId) {
        this.id = newId;
    }

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
    
    public Instant getEffectiveDate() {
        return effectiveDate;
    }
    
    public void setEffectiveDate(Instant newDate) {
        this.effectiveDate = newDate;
    }
}
