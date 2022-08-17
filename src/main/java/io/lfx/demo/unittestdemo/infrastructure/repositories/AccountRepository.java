package io.lfx.demo.unittestdemo.infrastructure.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import io.lfx.demo.unittestdemo.domain.entities.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {

    @Query("select u from Account u where u.enabled = :enabled")
    List<Account> getEnabled(@Param("enabled") Boolean enabled) ;
}