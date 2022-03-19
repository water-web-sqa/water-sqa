package main.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WaterMoneyUpdateBeans {
    private String codeHouse;
    private int numberWater;
    private Date dateWater;
}
