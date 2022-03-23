package main.service.impl;

import main.entity.Bill;
import main.repository.BillRespository;
import main.service.BillService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BillServiceImpl implements BillService {
    private static final Logger logger = LogManager.getLogger(BillServiceImpl.class);

    @Autowired
    BillRespository billRespository;

    @Override
    public Bill getBillByWaterNumber(Integer id) {
        try {
            return billRespository.getBillByIdWaterMoney(id);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }
}
