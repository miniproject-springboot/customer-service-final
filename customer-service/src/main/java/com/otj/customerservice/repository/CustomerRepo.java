package com.otj.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.otj.customerservice.model.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long>{

}
