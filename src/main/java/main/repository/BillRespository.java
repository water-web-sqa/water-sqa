package main.repository;

import main.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface BillRespository extends JpaRepository<Bill, Integer>, QueryByExampleExecutor<Bill> {
    Bill getBillByIdWaterMoney(Integer id);
}
