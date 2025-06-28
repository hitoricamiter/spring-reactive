package ru.zaikin.reactive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.zaikin.reactive.dto.Customer;
import ru.zaikin.reactive.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/get")
    public List<Customer> getCustomers() throws InterruptedException {
        return customerService.loadAllCustomers();
    }

    @GetMapping(value = "/reactive", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Customer> getCustomersReactive() throws InterruptedException {
        return customerService.loadAllCustomersReactive();
    }
}
