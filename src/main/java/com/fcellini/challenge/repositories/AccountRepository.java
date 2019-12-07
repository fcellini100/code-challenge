package com.fcellini.challenge.repositories;

import com.fcellini.challenge.models.Transaction;
import com.fcellini.challenge.storage.AccountDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AccountRepository {

    private AccountDatabase db;

    @Autowired
    public AccountRepository(AccountDatabase db) {
        this.db = db;
    }
    
    public Transaction saveTransaction(Transaction trx){
        return db.updateBalance(trx);
    }

    public List<Transaction> getTransactions(){
        return db.getTransactions();
    }

    public Optional<Transaction> getTransactionById(UUID id){
        return db.getTransactionById(id);
    }

    public double getBalance(){
        return db.getBalance();
    }
}
