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
 * Servlet implementation class EnrollController
 */
@WebServlet("/member/register.do")  // 서블릿 주소? html에 form태그랑 연결할 때 사용함
public class EnrollController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");  // 인코딩 한글로 하기
		String memberId = request.getParameter("member-id");
		String memberPW = request.getParameter("member-pw");
		String memberName = request.getParameter("member-name");
		int memberAge = Integer.parseInt(request.getParameter("member-age"));
		String memberGender = request.getParameter("member-gender");
		String memberEmail = request.getParameter("member-email");
		String memberPhone = request.getParameter("member-phone");
		String memberAddress = request.getParameter("member-address");
		String memberHobby = request.getParameter("member-hobby");
		
		Member member = new Member(memberId, memberPW, memberName, memberAge, memberGender, memberEmail, memberPhone, memberAddress, memberHobby);
		// DAO 서비스 호출
		MemberService service = new MemberService();
		// INSERT INTO MEMBER_TBL VALUES(?,?,?,?,?,?,?,?,?,DEFAULT,DEFAULT,DEFAULT)
		int result = service.insertMember(member);
		if(result > 0) {
			// 성공하면 성공 페이지로 이동 -> RequestDispatcher
			request.setAttribute("msg", "회원가입 성공했어요.");
			request.setAttribute("url", "/member/logout.do");
			request.getRequestDispatcher("/member/serviceSuccess.jsp").forward(request, response);
			
		}
		else {
			// 실패
			request.getRequestDispatcher("/member/serviceFailed.jsp").forward(request, response);
		}
	}

}
