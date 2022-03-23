package main.repository;

import main.entity.WaterMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WaterMoneyRepository extends JpaRepository<WaterMoney, Integer> {

    @Query(value = "SELECT * FROM water_money \n " +
            "WHERE code_house = ? \n " +
            "AND month(date_water) = ? \n " +
            "AND year(date_water) = ? \n " +
            "ORDER BY created_at DESC \n " +
            "LIMIT 1;", nativeQuery = true)
    WaterMoney findDateWaterLast(String codeHouse, Integer month, Integer year);

    @Query(value = "SELECT A.* FROM water_money AS A " +
            "INNER JOIN ( SELECT date_water, MAX(created_at) AS update_create FROM water_money WHERE code_house = ? GROUP BY date_water) AS B " +
            "ON A.date_water = B.date_water AND A.created_at = B.update_create;", nativeQuery = true)
    List<WaterMoney> getOldestUpdateWaterMoneyByCodeHouse(String codeHouse);
}
