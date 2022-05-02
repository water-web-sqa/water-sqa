package main.service.impl;

import main.beans.WrapperResponse;
import main.entity.CustomerRegister;
import main.entity.HouseHold;
import main.repository.CustomerRegisterRepository;
import main.repository.HouseHoldRepository;
import main.service.CustomerRegisterService;
import main.service.HouseHoldService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Repository
@Transactional(rollbackOn = Exception.class)
@EnableTransactionManagement(proxyTargetClass = true)
public class CustomerRegisterServiceImpl implements CustomerRegisterService {
    private static final Logger logger = LogManager.getLogger(CustomerRegisterServiceImpl.class);

    @Autowired
    CustomerRegisterRepository customerRegisterRepository;
    @Autowired
    HouseHoldService houseHoldService;

    @Override
    public List<CustomerRegister> allCustomerRegister() {
        try {
            return customerRegisterRepository.findAllByStatusNotLike(2);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void deleteCustomerRegister(Integer id) {
        try {
            customerRegisterRepository.deleteById(id);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void updateStatus(Integer status, Integer id) {
        try {
            customerRegisterRepository.updateStatus(status, id);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public CustomerRegister findById(Integer id) {
        return customerRegisterRepository.findById(id).get();
    }

    @Override
    public void save(CustomerRegister customerRegister) {
        try {
            customerRegisterRepository.save(customerRegister);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public WrapperResponse<Boolean> addCustomerResigter(CustomerRegister customerRegister) {
        WrapperResponse<Boolean> response = new WrapperResponse<Boolean>();
        try {
            response.setStatus(200);
            response.setBody(true);
            customerRegisterRepository.save(customerRegister);
        } catch (Exception e) {
            response.setStatus(402);
        }
        return response;
    }

    @Override
    public HashMap<String, Object> getAllRegisterRequest() {
        HashMap<String, Object> result = new HashMap<>();
        try {
            List<CustomerRegister> list = allCustomerRegister();
            result.put("draw", 1);
            result.put("recordsTotal", list.size());
            result.put("recordsFiltered", list.size());
            result.put("data", list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public WrapperResponse<Boolean> setStatusRequest(Integer type, Integer id) {
        WrapperResponse<Boolean> result = new WrapperResponse<>();
        try {
            switch (type) {
                case 0: {
                    updateStatus(1, id);
                    break;
                }
                case 1: {
                    deleteCustomerRegister(id);
                    break;
                }
                case 2: {
                    CustomerRegister customerRegister = findById(id);
                    String lastId = houseHoldService.lastId();
                    Integer stt = Integer.parseInt(lastId.substring(3, lastId.length())) + 1;
                    HouseHold newHouseHold = new HouseHold("MDB" + ((stt < 10) ? ("0" + stt) : (stt + "")),
                            customerRegister.getNameHouse(), customerRegister.getAddress(), customerRegister.getDataBirth(),
                            customerRegister.getIdSupplier(), customerRegister.getTypeHouse());
                    houseHoldService.saveHouseHold(newHouseHold);
                    deleteCustomerRegister(id);
                    break;
                }
            }
            result.setStatus(200);
            result.setMsg("Chỉnh sửa thành công");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setStatus(400);
            result.setMsg("Yêu cầu thất bại");
        }
        return result;
    }

    @Override
    public WrapperResponse<Boolean> updateHouseHold(CustomerRegister customerRegister) {
        WrapperResponse<Boolean> response = new WrapperResponse<Boolean>();
        try {
            response.setStatus(200);
            response.setBody(true);
            save(customerRegister);
            return response;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        response.setStatus(200);
        response.setBody(true);
        return null;
    }
}
