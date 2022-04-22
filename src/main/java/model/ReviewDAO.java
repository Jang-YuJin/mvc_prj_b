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

public class ReviewDAO {
	private PreparedStatement pstmt;
	private Connection con;
	private DataSource dataFactory;
	
	public ReviewDAO() {
		Context ctx;
		try {
			ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/banana");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public List<ReviewDTO> selectReviewList(String code){ //상품코드에 해당하는 tb_review의 모든 레코드를 list에 저장
		List<ReviewDTO> list = new ArrayList<>();
		
		try {
			con = dataFactory.getConnection();
			
			String query = "select * from tb_review where code like '" + code + "'";
			pstmt = con.prepareStatement(query);
			ResultSet result = pstmt.executeQuery();
			while(result.next()) {
				ReviewDTO dto = new ReviewDTO();
				dto.setReview_id(result.getString("review_id"));
				dto.setId(result.getString("id"));
				dto.setCode(result.getString("code"));
				dto.setTitle(result.getString("title"));
				dto.setContent(result.getString("content"));
				dto.setWrite_date(result.getDate("write_date"));
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
	
	public List<ReviewDTO> selectReviewList(String code, int nowPage, int perPage){ //review테이블 페이징에 필요한 list
		List<ReviewDTO> list = new ArrayList<>();
		int offset = 1 + (nowPage - 1) * perPage;
		int end = nowPage * perPage;
		
		try {
			con = dataFactory.getConnection();
			
			String query = "select * from (select rownum num, r.* from tb_review r where code like '" + code + "%') where num between ? and ?";
//			String query = "select * from ("
//					+ "    select dense_rank() over(order by v.num desc) as rnk, v.*"
//					+ "    from v_review1 v where code like '" + code + "')"
//					+ "    where rnk between ? and ?";
//			pstmt = con.prepareStatement(query);
			pstmt = new LoggableStatement(con, query);//TODO : SQL 찍어보기
			pstmt.setInt(1, offset);
			pstmt.setInt(2, end);
			System.out.println(((LoggableStatement)pstmt).getQueryString());//TODO : SQL 찍어보기
			ResultSet result = pstmt.executeQuery();
			while(result.next()) {
				ReviewDTO dto = new ReviewDTO();
				dto.setReview_id(result.getString("review_id"));
				dto.setId(result.getString("id"));
				dto.setCode(result.getString("code"));
				dto.setTitle(result.getString("title"));
				dto.setContent(result.getString("content"));
				dto.setWrite_date(result.getDate("write_date"));
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
	
	public int deleteReview(String code){
		int result = -10;
		try {
			con = dataFactory.getConnection();
			String query = "delete from tb_review where code like '" + code + "'";
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
	
	public int deleteReviewToId(String id){ //TODO : product테이블 code에 해당하는 상품 지우는건데 일단 select하는 걸로, 나중에 return타입 void로
		int result = -10;
		try {
			con = dataFactory.getConnection();
			String query = "delete from tb_review where id like '" + id + "'";
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
}
