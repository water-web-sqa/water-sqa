package main.repository;

import main.entity.HouseHold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface HouseHoldRepository extends JpaRepository<HouseHold, String> {
    @Query(value = "SELECT * FROM quanlynuoc.household\n" +
            "WHERE address LIKE ?\n" +
            "AND address LIKE ?\n" +
            "AND address LIKE ?\n" +
            "AND name_house LIKE ?", nativeQuery = true)
    List<HouseHold> findByAddressAndName(String city, String district, String ward, String name);

    HouseHold findByCodeHouse(String codeHouse);
}
