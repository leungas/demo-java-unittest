package io.lfx.demo.unittestdemo.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.lfx.demo.unittestdemo.app.dto.AccountDto;
import io.lfx.demo.unittestdemo.domain.entities.Account;
import io.lfx.demo.unittestdemo.domain.services.AccountService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AccountController {

    @Autowired
    private AccountService service;

    @GetMapping("accounts")
    public Iterable<Account> all() {
        return this.service.list();
    }

    @PostMapping("accounts") 
    public Account create(@RequestBody() AccountDto request) {
        return this.service.create(request.getName());
    }

    @PutMapping("accounts/disable/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disable(@PathVariable("id") Long accountId) {
        this.service.disable(accountId);
    }

    @GetMapping("accounts/search/enabled")
    public Iterable<Account> enabled() {
        return this.service.getEnabled();
    }    

    @GetMapping("accounts/search/id/{id}")
    public Account get(@PathVariable("id") Long accountId) {
        return this.service.get(accountId).get();
    }   
    
    @PatchMapping("accounts/{id}")
    public Account update(@PathVariable("id") Long accountId, @RequestBody() AccountDto request) {
        return this.service.update(accountId, request.getName());
    }
    
    @DeleteMapping("accounts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable("id") Long accountId) {
        this.service.remove(accountId);
    }
        
}
