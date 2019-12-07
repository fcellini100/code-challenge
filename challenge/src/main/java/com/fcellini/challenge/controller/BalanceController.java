package com.fcellini.challenge.controller;

import com.fcellini.challenge.models.Balance;
import com.fcellini.challenge.services.AccountService;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("balance")
@CrossOrigin
@Api(value = "balance", tags = "Balance")
public class BalanceController {

    private AccountService accountSrv;

    @Autowired
    public BalanceController(AccountService accountSrv) {
    	
        this.accountSrv = accountSrv;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Balance> getBalance(){
    	
        return ResponseEntity.ok(accountSrv.getBalance());
        
    }
}
