package main.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import main.entity.Bill;
import main.entity.HouseHold;
import main.entity.WaterMoney;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSearchBillResponse {
    private HouseHold houseHold;
    private WaterMoney waterMoney;
    private Bill bill;
    private String nameSupplier;
}
