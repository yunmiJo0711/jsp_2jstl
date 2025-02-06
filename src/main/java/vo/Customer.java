package vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
// import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// 롬복을 사용합니다.
@Getter
@Setter
@ToString
@AllArgsConstructor   // 모든 프로퍼티값 초기화하는 생성자 코드
@RequiredArgsConstructor     // final 변수만 초기화하는 커스텀 생성자
// @NoArgsConstructor     // final 변수가 있으면 기본 생성자 사용 못합니다.
                    // 기본생성자 사용하려면 변수선언시 값을 정해야 합니다.
public class Customer {
    private final String customId;
    private final String name;
    private final String email;
    private final int age;
    private final Date regDate;
    private String postcode;

    
}

// ORM 프레임웍을 사용하면 기본생성자, setter 가 필요합니다.