package main.repository;

import main.entity.CustomerRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRegisterRepository extends JpaRepository<CustomerRegister, Integer> {
    List<CustomerRegister> findAllByStatusNotLike(Integer status);

    @Query(value = "UPDATE customer_register SET status = ? WHERE (`id` = ?)", nativeQuery = true)
    @Modifying
    void updateStatus(Integer status, Integer id);
}
