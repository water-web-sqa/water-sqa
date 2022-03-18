package main.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseHoldBeans {
    private String city, distrinct, ward;
    private String namehouse, codehouse;
    private Date timesearch;
}
