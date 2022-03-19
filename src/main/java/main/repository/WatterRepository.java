package main.repository;

import main.entity.HouseHold;
import main.entity.WaterSupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface WatterRepository extends JpaRepository<WaterSupplier, Long>, QueryByExampleExecutor<WaterSupplier> {
    List<WaterSupplier> findAll();

    WaterSupplier findById(Integer id);
}
