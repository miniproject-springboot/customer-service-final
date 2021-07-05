package com.otj.customerservice.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.otj.customerservice.model.Customer;
import com.otj.customerservice.repository.CustomerRepo;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@GetMapping("service1/customers")
	public List<Customer> getCustomers() {
		return customerRepo.findAll();
	}
	
	@PostMapping("service1/customer")
	public ResponseEntity<Object> createCustomer(@RequestBody Customer customer ) {
		
		Customer savedCustomer =customerRepo.save(customer);
		
		//new RestTemplate().postForLocation("http://localhost:8002/service3/customer", customer.getClass());
		
		ResponseEntity<String> result = new RestTemplate().postForEntity("http://localhost:8002/service3/customer", customer, String.class);
		
		URI location = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(savedCustomer.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}

}
