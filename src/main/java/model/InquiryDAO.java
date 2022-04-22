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

public class InquiryDAO {
	private PreparedStatement pstmt;
	private Connection con;
	private DataSource dataFactory;
	
	public InquiryDAO() {
		Context ctx;
		try {
			ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/banana");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public List<InquiryDTO> selectInquiry(String code, int nowPage, int perPage){ //inquiry테이블 페이징에 필요한 list -> 상품디테일페이지 문의 볼때 사용했음
		List<InquiryDTO> list = new ArrayList<>();
		int offset = 1 + (nowPage - 1) * perPage;
		int end = nowPage * perPage;
		
		try {
			con = dataFactory.getConnection();
			String query = "select * from"
					+ "(with tmp (inquiry_id, id, code, title, content, write_date, pi_id) as ("
					+ "    select inquiry_id, id, code, title, content, write_date, pi_id"
					+ "    from tb_inquiry"
					+ "    where pi_id = 0"
					+ "    and code like '" + code + "'"
					+ "    union all"
					+ "    select b.inquiry_id, b.id, b.code, b.title, b.content, b.write_date, b.pi_id"
					+ "    from tmp t, tb_inquiry b"
					+ "    where b.pi_id = t.inquiry_id"
					+ ")"
					+ " search depth first by pi_id set p_sort"
					+ " select rownum num, tmp.* from tmp"
					+ " order by p_sort)"
					+ " where num between ? and ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, offset);
			pstmt.setInt(2, end);
			ResultSet result = pstmt.executeQuery();
			while(result.next()) {
				InquiryDTO dto = new InquiryDTO();
				dto.setInquiry_id(result.getInt("inquiry_id"));
				dto.setId(result.getString("id"));
				dto.setCode(result.getString("code"));
				dto.setTitle(result.getString("title"));
				dto.setContent(result.getString("content"));
				dto.setWrite_date(result.getDate("write_date"));
				dto.setPi_id(result.getString("pi_id"));
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

	public List<InquiryDTO> selectInquiry(String code) {// -> 상품디테일페이지 문의 볼때 사용했음
		List<InquiryDTO> list = new ArrayList<>();
		
		try {
			con = dataFactory.getConnection();
			String query = "select * from"
					+ "(with tmp (inquiry_id, id, code, title, content, write_date, pi_id) as ("
					+ "    select inquiry_id, id, code, title, content, write_date, pi_id"
					+ "    from tb_inquiry"
					+ "    where pi_id = 0"
					+ "    and code like '" + code + "'"
					+ "    union all"
					+ "    select b.inquiry_id, b.id, b.code, b.title, b.content, b.write_date, b.pi_id"
					+ "    from tmp t, tb_inquiry b"
					+ "    where b.pi_id = t.inquiry_id"
					+ ")"
					+ " search depth first by pi_id set p_sort"
					+ " select rownum num, tmp.* from tmp"
					+ " order by p_sort)";
			pstmt = con.prepareStatement(query);
			ResultSet result = pstmt.executeQuery();
			while(result.next()) {
				InquiryDTO dto = new InquiryDTO();
				dto.setInquiry_id(result.getInt("inquiry_id"));
				dto.setId(result.getString("id"));
				dto.setCode(result.getString("code"));
				dto.setTitle(result.getString("title"));
				dto.setContent(result.getString("content"));
				dto.setWrite_date(result.getDate("write_date"));
				dto.setPi_id(result.getString("pi_id"));
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
//		System.out.println("왜 안나오냐 문의 0인덱스 문의아이디 : " + list.get(0).getInquiry_id());
		System.out.println("문의 상품코드만 해당하는 리스트 개수 : " + list.size());
		return list;
	}

	public List<InquiryDTO> selectSellerInquiry(String sellerId, int nowPage, int perPage) {
		List<InquiryDTO> list = new ArrayList<>();
		int offset = 1 + (nowPage - 1) * perPage;
		int end = nowPage * perPage;
		try {
			con = dataFactory.getConnection();
			String query = "with tmp (inquiry_id, id, code, title, content, write_date, pi_id, name) as ("
					+ "    select i.inquiry_id, i.id, i.code, i.title, i.content, i.write_date, i.pi_id, p.name"
					+ "    from tb_product p, tb_inquiry i"
					+ "    where p.code = i.code"
					+ "    and p.id like '" + sellerId + "'"
					+ "    and i.pi_id = 0"
					+ "    union all"
					+ "    select b.inquiry_id, b.id, b.code, b.title, b.content, b.write_date, b.pi_id, p.name"
					+ "    from tmp t, tb_inquiry b, tb_product p"
					+ "    where b.pi_id = t.inquiry_id"
					+ "    and p.code = b.code"
					+ ")"
					+ " search depth first by pi_id set p_sort"
					+ " select tmp.* from tmp"
					+ " where p_sort between ? and ?"
					+ " order by p_sort";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, offset);
			pstmt.setInt(2, end);
			ResultSet result = pstmt.executeQuery();
			while(result.next()) {
				InquiryDTO dto = new InquiryDTO();
				dto.setInquiry_id(result.getInt("inquiry_id"));
				dto.setId(result.getString("id"));
				dto.setCode(result.getString("code"));
				dto.setTitle(result.getString("title"));
				dto.setContent(result.getString("content"));
				dto.setWrite_date(result.getDate("write_date"));
				dto.setPi_id(result.getString("pi_id"));
				dto.setName(result.getString("name"));
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
		System.out.println("왜 안나오냐 문의 0인덱스 문의아이디 : " + list.get(0).getInquiry_id());
		System.out.println("문의 상품코드만 해당하는 리스트 개수 : " + list.size());
		return list;
	}

	public List<InquiryDTO> selectSellerInquiry(String sellerId) {
		List<InquiryDTO> list = new ArrayList<>();
		
		try {
			con = dataFactory.getConnection();
			String query = "with tmp (inquiry_id, id, code, title, content, write_date, pi_id, name) as ("
					+ "    select i.inquiry_id, i.id, i.code, i.title, i.content, i.write_date, i.pi_id, p.name"
					+ "    from tb_product p, tb_inquiry i"
					+ "    where p.code = i.code"
					+ "    and p.id like '" + sellerId + "'"
					+ "    and i.pi_id = 0"
					+ "    union all"
					+ "    select b.inquiry_id, b.id, b.code, b.title, b.content, b.write_date, b.pi_id, p.name"
					+ "    from tmp t, tb_inquiry b, tb_product p"
					+ "    where b.pi_id = t.inquiry_id"
					+ "    and p.code = b.code"
					+ ")"
					+ " search depth first by pi_id set p_sort"
					+ " select tmp.* from tmp"
					+ " order by p_sort";
			pstmt = con.prepareStatement(query);
			ResultSet result = pstmt.executeQuery();
			while(result.next()) {
				InquiryDTO dto = new InquiryDTO();
				dto.setInquiry_id(result.getInt("inquiry_id"));
				dto.setId(result.getString("id"));
				dto.setCode(result.getString("code"));
				dto.setTitle(result.getString("title"));
				dto.setContent(result.getString("content"));
				dto.setWrite_date(result.getDate("write_date"));
				dto.setPi_id(result.getString("pi_id"));
				dto.setName(result.getString("name"));
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
		System.out.println("왜 안나오냐 문의 0인덱스 문의아이디 : " + list.get(0).getInquiry_id());
		System.out.println("문의 상품코드만 해당하는 리스트 개수 : " + list.size());
		return list;
	}
	
	public int insertMemberInquiry(InquiryDTO dto/* String uCode, String sCode, int k, String img */) {
		String sql = "insert into tb_Inquiry"
				+ "(inquiry_id, id, code, title, content, write_date, pi_id)"
				+ " values (TB_INQUIRY_INQUIRY_ID_SEQ.nextval, ?, ?, ?, ?, sysdate, ?)";
		int result = 0;
			try {
				con = dataFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, dto.getId());
				pst.setString(2, dto.getCode());
				pst.setString(3, dto.getTitle());
				pst.setString(4, dto.getContent());
				pst.setString(5, dto.getPi_id());
//				System.out.println(((LoggableStatement)pst).getQueryString());
				result = pst.executeUpdate();
				pst.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return result;
	}
	
	public List<InquiryDTO> selectMemberInquiry(String memberId, int nowPage, int perPage) {
		List<InquiryDTO> list = new ArrayList<>();
		int offset = 1 + (nowPage - 1) * perPage;
		int end = nowPage * perPage;
		try {
			con = dataFactory.getConnection();
			String query = "with tmp (inquiry_id, id, code, title, content, write_date, pi_id, name) as ("
					+ "    select i.inquiry_id, i.id, i.code, i.title, i.content, i.write_date, i.pi_id, p.name"
					+ "    from tb_product p, tb_inquiry i"
					+ "    where p.code = i.code"
					+ "    and i.id like '" + memberId + "'"
					+ "    and i.pi_id = 0"
					+ "    union all"
					+ "    select b.inquiry_id, b.id, b.code, b.title, b.content, b.write_date, b.pi_id, p.name"
					+ "    from tmp t, tb_inquiry b, tb_product p"
					+ "    where b.pi_id = t.inquiry_id"
					+ "    and p.code = b.code"
					+ ")"
					+ " search depth first by pi_id set p_sort"
					+ " select tmp.* from tmp"
					+ " where p_sort between ? and ?"
					+ " order by p_sort";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, offset);
			pstmt.setInt(2, end);
			ResultSet result = pstmt.executeQuery();
			while(result.next()) {
				InquiryDTO dto = new InquiryDTO();
				dto.setInquiry_id(result.getInt("inquiry_id"));
				dto.setId(result.getString("id"));
				dto.setCode(result.getString("code"));
				dto.setTitle(result.getString("title"));
				dto.setContent(result.getString("content"));
				dto.setWrite_date(result.getDate("write_date"));
				dto.setPi_id(result.getString("pi_id"));
				dto.setName(result.getString("name"));
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
		System.out.println("왜 안나오냐 문의 0인덱스 문의아이디 : " + list.get(0).getInquiry_id());
		System.out.println("문의 상품코드만 해당하는 리스트 개수 : " + list.size());
		return list;
	}

	public List<InquiryDTO> selectMemberInquiry(String memberId) {
		List<InquiryDTO> list = new ArrayList<>();
		
		try {
			con = dataFactory.getConnection();
			String query = "with tmp (inquiry_id, id, code, title, content, write_date, pi_id, name) as ("
					+ "    select i.inquiry_id, i.id, i.code, i.title, i.content, i.write_date, i.pi_id, p.name"
					+ "    from tb_product p, tb_inquiry i"
					+ "    where p.code = i.code"
					+ "    and i.id like '" + memberId + "'"
					+ "    and i.pi_id = 0"
					+ "    union all"
					+ "    select b.inquiry_id, b.id, b.code, b.title, b.content, b.write_date, b.pi_id, p.name"
					+ "    from tmp t, tb_inquiry b, tb_product p"
					+ "    where b.pi_id = t.inquiry_id"
					+ "    and p.code = b.code"
					+ ")"
					+ " search depth first by pi_id set p_sort"
					+ " select tmp.* from tmp"
					+ " order by p_sort";
			pstmt = con.prepareStatement(query);
			ResultSet result = pstmt.executeQuery();
			while(result.next()) {
				InquiryDTO dto = new InquiryDTO();
				dto.setInquiry_id(result.getInt("inquiry_id"));
				dto.setId(result.getString("id"));
				dto.setCode(result.getString("code"));
				dto.setTitle(result.getString("title"));
				dto.setContent(result.getString("content"));
				dto.setWrite_date(result.getDate("write_date"));
				dto.setPi_id(result.getString("pi_id"));
				dto.setName(result.getString("name"));
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
		System.out.println("왜 안나오냐 문의 0인덱스 문의아이디 : " + list.get(0).getInquiry_id());
		System.out.println("문의 상품코드만 해당하는 리스트 개수 : " + list.size());
		return list;
	}
	
	public InquiryDTO selectToInquiry_id(int inquiry_id) {
		InquiryDTO dto = new InquiryDTO();
		
		try {
			con = dataFactory.getConnection();
			String query = "select * from tb_inquiry where inquiry_id = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, inquiry_id);
			ResultSet result = pstmt.executeQuery();
			while(result.next()) {
				dto.setInquiry_id(result.getInt("inquiry_id"));
				dto.setId(result.getString("id"));
				dto.setCode(result.getString("code"));
				dto.setTitle(result.getString("title"));
				dto.setContent(result.getString("content"));
				dto.setWrite_date(result.getDate("write_date"));
				dto.setPi_id(result.getString("pi_id"));
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
		return dto;
	}
	
	public int insertSellerAnswerInquiry(InquiryDTO dto/* String uCode, String sCode, int k, String img */) {
		String sql = "insert into tb_Inquiry"
				+ "(inquiry_id, id, code, title, content, write_date, pi_id)"
				+ " values (TB_INQUIRY_INQUIRY_ID_SEQ.nextval, ?, ?, ?, ?, sysdate, ?)";
		int result = 0;
			try {
				con = dataFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, dto.getId());
				pst.setString(2, dto.getCode());
				pst.setString(3, dto.getTitle());
				pst.setString(4, dto.getContent());
				pst.setString(5, dto.getPi_id());
//				System.out.println(((LoggableStatement)pst).getQueryString());
				result = pst.executeUpdate();
				pst.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return result;
	}
	
	public int deleteInquiryToId(String id) { //원글, 답글 동시에 삭제
		int result = -10;
		try {
			con = dataFactory.getConnection();
			String query = "delete from tb_inquiry"
					+ " where inquiry_id in ("
					+ " select inquiry_id from"
					+ " (with tmp (inquiry_id, id, pi_id) as ("
					+ "    select inquiry_id, id, pi_id"
					+ "    from tb_inquiry"
					+ "    where pi_id = 0"
					+ "    and id like '" + id + "'"
					+ "    union all"
					+ "    select b.inquiry_id, b.id, b.pi_id"
					+ "    from tmp t, tb_inquiry b"
					+ "    where b.pi_id = t.inquiry_id"
					+ ")"
					+ " search depth first by pi_id set p_sort"
					+ " select * from tmp"
					+ " order by p_sort))";
			pstmt = con.prepareStatement(query);
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
