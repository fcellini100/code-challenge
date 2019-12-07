package com.fcellini.challenge.services;

import com.fcellini.challenge.models.Balance;
import com.fcellini.challenge.models.TrxRequest;
import com.fcellini.challenge.models.Transaction;

import com.fcellini.challenge.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {

	
    private AccountRepository accountRepo;

    @Autowired
    public AccountService(AccountRepository accountRepo) {
    	
        this.accountRepo = accountRepo;
        
    }

    public Transaction saveTransaction(TrxRequest request){
    	
        Transaction trx = new Transaction();
        
        trx.setType(request.getType());
        trx.setAmount(request.getAmount());
        
        return accountRepo.saveTransaction(trx);
        
    }

    public List<Transaction> getTransactions(){
        return accountRepo.getTransactions();
    }

    public Balance getBalance(){
    	
        Balance balance = new Balance();
        balance.setBalance(accountRepo.getBalance());
        
        return balance;
    }

    public Optional<Transaction> getTransactionById(String id){
        return accountRepo.getTransactionById(UUID.fromString(id));
    }
}
