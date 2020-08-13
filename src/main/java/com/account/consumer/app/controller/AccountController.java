package com.account.consumer.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.account.consumer.app.entity.Account;
import com.account.consumer.app.exception.AccountExsistedException;
import com.account.consumer.app.model.AccountDto;
import com.account.consumer.app.service.AccountService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RefreshScope
@RestController
@RequestMapping(value = "/account")
public class AccountController {

	private final AccountService accountService;

	@GetMapping("/findAll")
	public List<Account> findAllAccount() {

		return accountService.findAllAccount();
	}

	@GetMapping("/findAccount/{accName}")
	public Account findAccountByName(@PathVariable("accName") String accountName) {

		return accountService.findAccountByName(accountName);
	}

	@PostMapping("/create")
	public String addAccount(@Valid @RequestBody AccountDto account) throws AccountExsistedException {

		return accountService.addAccount(account);
	}

}
