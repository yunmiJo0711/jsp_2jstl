package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.Customer;

public class TblCustomerDao {

    // 싱글톤 패턴 정의
    private static TblCustomerDao dao = new TblCustomerDao();
    private TblCustomerDao() { }

    public static TblCustomerDao getInstance(){
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
    public int insert(Customer vo ){
      int result=0;
      String sql="INSERT INTO tbl_customer(custom_id, name, email, age, reg_date) " +
                 "VALUES (?, ?, ?, ? , sysdate) ";
      try (Connection connection = getConnection();
           PreparedStatement pstmt = connection.prepareStatement(sql);
          ) {
            pstmt.setString(1, vo.getCustomId());
            pstmt.setString(2, vo.getName());
            pstmt.setString(3, vo.getEmail());
            pstmt.setInt(4, vo.getAge());

            result = pstmt.executeUpdate();
      } catch (Exception e) {
            System.out.println("예외 : " + e.getMessage());
      }
      return result;
    }

    public int update(Customer vo) {
      int result =0;
      // 이메일, 우편번호 업데이트
      String sql="UPDATE tbl_customer SET email = ?, postcode = ? WHERE custom_id = ?";
      try (Connection connection = getConnection();
           PreparedStatement pstmt = connection.prepareStatement(sql);
          ) {
            pstmt.setString(1, vo.getEmail());
            pstmt.setString(2, vo.getPostcode());
            pstmt.setString(3,vo.getCustomId());
            result=pstmt.executeUpdate();
        
      } catch (Exception e) {
        System.out.println("예외 : " + e.getMessage());
      }
      return result;
    }

    // 기본키 값으로 삭제
    public int delete(String customId) {
      int result =0;
      String sql="DELETE FROM tbl_customer WHERE custom_id = ?";
      try (Connection connection = getConnection();
           PreparedStatement pstmt = connection.prepareStatement(sql);
          ) {
            pstmt.setString(1, customId);
            result = pstmt.executeUpdate();
      } catch (Exception e) {
        System.out.println("예외 : " + e.getMessage());
      }
      return result;
    }

    // 기본키 custom_id 컬럼 값으로 조회
    public Customer selectByPk(String custom_id){
      String sql="SELECT * FROM tbl_customer WHERE custom_id = ?";
      Customer customer = null;
      try (Connection connection = getConnection();
           PreparedStatement pstmt = connection.prepareStatement(sql);
          ) {
            pstmt.setString(1,  custom_id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
              customer = new Customer(rs.getString(1),
                      rs.getString(2),
                      rs.getString(3),
                      rs.getInt(4),     //age 컬럼
                      rs.getDate(5),    //reg_date 컬럼
                      rs.getString(6)); //postcode 컬럼
            }

      } catch (Exception e) {
        System.out.println("예외 : " + e.getMessage());
      }
      return customer;
    } 
}
