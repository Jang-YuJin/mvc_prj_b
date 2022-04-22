package service;

import java.util.List;

import model.MylistDAO;
import model.MylistDTO;

public class MylistService {
	MylistDAO dao;
	
	public MylistService() {
		dao = new MylistDAO();
	}
	
	public int erasureMylist(String code) {
		return dao.deleteMylist(code);
	}

	public int erasureMylistToId(String id) {
		return dao.deleteMylistToId(id);
	}

	public List<MylistDTO> getOrderMylistToId(String id) {
		return dao.selectOrderMylistToId(id);
	}

	public List<MylistDTO> getCartMylistToId(String id) {
		return dao.selectCartMylistToId(id);
	}

	public int modifyOrder(String mylist_id) {
		return dao.updateOrder(mylist_id);
	}

	public int setOrder(String id, String code) {
		return dao.insertOrder(id, code);
	}

	public int setCart(String id, String code) {
		return dao.insertCart(id, code);
	}
}
