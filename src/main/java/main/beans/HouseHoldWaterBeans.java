package main.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import main.entity.HouseHold;
import main.entity.WaterMoney;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseHoldWaterBeans {
    private HouseHold houseHold;
    private WaterMoney nowWater;
}
