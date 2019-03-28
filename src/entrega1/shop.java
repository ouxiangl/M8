package entrega1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class shop
 */
@WebServlet("/shop")
public class shop extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public shop() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println(login.sesion.getAttribute("usuario"));
		//String nick = (String) login.sesion.getAttribute("usuario");
		String nick = request.getParameter("nick");
		String payment = request.getParameter("payment");
		String quantity = request.getParameter("quantity");
		String texto = request.getParameter("texto");
		String[] producto= request.getParameterValues("img");
		boolean vNick = nick.matches("^[A-Za-z0-9]{1,10}$");
		boolean vTexto = texto.matches("^(.|\r|\n){0,200}$");
		boolean vQuantity = quantity.matches("^[1-5]$");
		System.out.println("x1");
		if(vNick && vTexto && vQuantity) {
			System.out.println("x2");
			if(LoginRegistCase.existUser(nick)) {
				System.out.println("x3");
				if(LoginRegistCase.insertVenta(nick,payment,quantity,texto,producto)) {
					System.out.println("x4");
					getServletContext().getRequestDispatcher("/html/SHOP_OK.html").forward(request, response);
				}
			}
		}
		getServletContext().getRequestDispatcher("/html/SHOP_NOT_OK.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
