package entrega1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Ex1
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
       

    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sesion = request.getSession();
		String user = request.getParameter("user");
		String pass = request.getParameter("password");
		if(LoginRegistCase.loginUser(user,pass)) {
			sesion.setAttribute("usuario", user);
			response.setContentType("text/html;charset=UTF-8");
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", "http://localhost:8080/M8/index");
		}else {
			getServletContext().getRequestDispatcher("/html/LOGIN_NOT_OK.html").forward(request, response);
		}
		/*
		if ("lin".equals(user) && "lin".equals(pass)) {
			response(response, "login ok");
		} else {
			response(response, "invalid login");
		}
		*/
		
	}
	/*
	private void response(HttpServletResponse resp, String msg)
			throws IOException {
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<t1>" + msg + "</t1>");
		out.println("</body>");
		out.println("</html>");
	}
	*/
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
