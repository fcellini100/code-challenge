package com.fcellini.challenge.controller;

import com.fcellini.challenge.models.TrxRequest;
import com.fcellini.challenge.models.Transaction;
import com.fcellini.challenge.services.AccountService;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("transaction")
@Api(value = "transaction",tags = "Transactions")
@CrossOrigin
public class TransactionController {

    private AccountService accountSrv;

    @Autowired
    public TransactionController(AccountService accountSrv) {
        this.accountSrv = accountSrv;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Transaction>> getTransactions(){
    	
        return ResponseEntity.ok(accountSrv.getTransactions());
        
    }
    
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Transaction not found"),
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable("id") String id){
    	
        try{
            return ResponseEntity.ok(accountSrv.getTransactionById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Not Found")));
        }catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid UUID");
        }
        
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK."),
            @ApiResponse(code = 409, message = "insufficient founds to co operation."),
    })
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Transaction> addTransaction(@RequestBody TrxRequest request){
    	
        try{
            return ResponseEntity.ok(accountSrv.saveTransaction(request));
        }catch (IllegalStateException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Insufficient funds.");
        }
    }

}
