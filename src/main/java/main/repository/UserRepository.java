package main.repository;

import main.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface UserRepository extends JpaRepository<User, Long>, QueryByExampleExecutor<User> {
	/**
	 * @param username
	 * @return
	 */
	public User findByUserNameAndDeleteFlg(String username, String deleteFlg);
}
