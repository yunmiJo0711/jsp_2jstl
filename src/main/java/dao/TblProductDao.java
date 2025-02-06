package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vo.Product;

public class TblProductDao {
    // 싱글톤 패턴 정의
    private static TblProductDao dao = new TblProductDao();
    private TblProductDao(){ }
    public static TblProductDao getInstance(){
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

    // SQL 메소드 정의 각 sql connection 만들어서 사용하고 sql 실행 후 close()
    // 상품 등록하기 
    public int insert(Product vo){
        int result =0;
        String sql = "INSERT INTO TBL_PRODUCT(pcode, category, pname, price)" + 
                        "VALUES(?, ?, ?, ?)";
        try (Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ) {
                pstmt.setString(1, vo.getPcode());
                pstmt.setString(2, vo.getCategory());
                pstmt.setString(3, vo.getPname());
                pstmt.setInt(4, vo.getPrice());

                result = pstmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("예외 : " + e.getMessage());
        }
        return result;
    }

    // 상품명, 가격 업데이트
    public int update(Product vo){
        int result =0;
        String sql ="UPDATE TBL_PRODOCT SET pname = ?, price = ? WHERE pcode = ?";
        try (Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ) {
                pstmt.setString(1, vo.getPname());
                pstmt.setInt(2, vo.getPrice());
                pstmt.setString(3, vo.getPcode());

                result = pstmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("예외 : " + e.getMessage());
        }
        return result;
    }

    // 기본키 값으로 삭제
    public int delete(String pcode){
        int result=0;
        String sql = "DELETE FROM tbl_product WHERE pcode = ?";
        try (Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ) {
                pstmt.setString(1, pcode);
                result = pstmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("예외 : " + e.getMessage());
        }
        return result;
    }

    // 기본키 값으로 조회 - 0 또는 1개 행이 결과값
    public Product selectByPk(String pcode){
        String sql = "SELECT * FROM TBL_PRODUCT WHERE pcode = ?";
        Product product = null;
        try (Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ) {
                pstmt.setString(1, pcode);
                ResultSet rs = pstmt.executeQuery();
                if(rs.next()){
                    product = new Product(rs.getString(1),
                                          rs.getString(2),
                                          rs.getString(3),
                                          rs.getInt(4));
                }
        } catch (Exception e) {
            System.out.println("예외 : " + e.getMessage());
        }
        return product;    // 조회 결과(행)가 없으면 product는  null 
    }
    
    // 전체 행 상품 조회 - n개 행이 결과값. 메소드 이름은 selectAllProduct
    public List<Product> selectAllProduct(){
        List<Product> list = null;
        String sql = "SELECT * FROM tbl_product ORDER BY category, pname";
        try (
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
        ){
            list = new ArrayList<>();
            ResultSet rs = pstmt.executeQuery();
            Product product = null;
            while (rs.next()) {
                product = new Product(rs.getString(1),  // pcode
                                      rs.getString(2),  // category
                                      rs.getString(3),  // pname
                                      rs.getInt(4));	// price
                list.add(product);
            }


        }catch (SQLException e) {
            System.out.println("예외 : " + e.getMessage());
        }

        return list;   // 조회 결과(행)가 없으면 list가 null 은 아니고 size가 0
    }
    
    
    
    

    // 카테고리 값으로 상품 조회 - n개 행이 결과값
    public List<Product> selectByCategory(String category){
        List<Product> list = null;
        String sql = "SELECT * FROM tbl_product WHERE category = ?";
        try (
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
        ){
            list = new ArrayList<>();
            pstmt.setString(1,category);
            ResultSet rs = pstmt.executeQuery();
            Product product = null;
            while (rs.next()) {
                product = new Product(rs.getString(1),  // pcode
                                      rs.getString(2),  // category
                                      rs.getString(3),  // pname
                                      rs.getInt(4));	// price
                list.add(product);
            }


        }catch (SQLException e) {
            System.out.println("예외 : " + e.getMessage());
        }

        return list;   // 조회 결과(행)가 없으면 list가 null 은 아니고 size가 0
    }




    // 상품 키워드로 조회 - n개 행이 결과값
    public List<Product> searchByKeyword(String keyword){
        String sql = "SELECT * FROM TBL_PRODUCT WHERE pname LIKE '%' || ? || '%' ";
        List<Product> list = new ArrayList<>();
        try (Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ) {
                pstmt.setString(1, keyword);

                ResultSet rs = pstmt.executeQuery();
                Product product = null;
            while (rs.next()) {
                product = new Product(rs.getString(1),
                                      rs.getString(2),
                                      rs.getString(3),
                                      rs.getInt(4));
                list.add(product);
            }
           
        } catch (Exception e) {
            System.out.println("예외 : " + e.getMessage());
        }
        return list;     // 조회 결과(행)가 없으면 list가 null 은 아니고 size가 0
    }

    // 상품 가격 정보를 Map 에 저장하기 - map 연습 예제
    //  ㄴ Map 에 저장한 데이터는 검색 성능이 좋습니다.
    // 상품 가격표 - n개 행이 조회
    public Map<String, Integer> getPriceTable() {
        Map<String, Integer> map = new HashMap<>();
        String sql = "select pcode,price from tbl_product";
        try(
            Connection conn = getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql))
		{
		    ResultSet rs = psmt.executeQuery();
		    while(rs.next()) {  
			        map.put(rs.getString(1),   //key는 조회된 첫번째 컬럼 pcode
                       rs.getInt(2));    //value는 조회된 두번째 컬럼 price
		    }
        }catch(SQLException e){
            System.out.println("getPriceTable 예외 발생 : " + e.getMessage());
        }

        return map;
    }

    // Map에 저장된 데이터는 selectByPk(String pcode) 메소드를 대신할 수 있습니다.
    // Map 연습 메소드 - CartMenu 에서 활용은 보류
    public Map<String,Product> selectAll(){
      Map<String,Product> map = new HashMap<>();
      String sql = "SELECT * FROM tbl_product";    // 모든행, 모든컬럼 조회
      try (
           Connection connection = getConnection();
           PreparedStatement pstmt = connection.prepareStatement(sql)
      ) {
           ResultSet rs = pstmt.executeQuery();
           while(rs.next()){
              // map 에 조회된 행들을 추가
              Product vo = new Product(rs.getString(1), 
                      rs.getString(2), 
                      rs.getString(3), 
                      rs.getInt(4));
              map.put(rs.getString(1),vo);  //value 타입이 Product 
           }
      } catch (Exception e) {
        System.out.println("예외 : " + e.getMessage());
      }


      return map;
    }

}