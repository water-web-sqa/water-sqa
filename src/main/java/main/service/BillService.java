package main.service;

import main.entity.Bill;

public interface BillService {
    Bill getBillByWaterNumber(Integer id);
}
