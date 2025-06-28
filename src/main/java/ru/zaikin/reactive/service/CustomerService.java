package ru.zaikin.reactive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ru.zaikin.reactive.dao.CustomerDao;
import ru.zaikin.reactive.dto.Customer;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao dao;
    public List<Customer> loadAllCustomers() throws InterruptedException {
        long start = System.currentTimeMillis();
        List<Customer> customers = dao.getCustomers();
        long end = System.currentTimeMillis();
        System.out.println("exct time is " + (end - start));
        return customers;
    }

    public Flux<Customer> loadAllCustomersReactive() throws InterruptedException {
        long start = System.currentTimeMillis();
        Flux<Customer> customers = dao.getCustomersReactive();
        long end = System.currentTimeMillis();
        System.out.println("exct time is " + (end - start));
        return customers;
    }
}
