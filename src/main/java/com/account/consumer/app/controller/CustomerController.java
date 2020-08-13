package com.account.consumer.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.account.consumer.app.entity.Customer;
import com.account.consumer.app.service.CustomerService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

	private final CustomerService customerService;

	@GetMapping(value = "/findCustomer/{id}")
	public Customer findByCustomerId(@PathVariable("id") Integer id) {

		return customerService.findByCustomerId(id);
	}

}
