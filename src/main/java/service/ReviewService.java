package service;

import java.util.List;

import model.ReviewDAO;
import model.ReviewDTO;

public class ReviewService {
	ReviewDAO dao;
	
	public ReviewService() {
		dao = new ReviewDAO();
	}
	
	public List<ReviewDTO> getCodeReviewList(String code) {
		List<ReviewDTO> list = dao.selectReviewList(code);
		return list;
	}
	
	public List<ReviewDTO> getCodeReviewList(String code, int nowPage, int perPage) {
		List<ReviewDTO> list = dao.selectReviewList(code, nowPage, perPage);
		return list;
	}
	
	public int erasureReview(String code) {
		return dao.deleteReview(code);
	}
	
	public int erasureReviewToId(String id) {
		return dao.deleteReviewToId(id);
	}
}
