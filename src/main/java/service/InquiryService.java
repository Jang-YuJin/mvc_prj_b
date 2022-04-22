package service;

import java.util.List;

import model.InquiryDAO;
import model.InquiryDTO;
import model.ProductDTO;

public class InquiryService {
	InquiryDAO dao;
	
	public InquiryService() {
		dao = new InquiryDAO();
	}
	
	public List<InquiryDTO> getInquiry(String code){
		return dao.selectInquiry(code);
	}
	
	public List<InquiryDTO> getInquiry(String code, int nowPage, int perPage){
		return dao.selectInquiry(code, nowPage, perPage);
	}

	public List<InquiryDTO> getSellerInquiry(String code, int nowPage, int perPage){
		return dao.selectSellerInquiry(code, nowPage, perPage);
	}

	public List<InquiryDTO> getSellerInquiry(String sellerId) {
		return dao.selectSellerInquiry(sellerId);
	}

	public int setMemberInquiry(InquiryDTO dto) {
		return dao.insertMemberInquiry(dto);
	}
	
	public List<InquiryDTO> getMemberInquiry(String memberId, int nowPage, int perPage){
		return dao.selectMemberInquiry(memberId, nowPage, perPage);
	}
	
	public List<InquiryDTO> getMemberInquiry(String memberId){
		return dao.selectMemberInquiry(memberId);
	}

	public InquiryDTO getToInquiry_id(int inquiry_id){
		return dao.selectToInquiry_id(inquiry_id);
	}

	public int setSellerInquiry(InquiryDTO dto) {
		return dao.insertSellerAnswerInquiry(dto);
	}
	
	public int erasureInqiru(String id){
		return dao.deleteInquiryToId(id);
	}
}
