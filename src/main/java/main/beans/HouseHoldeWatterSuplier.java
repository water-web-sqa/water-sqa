package main.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import main.entity.HouseHold;
import main.entity.WaterSupplier;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseHoldeWatterSuplier {
    private HouseHold houseHold;
    private WaterSupplier waterSupplier;
}

