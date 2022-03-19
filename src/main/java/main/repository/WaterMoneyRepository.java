package main.repository;

import main.entity.WaterMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WaterMoneyRepository extends JpaRepository<WaterMoney, Integer> {

    @Query(value = "SELECT * FROM water_money \n " +
            "WHERE code_house = ? \n " +
            "AND year(date_water) = YEAR(CURRENT_TIMESTAMP) \n " +
            "AND month(date_water) = MONTH(CURRENT_TIMESTAMP) \n " +
            "ORDER BY date_water DESC \n " +
            "AND month(date_water) = ? \n " +
            "AND year(date_water) = ? \n " +
            "ORDER BY created_at DESC \n " +
            "LIMIT 1;", nativeQuery = true)
    WaterMoney findDateWaterLast(String codeHouse, Integer month, Integer year);

    @Modifying
    @Query(value = "INSERT INTO water_money " +
            "(date_water, number_water, code_house) \n" +
            "VALUES (CURRENT_DATE, ?, ?);", nativeQuery = true)
    void saveWaterMoney(Integer numberWater, String codeHouse);

    @Modifying
    @Query(value = "DELETE FROM water_money\n" +
            "WHERE code_house = ? \n" +
            "AND date_water = CURRENT_DATE", nativeQuery = true)
    void deleteDateWaterNow(String codeHouse);


    List<WaterMoney> getWaterMoneyByCodeHouse(String codehouse);
}
