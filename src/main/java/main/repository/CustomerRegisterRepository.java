package main.repository;

import main.entity.CustomerRegister;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRegisterRepository extends JpaRepository<CustomerRegister, String> {
    List<CustomerRegister> findAllByStatus(Integer status);
}
