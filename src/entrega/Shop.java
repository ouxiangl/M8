package entrega;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class shop
 */
@WebServlet("/shop")
public class Shop extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Shop() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Properties prop = Proper.getProp();
		String nick = request.getParameter("nick");
		String payment = request.getParameter("payment");
		String quantity = request.getParameter("quantity");
		String texto = request.getParameter("texto");
		String[] producto= request.getParameterValues("img");
		
		boolean vNick = nick.matches(prop.getProperty("nick"));
		boolean vTexto = texto.matches(prop.getProperty("texto"));
		boolean vQuantity = quantity.matches(prop.getProperty("quantity"));
		if(vNick && vTexto && vQuantity && LoginRegistCase.existUser(nick) && LoginRegistCase.insertVenta(nick,payment,quantity,texto,producto)) {
			getServletContext().getRequestDispatcher("/html/SHOP_OK.html").forward(request, response);
		}
		getServletContext().getRequestDispatcher("/html/SHOP_NOT_OK.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
