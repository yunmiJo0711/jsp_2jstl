package vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter   
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FanItemVO {
	private int seq;        //순서
	private String title;   //상품명
	private int price;		//가격
	private int newitem;	//신상품이면 1
	private int soldout;	//품절이면 1
	private String filename;  //상품이미지
}

