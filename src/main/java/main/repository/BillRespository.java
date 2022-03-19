package main.repository;

import main.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface BillRespository extends JpaRepository<Bill, Integer>, QueryByExampleExecutor<Bill> {

    @Modifying
    @Query(value = "INSERT INTO bill (id_staff, id_water_money, sum_money) VALUES (?, ?, ?);", nativeQuery = true)
    void saveWaterMoney(Integer idStaff, Integer idWaterMoney, Float sumMoney);
}
