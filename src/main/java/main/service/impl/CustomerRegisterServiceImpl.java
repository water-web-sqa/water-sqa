package main.service.impl;

import main.entity.CustomerRegister;
import main.repository.CustomerRegisterRepository;
import main.service.CustomerRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Repository
@Transactional(rollbackOn = Exception.class)
@EnableTransactionManagement(proxyTargetClass = true)
public class CustomerRegisterServiceImpl implements CustomerRegisterService {
    @Autowired
    CustomerRegisterRepository customerRegisterRepository;

    @Override
    public List<CustomerRegister> allCustomerRegister() {
        return customerRegisterRepository.findAllByStatusNotLike(2);
    }

    @Override
    public void deleteCustomerRegister(Integer id) {
        try {
            customerRegisterRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void updateStatus(Integer status, Integer id) {
        try {
            customerRegisterRepository.updateStatus(status, id);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public CustomerRegister findById(Integer id) {
        return customerRegisterRepository.findById(id).get();
    }

    @Override
    public void save(CustomerRegister customerRegister) {
        try {
            customerRegisterRepository.save(customerRegister);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
