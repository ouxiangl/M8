package entrega;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Logger;

public class LoginRegistCase {
	private LoginRegistCase() {
	    throw new IllegalStateException("Utility class");
	  }
	public static boolean loginUser(String name, String pass) {
		Properties prop = Proper.getProp();
		
		String select = "select * from "+prop.getProperty("bbdd.tabla.user")+" where nick = '"+name+"' AND pass = '"+pass+"'";
		try(
			Connection con= ConnectionDB.getConnection();
			PreparedStatement preparedStatement =  con.prepareStatement(select);
			ResultSet rs = preparedStatement.executeQuery(select);
		){
			//No pasa por aqui...!!!!!!!!!!!!!!!!!!!!!!!!!!!
			Logger.getLogger("rs: "+rs.toString());
			if(rs.next()) {
				return true;
			}
		} catch (Exception e) {
			Logger.getLogger("Error, funcion loginUser");
		}
		Logger.getLogger(select);
		Logger.getLogger("No existe usuario");
		return false;
	}
	public static boolean insertUser(String name, String pass, String mail) throws SQLException {
		
		String consulta = "insert into \"PUBLIC\".\"USERS\" (\"NICK\",\"PASS\",\"EMAIL\")"+"VALUES(?,?,?);";
		String select = "select * from users where nick = '"+name+"'";
		try (
			Connection con = ConnectionDB.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(select); 
			PreparedStatement ps = con.prepareStatement(consulta)){
			if(!rs.next()) {
				ps.setString(1,name);
				ps.setString(2,pass);
				ps.setString(3,mail);
				int resultado = ps.executeUpdate();
				if(resultado==1) {
					return true;
				}
			}
		}catch(SQLException e) {
			Logger.getLogger("Error en insertUser");
		}
		return false;
	}
	public static boolean existUser(String nombre) {
		String select = "select * from users where nick = '"+nombre+"'";
		try (
				Connection con = ConnectionDB.getConnection();
				Statement stmt = con.createStatement();
				PreparedStatement ps = null;
				ResultSet rs = stmt.executeQuery(select);
			){
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			Logger.getLogger("Error, funcion existUser");
		}
		return false;
	}
	public static boolean insertVenta(String nick,String payment,String quantity,String texto,String[] producto) {
		String productos = null;
		String precio = null;
		try{
			productos = getProducto(producto);
			precio = getPrecio(producto,Integer.parseInt(quantity));
		}
		catch(Exception e){
			Logger.getLogger("Error, funcion insertVenta");
			return false;
		}
		
		String consulta = "INSERT INTO \"PUBLIC\".\"VENTAS\"\r\n" + 
				"( \"NICK\", \"PRODUCTS\", \"PAYMENT\", \"QUANTITY\", \"AMOUNT\", \"COMMENTS\" )\r\n" + 
				"VALUES (?,?,?,?,?,?)";
		try (Connection con = ConnectionDB.getConnection();
			Statement stmt = con.createStatement();
			PreparedStatement ps = con.prepareStatement(consulta)
		){
			
			ps.setString(1,nick);
			ps.setString(2,productos);
			ps.setString(3,payment);
			ps.setInt(4,Integer.parseInt(quantity));
			ps.setString(5,precio);
			ps.setString(6,texto);
			int resultado = ps.executeUpdate();
			if(resultado==1) {
				return true;
			}
		}catch(SQLException e) {
			Logger.getLogger("Error en insertVenta");
		}
		return false;	
	}
	public static String getProducto(String[] producto) {
		String linea = "";
		if(producto == null) {
			return null;
		}else {
			for(int i = 0 ; i<producto.length;i++) {
				linea = new StringBuilder(linea).append(producto[i]).toString();
			}
		}
		return linea;
	}
	public static String getPrecio(String[] producto,int num) {
		int precio = 0;
		String[][] listaPrecio = {{"1","10"},{"2","20"},{"3","30"},{"4","40"},{"5","50"}};
		for(int i = 0; i<producto.length;i++) {
			for(int ii = 0; ii < listaPrecio.length; ii++) {
				if(producto[i].equals(listaPrecio[ii][0]) && isNumeric(listaPrecio[ii][1])) {
					precio+=num*Integer.parseInt(listaPrecio[ii][1]);
				}
			}
		}
		return Integer.toString(precio);
		
	}
	public static boolean isNumeric(String str) { 
		  try {  
			  Integer.parseInt(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
		}
}
