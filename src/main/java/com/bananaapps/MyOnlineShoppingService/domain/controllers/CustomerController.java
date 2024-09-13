package com.bananaapps.MyOnlineShoppingService.domain.controllers;

import com.bananaapps.MyOnlineShoppingService.domain.dto.response.CustomerDto;
import com.bananaapps.MyOnlineShoppingService.domain.entities.Customer;
import com.bananaapps.MyOnlineShoppingService.domain.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(
        value = "/customers",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        consumes = {MediaType.APPLICATION_JSON_VALUE}
)
public class CustomerController {
    private CustomerService customerService;

    @GetMapping()
    public ResponseEntity<List<CustomerDto>> getCustomers() {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getAllCustomers());
    }
    @PostMapping
    public ResponseEntity<CustomerDto> newCustomer(@Valid @RequestBody CustomerDto customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customer));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> editCustomer(@PathVariable("id") Long id, @Valid @RequestBody CustomerDto customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.updateCustomer(id, customer));
    }
    @DeleteMapping("/id")
    public ResponseEntity<Object> deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
