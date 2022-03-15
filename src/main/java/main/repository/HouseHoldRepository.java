package main.repository;

import main.entity.HouseHold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseHoldRepository extends JpaRepository<HouseHold, String> {
    List<HouseHold> findAllByAddressContaining(String address);
}
