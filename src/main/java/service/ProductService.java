package service;

import java.util.ArrayList;
import java.util.List;

import model.ProductDAO;
import model.ProductDTO;

public class ProductService {
	ProductDAO dao;
	
	public ProductService() {
		dao = new ProductDAO();
	}
	
	public List<ProductDTO> getAllProductList(){
		return dao.selectAllProductList();
	}
	
	public List<ProductDTO> getCodeProductList(String code){
		return dao.selectProductList(code);
	}

	public List<ProductDTO> getCodeProductList(String code, int nowPage, int perPage){
		return dao.selectProductList(code, nowPage, perPage);
	}
	
	public List<ProductDTO> getCodeProductList(String id, String code, int nowPage, int perPage){
		return dao.selectProductList(id, code, nowPage, perPage);
	}

	public List<ProductDTO> getSellerProductList(String id, int nowPage, int perPage){
		return dao.selectSellerProductList(id, nowPage, perPage);
	}

	public List<ProductDTO> getSellerProductList(String id){
		return dao.selectSellerProductList(id);
	}

	public List<String> getUpperCode(List<ProductDTO> dto){//product테이블 code에 상위카테고리(outer, top...)만 list로 뽑음
		List<String> upperCode = new ArrayList<>();
		String[] code = new String[3];
		
		for(int i = 0; i < dto.size(); i++) {
			code = dto.get(i).getCode().split("_");
			upperCode.add(code[0]);
		}

		return upperCode;
	}
	
	public List<String> getSubCode(List<ProductDTO> dto){//product테이블 code에 하위카테고리(jk, ct...)만 list로 뽑음
		List<String> subCode = new ArrayList<>();
		String[] code = new String[3];
		
		for(int i = 0; i < dto.size(); i++) {
			code = dto.get(i).getCode().split("_");
			subCode.add(code[1]);
		}

		return subCode;
	}
	
	public List<String> getNumCode(List<ProductDTO> dto){//product테이블 code에 숫자만 list로 뽑음
		List<String> numCode = new ArrayList<>();
		String[] code = new String[3];
		
		for(int i = 0; i < dto.size(); i++) {
			code = dto.get(i).getCode().split("_");
			numCode.add(code[2]);
		}

		return numCode;
	}
	
	public int erasureProduct(String code) {//TODO : 판매자 상품 삭제 몰빵
		ReviewService r = new ReviewService();
		MylistService m = new MylistService();
		r.erasureReview(code);
		m.erasureMylist(code);
		return dao.deleteProduct(code);
	}
	
	public int modifyProduct(ProductDTO dto) {
		return dao.updateProduct(dto);
	}
	
	public int registerProduct(ProductDTO dto) {
		return dao.insertProduct(dto);
	}
}
