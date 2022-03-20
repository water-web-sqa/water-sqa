package main.service;

import main.entity.CustomerRegister;

import java.util.Date;
import java.util.List;

public interface CustomerRegisterService {
    List<CustomerRegister> allCustomerRegister();
    void deleteCustomerRegister(Integer id);
    void updateStatus(Integer status, Integer id);
    CustomerRegister findById(Integer id);
    void save(CustomerRegister customerRegister);
}
