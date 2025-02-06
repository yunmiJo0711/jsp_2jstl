package vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor  // 기본생성자
@AllArgsConstructor // 커스텀생성자(모든값 초기화)
@ToString
public class UserAccountVO {
	private String userid;
	private String username;
	private String password;
	private String birth;
	private String gender;
	private String email;


}
