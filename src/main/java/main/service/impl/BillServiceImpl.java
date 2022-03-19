package main.service.impl;

import main.entity.Bill;
import main.repository.BillRespository;
import main.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BillServiceImpl implements BillService {
    @Autowired
    BillRespository billRespository;

    @Override
    public Bill getBillByWaterNumber(Integer id) {
        return billRespository.getBillByIdWaterMoney(id);
    }
}
