package com.account.consumer.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.account.consumer.app.entity.Account;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

	public Account findByAccountName(String accountName);
	
	public List<Account> findByAccountType(Integer accounttype);
	
	public List<Account> findAllById(Iterable<Integer> ids);
}
