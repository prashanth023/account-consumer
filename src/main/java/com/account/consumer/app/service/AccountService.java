package com.account.consumer.app.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.account.consumer.app.entity.Account;
import com.account.consumer.app.entity.Customer;
import com.account.consumer.app.exception.AccountExsistedException;
import com.account.consumer.app.exception.AccountNotFoundException;
import com.account.consumer.app.exception.AccountServiceException;
import com.account.consumer.app.model.AccountDto;
import com.account.consumer.app.model.CustomerDto;
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

	public String addAccount(AccountDto accountDto) throws AccountExsistedException {

		String status = null;
		Account account = null;

		try {

			account = convertAccountToEntity(accountDto);
			accountRepository.save(account);
			status = "Account created";

		} catch (Exception e) {

			throw new AccountExsistedException("Account already existed");
		}

		return status;
	}

	private Account convertAccountToEntity(AccountDto account) {

		Account acc = new Account();
		acc.setAccountName(account.getAccountName());
		acc.setAccountType(account.getAccountType());
		acc.setMarketCap(account.getMarketCap());

		if (account.getCustomer() != null) {

			Set<CustomerDto> customers = account.getCustomer();
			Set<Customer> customrs = new HashSet<Customer>();
			Iterator<CustomerDto> customerIt = customers.iterator();
			while (customerIt.hasNext()) {

				CustomerDto customerDto = customerIt.next();
				Customer customer = new Customer();
				customer.setCustomerName(customerDto.getCustomerName());
				customer.setCustomerNum(customerDto.getCustomerNum());
				customrs.add(customer);
			}

			acc.setCustomer(customrs);
		}

		return acc;
	}

	public String addAccount(Account account) throws AccountExsistedException {

		String status = null;

		try {

			accountRepository.save(account);
			status = "Account created";

		} catch (Exception e) {

			throw new AccountExsistedException("Account already existed");
		}

		return status;
	}
}