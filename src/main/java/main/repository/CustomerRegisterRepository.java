package main.repository;

import main.entity.CustomerRegister;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRegisterRepository extends JpaRepository<CustomerRegister, String> {

}
