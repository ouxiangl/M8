package entrega;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class regis2
 */
@WebServlet("/regis")
public class Regis extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Properties prop = Proper.getProp();
	private static final Pattern regexEmail = Pattern.compile(prop.getProperty("valid.email"));
	private static final Pattern regexNick = Pattern.compile(prop.getProperty("valid.nick"));
	private static final Pattern regexPass = Pattern.compile(prop.getProperty("pass"));
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Regis() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nick = request.getParameter("nick");
		String pass = request.getParameter("password");
		String email = request.getParameter("email");
		Matcher vEmail = regexEmail.matcher(email);
		Matcher vNick = regexNick.matcher(nick);
		Matcher vPass = regexPass.matcher(pass);
		Logger.getLogger(vPass.toString());
		if(vEmail.matches() && vPass.matches() && vNick.matches()) {
			try {
				if(LoginRegistCase.insertUser(nick,pass,email)) {
					getServletContext().getRequestDispatcher("/html/registreOk.html").forward(request, response);
				}else {
					getServletContext().getRequestDispatcher("/html/REGISTRE_NOT_OK.html").forward(request, response);
				}
				
			} catch (ServletException | IOException | SQLException e) {
				e.printStackTrace();
			}
		}else {
			try {
				getServletContext().getRequestDispatcher("/html/REGISTRE_NOT_OK.html").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
