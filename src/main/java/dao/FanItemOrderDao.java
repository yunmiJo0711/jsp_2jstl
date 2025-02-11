package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import vo.FanItemOrderVO;

public class FanItemOrderDao {
	// 싱글톤 패턴 정의
    private static FanItemOrderDao dao = new FanItemOrderDao();
    private FanItemOrderDao() { }
    public static FanItemOrderDao getInstance(){
      return dao;
    }

    // db연결 정보를 상수로 정의합니다.
    private static final String URL = "jdbc:oracle:thin:@//localhost:1521/xe";
    private static final String USERNAME ="c##idev";
    private static final String PASSWORD ="1234";
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    
    // db연결 객체 만드는 메소드를 dao 에서만 사용할 수 있도록 정의
    private Connection getConnection() throws SQLException {
    	try {
			Class.forName(DRIVER);  // jsp 사용하는 웹프로젝트에서는 생략 못합니다. 
		} catch (ClassNotFoundException e) {
			System.out.println("예외 : " + e.getMessage());
		}
      return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    // sql 실행 메소드 정의 -> 각 sql connection 만들어서 사용하고 sql 실행 후 close()
    public int insert(FanItemOrderVO vo ){
      int result=0;
      String sql="INSERT INTO tbl_fanitem_order(order_seq, userid, seq, count, pay) " +
                 "VALUES (tblorder_seq.nextval, ?, ?, ?, ?) ";  // sequence tblorder_seq 을 기본키 컬럼은 유일한 값이여야 하는데 number 타입일 때, 값을 직접 입력하지 않고 시쿼스 이용합니다. 
      															// '다음' 시퀀스 값 가져오는 함수 nextval, '현재' 시퀀스 값 가져오는 함수 currval
      try (Connection connection = getConnection();
           PreparedStatement pstmt = connection.prepareStatement(sql);
          ) {
            pstmt.setString(1, vo.getUserId());
            pstmt.setInt(2, vo.getSeq());
            pstmt.setInt(3, vo.getCount());
            pstmt.setInt(4, vo.getPay());

            result = pstmt.executeUpdate();
      } catch (Exception e) {
            System.out.println("예외 : " + e.getMessage());
      }
      return result;
    }

}
