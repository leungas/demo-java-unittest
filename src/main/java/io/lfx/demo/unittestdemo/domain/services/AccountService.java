package io.lfx.demo.unittestdemo.domain.services;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.naming.NameNotFoundException;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import io.lfx.demo.unittestdemo.domain.entities.Account;
import io.lfx.demo.unittestdemo.domain.exceptions.NotFoundException;
import io.lfx.demo.unittestdemo.infrastructure.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {
    
    private final AccountRepository accounts;
    
    public Account create(String name) {
        Account item = Account
            .builder()
            .name(name).build();        
        return this.accounts.save(item);
    }

    public void disable(Long id) {
        Optional<Account> model = this.get(id);
        if (model.isEmpty()) throw new NotFoundException();
        Account item = model.get();
        item.setEnabled(false);
        this.accounts.save(item);
    }

    public Optional<Account> get(Long id) {
        return this.accounts.findById(id);
    }

    public List<Account> getEnabled() {
        return this.accounts.getEnabled(true);
    }

    public Iterable<Account> list() {
        return this.accounts.findAll();
    }

    public void remove(Long id) {
        Optional<Account> model = this.get(id);
        if (model.isEmpty()) throw new NotFoundException();
        this.accounts.delete(model.get());
    }

    public Account update(Long id, String name) {
        Optional<Account> model = this.get(id);
        if (model.isEmpty()) throw new NotFoundException();
        Account item = model.get();
        item.setName(name);
        return this.accounts.save(item);
    }
}
