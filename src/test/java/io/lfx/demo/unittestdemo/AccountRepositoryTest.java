package io.lfx.demo.unittestdemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import io.lfx.demo.unittestdemo.domain.entities.Account;
import io.lfx.demo.unittestdemo.infrastructure.repositories.AccountRepository;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest(classes = UnittestdemoApplication.class)
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class,
    TransactionDbUnitTestExecutionListener.class
})
@TestPropertySource(properties = {
    "spring.jpa.hibernate.ddl-auto=create-drop"
})
public class AccountRepositoryTest {
   @Autowired
   private AccountRepository accounts;

   @Test
   public void testSave() {
      Account account = Account.builder().name("Test Account").build();
      accounts.save(account);
      Account result = accounts.findById(account.getId()).get();
      assertEquals(account.getId(), result.getId());	     
   }

   @Test
   @DatabaseSetup("/createAccount.xml")
   public void testFindAll() {
        assertEquals(3, StreamSupport.stream(this.accounts.findAll().spliterator(), false).count());
   }
   
}