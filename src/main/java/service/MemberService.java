package service;

import java.util.List;

import model.MemberDAO;
import model.MemberDTO;

public class MemberService {
	private MemberDAO dao;
	
	public MemberService() {
		dao = new MemberDAO();
	}
	
	public int setMemberInfo(MemberDTO memberDTO) {
		int result = dao.insertMemberInfo(memberDTO);
		
		return result;
	}
	
	public boolean isIdUnique(String id) {//중복ID 체크
		boolean isDuplId = false; //id 중복 체크 변수, 중복이면 true
		
		if(dao.selectMemberID(id) != null) {
			isDuplId = true;
		}	
		
		return isDuplId;
	}

	public MemberDTO getAllInfo(String id, String password) {
		return dao.selectAllInfo(id, password);
	}
	
	public int setSellerInfo(MemberDTO sellerDTO) {
		return dao.insertSellerInfo(sellerDTO);
	}
	
	public MemberDTO getSellerInfo(String id, String password) {
		return dao.selectSellerInfo(id, password);
	}
	
	public MemberDTO getMemberToID(String id){
		return dao.selectMemberToID(id);
	}

	public int modifyMember(MemberDTO dto){
		return dao.updateMember(dto);
	}
	
	public int erasureMember(String id){
		ReviewService r = new ReviewService();
		MylistService l = new MylistService();
		InquiryService i = new InquiryService();
		int r_result = r.erasureReviewToId(id);
		int l_result = l.erasureMylistToId(id);
		int i_result = i.erasureInqiru(id);
		System.out.println("회원 삭제하는데 리뷰 삭제먼저 잘 됨? : " + r_result);
		System.out.println("회원 삭제하는데 마이리스트 삭제먼저 잘 됨? : " + l_result);
		System.out.println("회원 삭제하는데 문의 삭제먼저 잘 됨? : " + i_result);
		return dao.deleteMember(id);
	}
}
