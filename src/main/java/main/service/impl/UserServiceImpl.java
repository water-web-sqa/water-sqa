package main.service.impl;

import javax.transaction.Transactional;

import main.common.StringConst;
import main.entity.User;
import main.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import main.repository.UserRepository;

@Service
@Repository
@Transactional(rollbackOn = Exception.class)
@EnableTransactionManagement(proxyTargetClass = true)
public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public User getUserInfoByName(String username) {
        try {
            User result = userRepository.findByUserNameAndDeleteFlg(username, StringConst.DELETE_FLG.NON_DELETE);
            if (result == null || null == result.getUserName()) {
                throw new RuntimeException("username not exist!");
            } else {
                return result;
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }
}
