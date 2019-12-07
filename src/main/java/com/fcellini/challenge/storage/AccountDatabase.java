package com.fcellini.challenge.storage;

import com.fcellini.challenge.models.Transaction;
import com.fcellini.challenge.models.TransactionType;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

@Component
public class AccountDatabase {
    private double balance;
    private Map<UUID,Transaction> transactionMap;

    private ReadWriteLock lock = new ReentrantReadWriteLock();
    
    public AccountDatabase() {
        balance = 500.0;
        transactionMap = new HashMap<>();
    }
    
    public double getBalance(){
        lock.readLock().lock();
        try{
            return balance;
        }finally {
            lock.readLock().unlock();
        }
    }

    public Transaction updateBalance(Transaction trx){
        lock.writeLock().lock();
        try{
            if(trx.getType().equals(TransactionType.CREDIT)){
                balance += trx.getAmount();
            }else{
                
            	double tempBalance = balance;
                tempBalance -= trx.getAmount();
                
                if(tempBalance < 0){
                	throw new IllegalStateException();
                }
                
                balance = tempBalance;
            }
            
            trx.setEffectiveDate(Instant.now());
            trx.setId(UUID.randomUUID());
            transactionMap.put(trx.getId(),trx);
            return trx;
        }finally {
            lock.writeLock().unlock();
        }
    }
    
    public List<Transaction> getTransactions(){
        lock.readLock().lock();
        try{
            return transactionMap.values().stream()
                    .sorted(Comparator.comparing(Transaction::getEffectiveDate).reversed())
                    .collect(Collectors.toList());
        }finally {
            lock.readLock().unlock();
        }
    }
    
    public Optional<Transaction> getTransactionById(UUID trxId){
        lock.readLock().lock();
        try{
            return Optional.ofNullable(transactionMap.get(trxId));
        }finally {
            lock.readLock().unlock();
        }
    }
    
}
