package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MylistDAO {
	private PreparedStatement pstmt;
	private Connection con;
	private DataSource dataFactory;
	
	public MylistDAO() {
		Context ctx;
		try {
			ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/banana");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public int deleteMylist(String code){
		int result = -10;
		try {
			con = dataFactory.getConnection();
			String query = "delete from tb_mylist where code like '" + code + "'";
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
	
	public int deleteMylistToId(String id){
		int result = -10;
		try {
			con = dataFactory.getConnection();
			String query = "delete from tb_mylist where id like '" + id + "'";
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
	
	public List<MylistDTO> selectOrderMylistToId(String id){ //mylist에 로그인한 회원이 주문한 것들만 뽑기
		List<MylistDTO> list = new ArrayList<>();
		
		try {
			con = dataFactory.getConnection();
			
			String query = "select l.mylist_id, l.id, l.code, l.order_date, l.sort, s.name, p.name as product_name, p.price from tb_mylist l, tb_product p, tb_mylistsort s"
					+ " where l.code = p.code"
					+ " and l.sort = s.sort"
					+ " and l.sort in (20, 30, 40, 50)"
					+ " and l.id like ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			ResultSet result = pstmt.executeQuery();
			while(result.next()) {
				MylistDTO dto = new MylistDTO();
				dto.setMylist_id(result.getString("mylist_id"));
				dto.setId(result.getString("id"));
				dto.setCode(result.getString("code"));
				dto.setOrder_date(result.getDate("order_date"));
				dto.setSort(result.getInt("sort"));
				dto.setSort_name(result.getString("name"));
				dto.setProduct_name(result.getString("product_name"));
				dto.setPrice(result.getInt("price"));
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
	
	public List<MylistDTO> selectCartMylistToId(String id){ //mylist에 로그인한 회원이 주문한 것들만 뽑기
		List<MylistDTO> list = new ArrayList<>();
		
		try {
			con = dataFactory.getConnection();
			
			String query = "select l.mylist_id, l.id, l.code, l.order_date, l.sort, s.name, p.name as product_name, p.price from tb_mylist l, tb_product p, tb_mylistsort s"
					+ " where l.code = p.code"
					+ " and l.sort = s.sort"
					+ " and l.sort = 10"
					+ " and l.id like ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			ResultSet result = pstmt.executeQuery();
			while(result.next()) {
				MylistDTO dto = new MylistDTO();
				dto.setMylist_id(result.getString("mylist_id"));
				dto.setId(result.getString("id"));
				dto.setCode(result.getString("code"));
				dto.setOrder_date(result.getDate("order_date"));
				dto.setSort(result.getInt("sort"));
				dto.setSort_name(result.getString("name"));
				dto.setProduct_name(result.getString("product_name"));
				dto.setPrice(result.getInt("price"));
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
	
	public int updateOrder(String mylist_id) { //member테이블에 seller 저장
		int result = -10;
		try {
			con = dataFactory.getConnection();
			String query = "update tb_mylist set sort = 20 where mylist_id like ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mylist_id);
			result = pstmt.executeUpdate();
			if(pstmt != null) {
				pstmt.close();
			}
			if(con != null) {
				con.close();
			}
			System.out.println(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int insertOrder(String id, String code) { //member테이블에 seller 저장
		int result = -10;
		try {
			con = dataFactory.getConnection();
			String query = "insert into tb_mylist (mylist_id, id, code, order_date, sort)"
					+ " values(tb_mylist_mylist_id_seq.nextval, ?, ?, sysdate, 20)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, code);
			result = pstmt.executeUpdate();
			if(pstmt != null) {
				pstmt.close();
			}
			if(con != null) {
				con.close();
			}
			System.out.println(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int insertCart(String id, String code) {
		int result = -10;
		try {
			con = dataFactory.getConnection();
			String query = "insert into tb_mylist (mylist_id, id, code, sort)"
					+ " values(tb_mylist_mylist_id_seq.nextval, ?, ?, 10)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, code);
			result = pstmt.executeUpdate();
			if(pstmt != null) {
				pstmt.close();
			}
			if(con != null) {
				con.close();
			}
			System.out.println(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
