package main.service;

import main.entity.WaterSupplier;

import java.util.List;

public interface WatterService {
    List<WaterSupplier> getListSupplier() throws Exception;

    WaterSupplier findById(Integer id);
}
