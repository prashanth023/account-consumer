package com.account.consumer.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.account.consumer.app.entity.Account;
import com.account.consumer.app.exception.AccountExsistedException;
import com.account.consumer.app.exception.AccountNotFoundException;
import com.account.consumer.app.exception.AccountServiceException;
import com.account.consumer.app.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AccountService {

	private final AccountRepository accountRepository;

	public List<Account> findAllAccount() {

		List<Account> accounts = null;
		try {

			accounts = accountRepository.findAll();
			
			if (accounts.isEmpty()) {

				throw new AccountNotFoundException("Account not found");
			}

		} catch (AccountServiceException e) {

			throw new AccountServiceException("Internal  server error");
		}

		return accounts;
	}

	public Account findAccountByName(final String accountName) {

		Account account = null;

		try {
			
			account = accountRepository.findByAccountName(accountName);
			if (account == null) {
				
				throw new AccountNotFoundException("Account not found");
			}

		} catch (AccountServiceException e) {

			throw new AccountServiceException("Internal  server error");
		}

		return account;
	}

	public String  addAccount(Account account) throws AccountExsistedException {
		
		String status=null;
		
		try {
			
			accountRepository.save(account);
			
		}catch (Exception e) {

			throw new AccountExsistedException("Account already existed");
		}
		

		return status;
	}
	
}