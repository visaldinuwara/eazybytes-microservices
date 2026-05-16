package com.eazybytes.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Accounts extends BaseEntity{
    //@GeneratedValue(strategy = GenerationType.AUTO,generator="native")
    //@GenericGenerator(name = "native",strategy = "native")  Depricated in recent spring versions
    @Column(name="customer_id")
    private Long customerId;
    @Column(name="account_number")
    @Id
    private Long accountNumber;
    @Column(name="cloun_type")
    private String accountType;
    @Column(name="branch_address")
    private String branchAddress;
    
}