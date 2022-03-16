package main.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseHoldBeans {
    private String city, distrinct, ward;
    private String namehouse, codehouse;
}
