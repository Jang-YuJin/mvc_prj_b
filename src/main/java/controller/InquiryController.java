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
import service.InquiryService;

@WebServlet("/inquiry/*")
public class InquiryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	InquiryService inquiryService;

	public void init(ServletConfig config) throws ServletException {
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
			if (action.equals("/member_reg.do")) {
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
						nextPage = "/member_inquiryReg.jsp";
					}
					else {
						out.println("<script>alert('접근 제한. 일반 회원만 접근 가능.'); window.history.back();</script>");
						out.close();
					}
				}
			}else if(action.equals("/member_reg_c.do")) {
				InquiryDTO dto = new InquiryDTO();
				System.out.println("문의에서 코드 못 가져와? : " + request.getParameter("code"));
				dto.setId((String)session.getAttribute("id"));
				dto.setCode(request.getParameter("code"));
				dto.setTitle(request.getParameter("title"));
				dto.setContent(request.getParameter("content"));
				dto.setPi_id("0");
				
				int result = inquiryService.setMemberInquiry(dto);
				System.out.println("일반회원이 문의 등록 들어감? : " + result);
				System.out.println("뭐야 왜 코드 안나와?? " + request.getParameter("code"));
				nextPage = "/shop/detail.do?code=" + request.getParameter("code");
			}else if(action.equals("/member_inquiryView.do")) {
				Integer nowPage = 1;
				Integer total = inquiryService.getMemberInquiry((String)session.getAttribute("id")).size();
				int section = 5;// 5개씩 끊어서 페이지 숫자 나오도록 12345/678910/... 이런식
				int perPage = 9;
				if(request.getParameter("nowPage") != null) {
					nowPage = Integer.parseInt(request.getParameter("nowPage"));
				}
				List<InquiryDTO> list = inquiryService.getMemberInquiry((String)session.getAttribute("id"), nowPage, perPage);
				int lastPage = total % perPage == 0 ? total / perPage : total / perPage + 1;//한 페이지당 나오는 상품수를 12개로 정함
				int position = nowPage % section == 0 ? nowPage / section : nowPage / section + 1;//이전과 다음 사이에 나오는 페이지 수를 section개로 함
				int begin = (position - 1) * section + 1;
				int end = position * section;
				if(end > lastPage) {
					end = lastPage;
				}
				request.setAttribute("inquiry", list);
				request.setAttribute("nowPage", nowPage);
				request.setAttribute("lastPage", lastPage);
				request.setAttribute("begin", begin);
				request.setAttribute("end", end);
				request.setAttribute("id", session.getAttribute("id"));
				
				nextPage="/member_inquiryView.jsp";
			}else if(action.equals("/inquiryViewDetail.do")) {
				InquiryDTO dto = inquiryService.getToInquiry_id(Integer.parseInt(request.getParameter("inquiry_id")));//회원이 문의내역에 제목 클릭해서 넘어온 문의아이디(inquiry_id)만 select 한거임
				request.setAttribute("inquiry", dto);
				request.setAttribute("id", (String)session.getAttribute("id"));
				
				nextPage = "/member_inquiryViewDetail.jsp";//이 페이지 사실 회원이 문의 내용 보는건데 뭔가 여기도 쓸 수 있을거 같아서 씀
			}else if(action.equals("/inquiry_answerView.do")){
				request.setAttribute("pi_id", request.getParameter("pi_id"));
				request.setAttribute("code", request.getParameter("code"));
				nextPage = "/inquiry_answerView.jsp";
			}else if(action.equals("/inquiry_answer.do")){
				InquiryDTO dto = new InquiryDTO();
				dto.setPi_id(request.getParameter("pi_id"));
				System.out.println("판매자가 답변해주는데 세션에 왜 판매자 아이디가 안된다고 하는거야 : " + (String)session.getAttribute("id"));
				dto.setId((String)session.getAttribute("id"));
				dto.setCode(request.getParameter("code"));//TODO : 코드 던져줘?
				System.out.println("페이지에서 코드는 잘 줌??? : " + request.getParameter("code"));
				System.out.println("야 DTO 코드 들어왔어??? : " + dto.getCode());
				dto.setTitle(request.getParameter("title"));
				dto.setContent(request.getParameter("content"));
				System.out.println("DTO에는 잘 들어갔냐ㅑㅑㅑㅑ : " + dto.getId());
				int result = inquiryService.setSellerInquiry(dto);
				System.out.println("답변 잘 들어갔냐???? : " + result);
				
				nextPage = "/seller/inquiry.do";
			}
			else {
				nextPage = "/err.jsp";
			}
			System.out.println("nextPage: " + nextPage);
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
