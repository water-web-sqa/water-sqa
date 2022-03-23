package main.service.impl;

import lombok.extern.log4j.Log4j2;
import main.entity.WaterSupplier;
import main.repository.WatterRepository;
import main.service.WatterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Repository
@Transactional(rollbackOn = Exception.class)
@EnableTransactionManagement(proxyTargetClass = true)
@Log4j2
public class WatterServiceImpl implements WatterService {
    private static final Logger logger = LogManager.getLogger(WatterServiceImpl.class);

    @Autowired
    WatterRepository watterRepository;

    @Override
    public List<WaterSupplier> getListSupplier(){
        try {
            return watterRepository.findAll();
        }
        catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public WaterSupplier findById(Integer id) {
        try {
            return watterRepository.findById(id);
        }
        catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }
}
