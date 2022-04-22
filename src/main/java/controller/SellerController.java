package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.InquiryDTO;
import model.MemberDTO;
import model.ProductDTO;
import service.InquiryService;
import service.MemberService;
import service.MylistService;
import service.ProductService;
import service.ReviewService;

@WebServlet("/seller/*")
public class SellerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	MemberService memberService;
	ProductService productService;
	ReviewService reviewService;
	MylistService mylistService;
	MemberService memberService;
	InquiryService inquiryService;

	public void init(ServletConfig config) throws ServletException {
//		memberService = new MemberService();
		productService = new ProductService();
		reviewService = new ReviewService();
		mylistService = new MylistService();
		memberService = new MemberService();
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
		
		HttpSession session = request.getSession();
		
		try {
			if (action.equals("/entry.do")) {
				nextPage = "/entry.jsp";
			}else if(action.equals("/entryConfirm.do")){
				MemberDTO dto = new MemberDTO();
				String name = request.getParameter("name");
				String tel = request.getParameter("tel");
				String address = request.getParameter("address");
				String com_name = request.getParameter("com_name");
				String[] cr_no_arr = request.getParameterValues("cr_no");
				String cr_no = cr_no_arr[0] + "-" + cr_no_arr[1] + "-" + cr_no_arr[2];

				dto.setName(name);
				dto.setTel(tel);
				dto.setAddress(address);
				dto.setCom_name(com_name);
				dto.setCr_no(cr_no);
				
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				if("".equals(name) || name == null ||
						"".equals(tel) || tel == null ||
						"".equals(address) || address == null ||
						"".equals(com_name) || com_name == null ||
						cr_no == null || "".equals(cr_no_arr[0]) || "".equals(cr_no_arr[1]) || "".equals(cr_no_arr[2])) {
					out.println("<script>alert('모든 내용을 입력해주세요.'); location.href='/mpro/seller/entry.do';</script>");
//					out.println("<script>alert('모든 내용을 입력해주세요.');</script>");
					out.close();
//					nextPage = "/seller/entry.do";
				}else {
					System.out.println("입점 요청ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ");
					//TODO : insert 만들어
					int result = memberService.setSellerInfo(dto);
					System.out.println("판매자 입점 신청 : " + result);
					out.println("<script>alert('입점신청이 완료되었습니다. 승인까지 7일의 시간이 소요됩니다.'); location.href='/mpro/shop/main.do';</script>");
//					out.println("<script>alert('입점신청이 완료되었습니다. 승인까지 7일의 시간이 소요됩니다.');</script>");
					out.close();
//					nextPage = "/shop/main.do";
				}
			}else if(action.equals("/seller_main.do")){
				nextPage = "/seller_main.jsp";
			}else if(action.equals("/product_management.do")){
				Integer nowPage = 1;
				int section = 5;// 5개씩 끊어서 페이지 숫자 나오도록 12345/678910/... 이런식
				int perPage = 5;// 한 페이지 당 나오는 게시글 수
				if(request.getParameter("nowPage") != null) {
					nowPage = Integer.parseInt(request.getParameter("nowPage"));
				}
				String id = (String)session.getAttribute("id");
				
				List<ProductDTO> pList = productService.getSellerProductList(id);
				List<ProductDTO> productList = productService.getSellerProductList(id, nowPage, perPage);
				System.out.println("pList.size() : " + pList.size());
				request.setAttribute("product", productList);
				Integer total = pList.size();
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

				nextPage = "/product_management.jsp";
			}else if(action.equals("/product_del.do")){
				String[] codes = request.getParameterValues("del");
				int productResult = 0;
				int reviewResult = 0;
				int mylistResult = 0;
				for(int i = 0; i < codes.length; i++) {//TODO : 리뷰 삭제 넣기
//					reviewResult = reviewService.erasureReview(codes[i]);//TODO : 판매자 상품 삭제 몰빵@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//					mylistResult = mylistService.erasureMylist(codes[i]);//TODO : 리뷰있는 상품이면 삭제가 안됨...리뷰는 됐는데 mylist 데이블 때문에 또 안됨..... mylist delete 만들어...
					productResult = productService.erasureProduct(codes[i]);//TODO : 리뷰있는 상품이면 삭제가 안됨...리뷰는 됐는데 mylist 데이블 때문에 또 안됨..... mylist delete 만들어...
//					System.out.println("상품삭제 reviewResult : " + reviewResult);
//					System.out.println("상품삭제 mylistResult : " + mylistResult);
					System.out.println("상품삭제 productResult : " + productResult);
				}
				nextPage = "/seller/product_management.do";
			}else if(action.equals("/product_mod.do")){
				String code = request.getParameter("code");
				List<ProductDTO> list = productService.getCodeProductList(code);
				System.out.println("수정해야할 상품 개수 list.size() : " + list.size());
				request.setAttribute("product", list);
				
				nextPage = "/product_mod.jsp";
			}else if(action.equals("/product_mod_c.do")){
				ProductDTO dto = new ProductDTO();
				dto.setCode(request.getParameter("code"));
				dto.setName(request.getParameter("name"));
				dto.setPrice(Integer.parseInt(request.getParameter("price")));
				dto.setRepresentative(request.getParameter("representative"));
				dto.setDetails(request.getParameter("details"));
				System.out.println("dto.getName() : " + dto.getName());
				System.out.println("dto.getPrice() : " + dto.getPrice());
				
				int result = productService.modifyProduct(dto);
				System.out.println("상품 수정 result : " + result);
				
				nextPage = "/seller/product_management.do";
			}else if(action.equals("/product_reg.do")) {
				nextPage = "/product_reg.jsp";
			}else if(action.equals("/product_regInfo.do")) {
				ProductDTO dto = new ProductDTO();
				dto.setCode(request.getParameter("code"));
				dto.setId((String)session.getAttribute("id"));
				dto.setName(request.getParameter("name"));
				dto.setPrice(Integer.parseInt(request.getParameter("price")));
				dto.setRepresentative(request.getParameter("representative"));
				dto.setDetails(request.getParameter("details"));
				
				productService.registerProduct(dto);
				
				nextPage = "/seller/product_management.do";
			}else if(action.equals("/inquiry.do")){
				int in_nowPage = 1;
				if(request.getParameter("in_nowPage") != null) {
					in_nowPage = Integer.parseInt(request.getParameter("in_nowPage"));
				}
				String sellerId = (String)session.getAttribute("id");
				Integer in_total = inquiryService.getSellerInquiry(sellerId).size();
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
				List<InquiryDTO> inquiryList = inquiryService.getSellerInquiry(sellerId, in_nowPage, in_perPage);
				request.setAttribute("inquiry", inquiryList);
				request.setAttribute("in_nowPage", in_nowPage);
				request.setAttribute("in_lastPage", in_lastPage);
				request.setAttribute("in_begin", in_begin);
				request.setAttribute("in_end", in_end);
				
				nextPage = "/inquiry.jsp";
			}
			else {
				nextPage = "/err.jsp";
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("nextPage: " + nextPage);
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
}
