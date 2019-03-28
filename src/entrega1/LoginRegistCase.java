package entrega1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginRegistCase {
	public static boolean loginUser(String name, String pass) {
		Connection con = null;
		con = ConnectionDB.getConnection();
		PreparedStatement ps = null;
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from users where nick = '"+name+"' AND pass = '"+pass+"'");
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if(ps != null) {
					ps.close();
				}
				if(con != null) {
					con.close();
				}
				if(stmt != null) {
					stmt.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	public static boolean insertUser(String name, String pass, String mail) throws SQLException {
		Connection con = null;
		con = ConnectionDB.getConnection();
		PreparedStatement ps = null;
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from users where nick = '"+name+"'");
			if(!rs.next()) {
				String consulta = "insert into \"PUBLIC\".\"USERS\" (\"NICK\",\"PASS\",\"EMAIL\")"+"VALUES(?,?,?);";
				ps = con.prepareStatement(consulta);
				ps.setString(1,name);
				ps.setString(2,pass);
				ps.setString(3,mail);
				int resultado = ps.executeUpdate();
				if(resultado==1) {
					return true;
				}
			}
		}catch(SQLException e) {
			System.out.println("Error en LoginRegistCase");
			e.printStackTrace();
		}
		finally {
			try {
				if(ps != null) {
					ps.close();
				}
				if(con != null) {
					con.close();
				}
				if(stmt != null) {
					stmt.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	public static boolean existUser(String nombre) {
		Connection con = null;
		con = ConnectionDB.getConnection();
		PreparedStatement ps = null;
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from users where nick = '"+nombre+"'");
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(ps != null) {
					ps.close();
				}
				if(con != null) {
					con.close();
				}
				if(stmt != null) {
					stmt.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	public static boolean insertVenta(String nick,String payment,String quantity,String texto,String[] producto) {
		String productos = getProducto(producto);
		String precio = getPrecio(producto,Integer.parseInt(quantity));
		Connection con = null;
		con = ConnectionDB.getConnection();
		PreparedStatement ps = null;
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			String consulta = "INSERT INTO \"PUBLIC\".\"VENTAS\"\r\n" + 
					"( \"NICK\", \"PRODUCTS\", \"PAYMENT\", \"QUANTITY\", \"AMOUNT\", \"COMMENTS\" )\r\n" + 
					"VALUES (?,?,?,?,?,?)";
			ps = con.prepareStatement(consulta);
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
			System.out.println("Error en LoginRegistCase");
			e.printStackTrace();
		}
		finally {
			try {
				if(ps != null) {
					ps.close();
				}
				if(con != null) {
					con.close();
				}
				if(stmt != null) {
					stmt.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
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
					ii = listaPrecio.length;
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
