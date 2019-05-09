package entrega;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Ex1
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
       

    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
