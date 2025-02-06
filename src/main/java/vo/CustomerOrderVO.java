package vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class CustomerOrderVO {
    private String pname;
    private int price;
    private int quantity;
    private Date buy_date;
    private String category;

}
