package local.alfonso.orders.controller;

import local.alfonso.orders.model.Customers;
import local.alfonso.orders.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomersService customersService;

    @GetMapping(value = "/order", produces = {"application/json"})
    public ResponseEntity<?> listAllCustomers()
    {
        ArrayList<Customers> rtnCus = customersService.findAll();
        return new ResponseEntity<>(rtnCus, HttpStatus.OK);
    }
}
