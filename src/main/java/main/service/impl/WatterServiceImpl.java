package main.service.impl;

import lombok.extern.log4j.Log4j2;
import main.entity.WaterSupplier;
import main.repository.WatterRepository;
import main.service.WatterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import javax.xml.ws.Action;
import java.nio.file.WatchService;
import java.util.List;

@Service
@Repository
@Transactional(rollbackOn = Exception.class)
@EnableTransactionManagement(proxyTargetClass = true)
@Log4j2
public class WatterServiceImpl implements WatterService {

    @Autowired
    WatterRepository watterRepository;

    @Override
    public List<WaterSupplier> getListSupplier(){
        try {
            return watterRepository.findAll();
        }
        catch (Exception ex){
            log.error(ex.getMessage(), ex);
        }
        return null;
    }
}
