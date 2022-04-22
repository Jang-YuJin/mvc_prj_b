package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.MemberDTO;
import model.MylistDTO;
import service.MemberService;
import service.MylistService;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberService service;
	MylistService mylistService;
	
	public void init(ServletConfig config) throws ServletException {
		service = new MemberService();
		mylistService = new MylistService();
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
		
//		String prePage = "";
//		if(!"/logout.do".equals(action) || !"/login.do".equals(action)) {
//			prePage = action;
//		}
//		if(null == prePage && "/logout.do".equals(action) && "/login.do".equals(action)) {
//			prePage = request.getParameter("prePage");
//		}
////		if("".equals(prePage)) {
////			prePage = request.getParameter(prePage);
////		}
//		System.out.println("prePage : " + prePage);
		
		try {
			if (action.equals("/join.do")) {
				nextPage = "/join.jsp";
			}else if(action.equals("/joinConfirm.do")){
				MemberDTO dto = new MemberDTO();
				String name = request.getParameter("name");
				String id = request.getParameter("id");
				String password = request.getParameter("pw");
				String passwordC = request.getParameter("pw_ch");
				String tel = request.getParameter("tel");
				String address = request.getParameter("address");
				String[] birth = request.getParameterValues("birth");
				String birthIn = birth[0] + "-" + birth[1] + "-" + birth[2];
				
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				if("".equals(name) || name == null ||
						"".equals(id) || id == null ||
						"".equals(password) || password == null ||
						"".equals(passwordC) || passwordC == null ||
						"".equals(tel) || tel == null ||
						"".equals(address) || address == null ||
						birth == null || "".equals(birth[0]) || "".equals(birth[1]) || "".equals(birth[2])) {
					out.println("<script>alert('모든 내용을 입력해주세요.'); location.href='/mpro/member/join.do';</script>");
					out.close();
				}else {
					if(password.equals(passwordC)) {
						System.out.println(service.isIdUnique(id));
						if(service.isIdUnique(id)) {
							out.println("<script>alert('아이디가 중복됩니다.'); location.href='/mpro/member/join.do';</script>");
							out.close();
						}else {
							dto.setName(name);
							dto.setId(id);
							dto.setPassword(passwordC);
							dto.setTel(tel);
							dto.setAddress(address);
							dto.setBirth(Date.valueOf(birthIn));
							int result = service.setMemberInfo(dto);
							response.sendRedirect("/mpro/shop/main.do");//회원가입 정보 안보이게 하려고 redirect 씀
						}
					}else{
						out.println("<script>alert('비밀번호와 비밀번호 확인이 다릅니다.'); location.href='/mpro/member/join.do';</script>");
						out.close();
					}
				}
			}else if(action.equals("/login.do")){
				nextPage = "/login.jsp";
			}else if(action.equals("/loginConfirm.do")){
				String id = request.getParameter("id");
				String password = request.getParameter("password");
				
				MemberDTO member = service.getAllInfo(id, password);
				if(member.getId() != null) {
					System.out.println("session 저장 시작");
					session.setAttribute("id", member.getId());
					session.setAttribute("authority_id", member.getAuthority_id());
					session.setAttribute("password", member.getPassword());
					session.setAttribute("birth", member.getBirth());
					session.setAttribute("name", member.getName());
					session.setAttribute("tel", member.getTel());
					session.setAttribute("address", member.getAddress());
					session.setAttribute("com_name", member.getCom_name());
					session.setAttribute("cr_no", member.getCr_no());
					session.setAttribute("entry", member.getEntry());
				}else {
					PrintWriter out = response.getWriter();
					out.println("<script>alert('회원정보가 없습니다.'); location.href='/mpro/member/login.do';</script>");
					out.close();
				}
				
				if("10".equals((String)session.getAttribute("authority_id"))){
					nextPage = "/admin/admin_main.do";
				}else if("20".equals((String)session.getAttribute("authority_id")) && "1".equals((String)session.getAttribute("entry"))){
					nextPage = "/seller/seller_main.do";
				}else if("30".equals((String)session.getAttribute("authority_id"))) {
					nextPage = "/shop/main.do";
				}else {
					PrintWriter out = response.getWriter();
					out.println("<script>alert('권한이 없는 회원입니다.'); location.href='/mpro/member/login.do';</script>");//TODO : 나중에 문구 고치기
					out.close();
				}
//				System.out.println("loginConfirm request.getParameter(nextPage) -> " + request.getParameter("nextPage"));
//				nextPage = request.getParameter("nextPage");
//				response.sendRedirect("/mpro/shop" + prePage);
			}else if(action.equals("/logout.do")){
				
//				PrintWriter out = response.getWriter();
//				out.println("<script>history.back(-1);</script>");//TODO:이전페이지 주소를 받아놔서 그걸 sendRedirect하래... 근데 이전 페이지 주소는 어떻게 받지...?
//				out.close();
				session.invalidate();
//				System.out.println("로그아웃에서 prePage : " + prePage);
//				response.sendRedirect("/mpro/shop" + prePage);
				nextPage = "/shop/main.do";
			}else if(action.equals("/mypage.do")){
				String id = (String)session.getAttribute("id");
				String authority_id = (String)session.getAttribute("authority_id");
				System.out.println("session id : " + id);
				System.out.println("session authority_id : " + authority_id);
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				if("".equals(id) || id == null) {
					out.println("<script>alert('로그인 해주세요.'); location.href='/mpro/member/login.do';</script>");
					out.close();
				}else {
					if("30".equals(authority_id)) {
						request.setAttribute("code", request.getParameter("code"));
						nextPage = "/mypage_main.jsp";
					}
					else {
						out.println("<script>alert('접근 제한. 일반 회원만 접근 가능.'); window.history.back();</script>");
						out.close();
					}
				}
			}else if(action.equals("/member_mod.do")){
				MemberDTO member = service.getMemberToID((String)session.getAttribute("id"));
				request.setAttribute("member", member);
				
				nextPage = "/member_mod.jsp";
			}else if(action.equals("/member_mod_c.do")){
				MemberDTO dto = new MemberDTO();
				dto.setId((String)session.getAttribute("id"));
				dto.setName(request.getParameter("name"));
				dto.setBirth(Date.valueOf(request.getParameter("birth")));
				dto.setTel(request.getParameter("tel"));
				
				int result = service.modifyMember(dto);
				System.out.println("회원수정 됐냐 : " + result);
				
				nextPage = "/member/member_mod.do";
			}else if(action.equals("/member_delte.do")){
				nextPage = "/member_delete.jsp";
			}else if(action.equals("/member_delete_c.do")){
				int result = service.erasureMember((String)session.getAttribute("id"));
				System.out.println("회원 삭제 잘 됨? : " + result);
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('탈퇴처리 되었습니다.'); location.href='/mpro/member/logout.do';</script>");
				out.close();
				
			}else if(action.equals("/order_list.do")){
				List<MylistDTO> list = mylistService.getOrderMylistToId((String)session.getAttribute("id"));
				request.setAttribute("mylist", list);
				
				nextPage = "/order_list.jsp";
			}else if(action.equals("/cart_list.do")){
				List<MylistDTO> list = mylistService.getCartMylistToId((String)session.getAttribute("id"));
				request.setAttribute("mylist", list);
				
				nextPage = "/cart_list.jsp";
			}else if(action.equals("/cart_order.do")){
				String[] orders = request.getParameterValues("order");
				int result = 0;
				for(int i = 0; i < orders.length; i++) {
					result = mylistService.modifyOrder(orders[i]);
					System.out.println("장바구니가 주문으로 update 됨? : " + result);
				}
				
				nextPage = "/member/cart_list.do";
			}else if(action.equals("/order.do")){
				String code = request.getParameter("code");
				if("30".equals((String)session.getAttribute("authority_id"))) {
					int result = mylistService.setOrder((String)session.getAttribute("id"), code);
					System.out.println("주문으로 잘 들어갔니??? : " + result);
					nextPage = "/shop/detail.do?code=" + code;
				}else if("20".equals((String)session.getAttribute("authority_id")) || "10".equals((String)session.getAttribute("authority_id"))){
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('잘못된 접근입니다.'); location.href='/mpro/shop/detail.do?code=" + code + "';</script>");
					out.close();
				}else {
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('로그인 해주세요.'); location.href='/mpro/member/login.do';</script>");
					out.close();
				}
			}else if(action.equals("/cart.do")){
				String code = request.getParameter("code");
				if("30".equals((String)session.getAttribute("authority_id"))) {
					int result = mylistService.setCart((String)session.getAttribute("id"), code);
					System.out.println("장바구니로 잘 들어갔니??? : " + result);
					nextPage = "/shop/detail.do?code=" + code;
				}else if("20".equals((String)session.getAttribute("authority_id")) || "10".equals((String)session.getAttribute("authority_id"))){
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('잘못된 접근입니다.'); location.href='/mpro/shop/detail.do?code=" + code + "';</script>");
					out.close();
				}else {
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('로그인 해주세요.'); location.href='/mpro/member/login.do';</script>");
					out.close();
				}
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
