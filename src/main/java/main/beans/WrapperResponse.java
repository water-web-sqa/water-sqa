package main.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.formula.functions.T;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WrapperResponse<T> {
    private String msg;
    private Integer status;
    private T body;
}
