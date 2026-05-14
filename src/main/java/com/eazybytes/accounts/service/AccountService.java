package com.eazybytes.accounts.service;

import org.springframework.stereotype.Service;

import com.eazybytes.accounts.dto.AccountsDto;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
@Service
public class AccountService implements IAccountService{
    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(AccountsDto accountsDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createAccount'");
    }
    
}
