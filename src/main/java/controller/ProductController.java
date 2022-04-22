package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.InquiryDTO;
import model.ProductDTO;
import model.ReviewDTO;
import service.InquiryService;
import service.ProductService;
import service.ReviewService;

@WebServlet("/shop/*")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService productService;
	ReviewService reviewService;
	InquiryService inquiryService;

	public void init(ServletConfig config) throws ServletException {
		productService = new ProductService();
		reviewService = new ReviewService();
		inquiryService = new InquiryService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = "";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String action = request.getPathInfo();
		System.out.println("action : " + action);
		
//		String prePage = "";
//		if(!"/logout.do".equals(action) || !"/login.do".equals(action)) {
//			prePage = action;
//		}
//		if(null == prePage) {
//			prePage = request.getParameter("prePage");
//		}
////		if("".equals(prePage)) {
////			prePage = request.getParameter(prePage);
////		}
//		System.out.println("prePage : " + prePage);
		
		try {
			if (action.equals("/main.do")) {
				List<ProductDTO> list = productService.getAllProductList();
				System.out.println("product list.size() : " + list.size());
				List<String> upper = productService.getUpperCode(list);
				System.out.println("product upper.size() : " + upper.size());
				System.out.println("product upper.get(0) : " + upper.get(0));
				List<String> sub = productService.getSubCode(list);
				System.out.println("product sub.size() : " + sub.size());
				List<String> num = productService.getNumCode(list);
				request.setAttribute("product", list);
				request.setAttribute("upper", upper);
				request.setAttribute("sub", sub);
				request.setAttribute("num", num);
				
				nextPage = "/index.jsp";
				
			}else if(action.equals("/list.do")) {
				Integer nowPage = 1;
				int section = 5;// 5개씩 끊어서 페이지 숫자 나오도록 12345/678910/... 이런식
				int perPage = 9;
				if(request.getParameter("nowPage") != null) {
					nowPage = Integer.parseInt(request.getParameter("nowPage"));
				}
				System.out.println("nowPage : " + nowPage);
				String code = request.getParameter("code");
				List<ProductDTO> list = productService.getCodeProductList(code, nowPage, perPage);
				System.out.println("list.size() : " + list.size());
				List<String> upper = productService.getUpperCode(list);
				List<String> sub = productService.getSubCode(list);
				List<String> num = productService.getNumCode(list);
				request.setAttribute("code", code);
				request.setAttribute("product", list);
				request.setAttribute("upper", upper);
				request.setAttribute("sub", sub);
				request.setAttribute("num", num);
				
//				페이징 구현하기
				Integer total = productService.getCodeProductList(code).size();
				System.out.println("total : " + total);
//				if( total == null) {
//					total = 1;
//				}

				int lastPage = total % perPage == 0 ? total / perPage : total / perPage + 1;//한 페이지당 나오는 상품수를 12개로 정함
				int position = nowPage % section == 0 ? nowPage / section : nowPage / section + 1;//이전과 다음 사이에 나오는 페이지 수를 section개로 함
				int begin = (position - 1) * section + 1;
				int end = position * section;
				if(end > lastPage) {
					end = lastPage;
				}
				System.out.println("position : " + position + ", lastPage : " + lastPage + ", begin : " + begin + ", end : " + end);
				request.setAttribute("nowPage", nowPage);
				request.setAttribute("lastPage", lastPage);
				request.setAttribute("begin", begin);
				request.setAttribute("end", end);
				
				nextPage = "/shoplist.jsp";
				
			}else if(action.equals("/detail.do")) {
				Integer nowPage = 1;
				if(request.getParameter("nowPage") != null) {
					nowPage = Integer.parseInt(request.getParameter("nowPage"));
					
				}
				String code = request.getParameter("code");
				List<ProductDTO> list = productService.getCodeProductList(code);//한개만 뽑아올거임
				List<String> upper = productService.getUpperCode(list);
				List<String> sub = productService.getSubCode(list);
				List<String> num = productService.getNumCode(list);
				request.setAttribute("product", list);
				request.setAttribute("upper", upper);
				request.setAttribute("sub", sub);
				request.setAttribute("num", num);
				request.setAttribute("code", code);

				int section = 3;// 3개씩 끊어서 페이지 숫자 나오도록 123/456/789/... 이런식
				int perPage = 5;// 한 페이지 당 5개 리뷰 나옴
				
				List<ReviewDTO> reviewList = reviewService.getCodeReviewList(code, nowPage, perPage);
				
//				페이징 구현하기
				Integer total = reviewService.getCodeReviewList(code).size();
				System.out.println("reviewList.size() : " + reviewList.size());
//				if( total == null) {
//					total = 1;
//				}
				
				int lastPage = total % perPage == 0 ? total / perPage : total / perPage + 1;//한 페이지당 나오는 상품수를 12개로 정함
				int position = nowPage % section == 0 ? nowPage / section : nowPage / section + 1;//이전과 다음 사이에 나오는 페이지 수를 5개로 함
				int begin = (position - 1) * section + 1;
				int end = position * section;
				if(end > lastPage) {
					end = lastPage;
				}
				System.out.println("position : " + position + ", lastPage : " + lastPage + ", begin : " + begin + ", end : " + end);
				request.setAttribute("review", reviewList);
				request.setAttribute("nowPage", nowPage);
				request.setAttribute("lastPage", lastPage);
				request.setAttribute("begin", begin);
				request.setAttribute("end", end);
				
				///////////////////////////문의
				int in_nowPage = 1;
				if(request.getParameter("in_nowPage") != null) {
					in_nowPage = Integer.parseInt(request.getParameter("in_nowPage"));
				}
				Integer in_total = inquiryService.getInquiry(code).size();
				System.out.println("in_total : " + in_total);
				int in_section = 3;// 3개씩 끊어서 페이지 숫자 나오도록 123/456/789/... 이런식
				int in_perPage = 5;// 한 페이지 당 5개 리뷰 나옴
				
				int in_lastPage = in_total % in_perPage == 0 ? in_total / in_perPage : in_total / in_perPage + 1;//한 페이지당 나오는 상품수를 12개로 정함
				int in_position = in_nowPage % in_section == 0 ? in_nowPage / in_section : in_nowPage / in_section + 1;//이전과 다음 사이에 나오는 페이지 수를 5개로 함
				int in_begin = (in_position - 1) * in_section + 1;
				int in_end = in_position * in_section;
				if(in_end > in_lastPage) {
					in_end = in_lastPage;
				}
				List<InquiryDTO> inquiryList = inquiryService.getInquiry(code, in_nowPage, in_perPage);
				request.setAttribute("inquiry", inquiryList);
				request.setAttribute("in_nowPage", in_nowPage);
				request.setAttribute("in_lastPage", in_lastPage);
				request.setAttribute("in_begin", in_begin);
				request.setAttribute("in_end", in_end);
				
				
				nextPage = "/shopdetail.jsp";
			}
			else {
				nextPage = "/err.jsp";
			}
//			request.setAttribute("prePage", prePage);
			System.out.println("nextPage: " + nextPage);
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
