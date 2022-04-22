package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProductDAO {
	private PreparedStatement pstmt;
	private Connection con;
	private DataSource dataFactory;
	
	public ProductDAO() {
		Context ctx;
		try {
			ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/banana");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public List<ProductDTO> selectAllProductList(){ //product테이블의 모든 레코드를 list에 저장
		List<ProductDTO> list = new ArrayList<>();
		
		try {
			con = dataFactory.getConnection();
			
			String query = "select * from tb_product";
			pstmt = con.prepareStatement(query);
			ResultSet result = pstmt.executeQuery();
			while(result.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setCode(result.getString("code"));
				dto.setId(result.getString("id"));
				dto.setName(result.getString("name"));
				dto.setPrice(result.getInt("price"));
				dto.setRepresentative(result.getString("representative"));
				dto.setDetails(result.getString("details"));
				list.add(dto);
			}
			if(result != null) {
				result.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<ProductDTO> selectProductList(String code){ //product테이블의 특정 코드에 해당하는 레코드를 list에 저장
		List<ProductDTO> list = new ArrayList<>();
		System.out.println(code);
		try {
			con = dataFactory.getConnection();
			
			String query = "select * from tb_product where code like '%" + code + "%'";
			pstmt = con.prepareStatement(query);
			ResultSet result = pstmt.executeQuery();
			while(result.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setCode(result.getString("code"));
				dto.setId(result.getString("id"));
				dto.setName(result.getString("name"));
				dto.setPrice(result.getInt("price"));
				dto.setRepresentative(result.getString("representative"));
				dto.setDetails(result.getString("details"));
				list.add(dto);
			}
			if(result != null) {
				result.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<ProductDTO> selectProductList(String code, int nowPage, int perPage){ //product테이블 페이징에 필요한 list
		List<ProductDTO> list = new ArrayList<>();
		int offset = 1 + (nowPage - 1) * perPage;
		int end = nowPage * perPage;
		
		try {
			con = dataFactory.getConnection();
			String query = "select * from (select rownum num, p.* from tb_product p where code like '%" + code + "%') where num between ? and ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, offset);
			pstmt.setInt(2, end);
			ResultSet result = pstmt.executeQuery();
			while(result.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setCode(result.getString("code"));
				dto.setId(result.getString("id"));
				dto.setName(result.getString("name"));
				dto.setPrice(result.getInt("price"));
				dto.setRepresentative(result.getString("representative"));
				dto.setDetails(result.getString("details"));
				list.add(dto);
			}
			if(result != null) {
				result.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<ProductDTO> selectProductList(String id, String code, int nowPage, int perPage){ //product테이블 페이징에 필요한 list
		List<ProductDTO> list = new ArrayList<>();
		int offset = 1 + (nowPage - 1) * perPage;
		int end = nowPage * perPage;
		
		try {
			con = dataFactory.getConnection();
//			String query = "select * from (select rownum num, p.* from tb_product p where id like '" + id + "') where num between ? and ?";
			String query = "select * from (select rownum num, p.* from tb_product p where id like '" + id + "' and code like '" + code + "%') where num between ? and ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, offset);
			pstmt.setInt(2, end);
			ResultSet result = pstmt.executeQuery();
			while(result.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setCode(result.getString("code"));
				dto.setId(result.getString("id"));
				dto.setName(result.getString("name"));
				dto.setPrice(result.getInt("price"));
				dto.setRepresentative(result.getString("representative"));
				dto.setDetails(result.getString("details"));
				list.add(dto);
			}
			if(result != null) {
				result.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int deleteProduct(String code){ //TODO : product테이블 code에 해당하는 상품 지우는건데 일단 select하는 걸로, 나중에 return타입 void로
		int result = -10;
		try {
			con = dataFactory.getConnection();
			String query = "delete from tb_product where code like '" + code + "'";
			pstmt = con.prepareStatement(query);
			result = pstmt.executeUpdate();
			if(pstmt != null) {
				pstmt.close();
			}
			if(con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int updateProduct(ProductDTO dto){
		int result = -10;
		System.out.println("dao에서 dto.getPrice() : " + dto.getPrice());
		try {
			con = dataFactory.getConnection();
			String query = "update tb_product set name = '" + dto.getName() + "', price = " + dto.getPrice() + ", representative = '" + dto.getRepresentative() + "', details = '" + dto.getDetails() + "' where code like '" + dto.getCode() + "%'";
//			pstmt = con.prepareStatement(query); TODO: 이걸로 다시 해보기
			pstmt = new LoggableStatement(con, query);
			System.out.println(((LoggableStatement)pstmt).getQueryString());
			result = pstmt.executeUpdate();
			if(pstmt != null) {
				pstmt.close();
			}
			if(con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int insertProduct(ProductDTO dto/* String uCode, String sCode, int k, String img */) {
		String sql = "insert into tb_product"
				+ "(code, id, name, price, representative, details)"
				+ " values ('" + dto.getCode() + "_' || " + dto.getCode() + "_seq.nextval, ?, ?, ?, ?, ?)";
		int result = 0;
			try {
				con = dataFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, dto.getId());
				pst.setString(2, dto.getName());
				pst.setInt(3, dto.getPrice());
				pst.setString(4, dto.getRepresentative());
				pst.setString(5, dto.getDetails());
//				System.out.println(((LoggableStatement)pst).getQueryString());
				result = pst.executeUpdate();
				pst.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return result;
	}
	
	public List<ProductDTO> selectSellerProductList(String id, int nowPage, int perPage){ //product테이블 페이징에 필요한 list
		List<ProductDTO> list = new ArrayList<>();
		int offset = 1 + (nowPage - 1) * perPage;
		int end = nowPage * perPage;
		
		try {
			con = dataFactory.getConnection();
			String query = "select * from (select rownum num, p.* from tb_product p where id like '" + id + "') where num between ? and ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, offset);
			pstmt.setInt(2, end);
			ResultSet result = pstmt.executeQuery();
			while(result.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setCode(result.getString("code"));
				dto.setId(result.getString("id"));
				dto.setName(result.getString("name"));
				dto.setPrice(result.getInt("price"));
				dto.setRepresentative(result.getString("representative"));
				dto.setDetails(result.getString("details"));
				list.add(dto);
			}
			if(result != null) {
				result.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public List<ProductDTO> selectSellerProductList(String id){ //product테이블 페이징에 필요한 list
		List<ProductDTO> list = new ArrayList<>();
		try {
			con = dataFactory.getConnection();
			String query = "select * from tb_product where id like '" + id + "'";
			pstmt = con.prepareStatement(query);
			ResultSet result = pstmt.executeQuery();
			while(result.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setCode(result.getString("code"));
				dto.setId(result.getString("id"));
				dto.setName(result.getString("name"));
				dto.setPrice(result.getInt("price"));
				dto.setRepresentative(result.getString("representative"));
				dto.setDetails(result.getString("details"));
				list.add(dto);
			}
			if(result != null) {
				result.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
