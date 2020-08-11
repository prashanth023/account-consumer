package com.account.consumer.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.account.consumer.app.entity.AccountType;


@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Integer>{

}
