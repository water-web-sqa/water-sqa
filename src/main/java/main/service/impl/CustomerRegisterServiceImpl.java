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
}
