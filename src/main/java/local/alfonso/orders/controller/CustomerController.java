package local.alfonso.orders.controller;

import local.alfonso.orders.model.Customers;
import local.alfonso.orders.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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

    @GetMapping(value = "/name/{name}", produces = {"application/json"})
    public ResponseEntity<?> getCustomerByName(@PathVariable String name)
    {
        Customers customer = customersService.findCustomerByName(name);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping(value = "/new", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addNewCustomer(@Valid @RequestBody Customers newCustomer)
    {
        newCustomer = customersService.save(newCustomer);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{customerid}").buildAndExpand(newCustomer.getCustcode()).toUri();

        responseHeaders.setLocation(newCustomerURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{custcode}")
    public ResponseEntity<?> updateCustomerById(@RequestBody Customers updateCustomer, @PathVariable long custcode)
    {
        customersService.update(updateCustomer, custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/update/{custcode}", consumes = {"application/json"})
    public ResponseEntity<?> deleteCustomerById(@PathVariable long custcode)
    {
        customersService.delete(custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
