package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/member/update.do")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");  // 한글깨짐 방지
		String memberId = request.getParameter("member-id");
		String memberPw = request.getParameter("member-pw");
		String memberGender = request.getParameter("member-gender");
		String memberEmail = request.getParameter("member-email");
		String memberPhone = request.getParameter("member-phone");
		String memberAddress = request.getParameter("member-address");
		String memberHobby = request.getParameter("member-hobby");
		// UPDATE MEMBER_TBL SET MEMBER_PW = ?, MEMBER_EMAIL = ?, MEMBER_PHONE = ?, MEMBER_ADDRESS = ?, MEMBER_HOBBY = ?, UPDATE_DATE = SYSDATE WHERE MEMBER_ID = ?
		MemberService service = new MemberService();
//		Member member = new Member(memberId, memberPw, memberEmail, memberPhone, memberAddress, memberHobby);
		Member member = new Member(memberId, memberPw, memberGender, memberEmail, memberPhone, memberAddress, memberHobby);  // 성별 추가
//		int result = service.updateMember(memberId, memberPw, memberEmail, memberPhone, memberAddress, memberHobby);
		int result = service.updateMember(member);
		if (result > 0) {
			// 성공하면 메인페이지
			response.sendRedirect("/index.jsp");
		} else {
			// 실패하면 에러페이지
			request.setAttribute("msg", "수정이 완료되지 않았습니다.");
			request.getRequestDispatcher("/member/serviceFailed.jsp").forward(request, response);
		}
	}

}
