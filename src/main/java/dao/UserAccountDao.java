package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.UserAccountVO;

// sql 실행을 위한 메소드로만 구성 -> 객체는 한번만 생성해도 된다.(싱글톤 패턴)
// 참고 : vo 는 데이터를 저장하는 것이 목적
//				--> 데이터가 달라질 때 객체 생성하여 저장
public class UserAccountDao {
	private static UserAccountDao dao = new UserAccountDao();
	private UserAccountDao() {	}
	public static UserAccountDao getInstance() {
		return dao;
	}
	
	// db 연결 정보를 상수로 정의합니다.
    private static final String URL = "jdbc:oracle:thin:@//localhost:1521/xe";
    private static final String USERNAME = "c##idev";
    private static final String PASSWORD = "1234";
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    
 // db 연결 객체 만드는 메소드 dao 에서만 사용할 수 있도록 정의
    private Connection getConnection() throws SQLException{
    	try {
			Class.forName(DRIVER);  // jsp 사용하는 웹프로젝트에서는 생략 못합니다. 
		} catch (ClassNotFoundException e) {
			System.out.println("예외 : " + e.getMessage());
		}
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

	
	// 회원가입은 - db 테이블에 insert
    // 		   - db 테이블에 not null 컬럼 5개 값은 반드시 vo 에 저장되어있어야 함.
    // 						ㄴ 값이 null 이면 예외 발생
    //						ㄴ 사용자가 입력한 내용을 유효 여부 검증 -> 자바스크립트(클라이언트:브라우저)에서 먼저 실행 후 서버에 넘겨준다.
	public int insert(UserAccountVO vo) {
		int result=0;
		String sql = "INSERT INTO tbl_user_account(userid, username, password, birth, gender, email)" +
						"VALUES(?, ?, ?, ?, ?, ?)";
		try(Connection connection = getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql);
		){
			pstmt.setString(1, vo.getUserid());
			pstmt.setString(2, vo.getUsername());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getBirth());
			pstmt.setString(5, vo.getGender());
			pstmt.setString(6, vo.getEmail());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("예외 : " + e.getMessage());
		}
		
		return result;
	}
	
	// select 조회 결과가 최대 1개 행이므로 vo 타입으로 리턴 - 0 또는 1개 행이 결과값
	public UserAccountVO selectForLogin(String userid, String password) {
		UserAccountVO vo=null;
		String sql = "SELECT * FROM tbl_user_account WHERE userid= ? and password= ?";
		try(Connection connection = getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql);
		) {
			 pstmt.setString(1, userid);
			 pstmt.setString(2, password);
             ResultSet rs = pstmt.executeQuery();
             if(rs.next()){
            	 // 모든 컬럼 매핑
//            	 vo = new UserAccountVO( rs.getString(1),
//				            			 rs.getString(2),
//				            			 rs.getString(3),
//				            			 rs.getString(4),
//				            			 rs.getString(5),
//				            			 rs.getString(6));
            	 // 일부 컬럼만 매핑
            	 vo = new UserAccountVO(
            			 			rs.getString(1),
            			 			rs.getString(2),
            			 			null,  // 매핑할 필요가 없는 컬럼은 null 처리.
            			 			null,
            			 			null,
            			 			rs.getString(6));
                                    
             }
			
		} catch (Exception e) {
			System.out.println("예외 : " + e.getMessage());
		}
		return vo;  // 조회 결과(행)가 없으면 vo는  null 
	}

}
