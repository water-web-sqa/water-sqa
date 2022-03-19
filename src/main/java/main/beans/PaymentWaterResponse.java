package main.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentWaterResponse {
    private int numberWater;
    private String dateWater;
    private String priceBybac;
    private int sumPrice;
    private int idWaterMoney;
}
