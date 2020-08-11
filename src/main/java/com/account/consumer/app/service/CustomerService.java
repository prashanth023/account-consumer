package com.account.consumer.app.service;

import org.springframework.stereotype.Service;

import com.account.consumer.app.entity.Customer;
import com.account.consumer.app.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomerService {

	private final CustomerRepository customerRepository;
	
	public Customer findByCustomerId(final Integer id) {
		
		return customerRepository.findById(id).get();
	}
}
