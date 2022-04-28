package main.service;

import main.beans.WrapperResponse;
import main.entity.CustomerRegister;

import java.util.HashMap;
import java.util.List;

public interface CustomerRegisterService {
    List<CustomerRegister> allCustomerRegister();
    void deleteCustomerRegister(Integer id);
    void updateStatus(Integer status, Integer id);
    CustomerRegister findById(Integer id);
    void save(CustomerRegister customerRegister);

    WrapperResponse<Boolean> addCustomerResigter(CustomerRegister customerRegister);
    HashMap<String, Object> getAllRegisterRequest();
    WrapperResponse<Boolean> setStatusRequest(Integer type, Integer id);
    WrapperResponse<Boolean> updateHouseHold(CustomerRegister customerRegister);
}
