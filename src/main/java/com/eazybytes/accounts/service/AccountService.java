package com.eazybytes.accounts.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.eazybytes.accounts.Exception.CustomerAlreadyExistsException;
import com.eazybytes.accounts.Exception.ResourceNotFoundException;
import com.eazybytes.accounts.constants.AccountConstants;
import com.eazybytes.accounts.dto.AccountsDto;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.mapper.AccountsMapper;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.repository.CustomerRepository;

@Service
public class AccountService implements IAccountService {
    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mappingCustomer(new Customer(), customerDto);
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException(
                    "Customer already registered with given mobile number" + customerDto.getMobileNumber());
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymus");
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));

    }

    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        newAccount.setAccountNumber(1000000000L + new Random().nextInt(900000000));
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Anonymus");
        return newAccount;
    }

    @Override
    public CustomerDto fetchAccounts(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "mobileNumber", customer.getCustomerId().toString()));
                CustomerDto customerDto=CustomerMapper.mappingCustomerDto( new CustomerDto(),customer);
                customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(account,new AccountsDto()));

                return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
       boolean isUpdate=false;
       AccountsDto accountsDto=customerDto.getAccountsDto();
       if(accountsDto!=null){
        Accounts accounts=accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
            ()->new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
        );
        AccountsMapper.mapToAccounts(accountsDto,accounts);

        Long customerId=accounts.getCustomerId();
        Customer customer=customerRepository.findById(customerId).orElseThrow(
            ()->new ResourceNotFoundException("Customer", "CustomerId",customerId.toString() 
        )
        );
        CustomerMapper.mappingCustomer(customer, customerDto);
        customerRepository.save(customer);
        isUpdate=true;
       }
       return isUpdate;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer=customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
            ()->new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }

}
