package com.example.dbeaver.repository.account;

import com.example.dbeaver.entity.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, String> {
    @Query(value = "select top(20) * from Account", nativeQuery = true)
    List<Account> findAll();
}
