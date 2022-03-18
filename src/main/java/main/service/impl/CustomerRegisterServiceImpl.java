package main.service.impl;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;

@Service
@Repository
@Transactional(rollbackOn = Exception.class)
@EnableTransactionManagement(proxyTargetClass = true)
public class CustomerRegisterServiceImpl {
}
