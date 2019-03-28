package entrega1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	static String DRIVER = "org.hsqldb.jdbc.JDBCDriver";
	static String URL = "jdbc:hsqldb:file:C:\\Users\\LIN\\eclipse-workspace\\M8\\WebContent\\WEB-INF\\db\\srv_db";
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL,"sa","");
			System.out.println("conn: "+conn);
		}catch(ClassNotFoundException | SQLException e) {
			System.out.println("Error en connectionDB");
			System.err.println("Error: "+e);
		}
		return conn;
	}
	public ConnectionDB() {
		super();
	}
}
