package main.service;

import main.beans.WrapperResponse;
import main.entity.HouseHold;
import main.entity.WaterSupplier;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface WatterService {
    List<WaterSupplier> getListSupplier();

    WaterSupplier findById(Integer id);
}
