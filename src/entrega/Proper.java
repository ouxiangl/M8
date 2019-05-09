package entrega;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class Proper {
	private static Properties prop;
	private InputStream is;
	Proper(){
		try {
			is = new FileInputStream("C:\\Users\\LIN\\eclipse-workspace\\M8\\configuracion.properties");
			prop.load(is);
		} catch(IOException e) {
			Logger.getLogger(e.toString());
		}
	}
	public static Properties getProp(){
		return prop;
	}

}