package bbdd;

/**
 * @author Jesús Manuel Ruiz Verdejo
 */
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.parsers.*;
import org.w3c.dom.*;

public class Conexion {

	private Connection conexion;
	private String usuario;
	private String clave;
	private String baseDatos;
	private String forname;
	private String servidor;

	public Conexion() {
	}

	public Conexion(Connection conexion, String usuario, String clave, String baseDatos, String forname,
			String servidor) {
		this.conexion = conexion;
		this.usuario = usuario;
		this.clave = clave;
		this.baseDatos = baseDatos;
		this.forname = forname;
		this.servidor = servidor;
	}

	public void leerXML() {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {

			DocumentBuilder builder = factory.newDocumentBuilder();
			Document documento = builder.parse(new File("config.xml"));
			documento.getDocumentElement().normalize();

			NodeList config = documento.getElementsByTagName("config");

			Node bd = config.item(0);

			Element elemento = (Element) bd;

			this.forname = elemento.getElementsByTagName("forname").item(0).getTextContent();
			this.servidor = elemento.getElementsByTagName("servidor").item(0).getTextContent();
			this.baseDatos = elemento.getElementsByTagName("basedatos").item(0).getTextContent();
			this.usuario = elemento.getElementsByTagName("usuario").item(0).getTextContent();
			this.clave = elemento.getElementsByTagName("clave").item(0).getTextContent();

			/* Comprobamos si esta recogiedo los datos del XML */
			System.out.println("Conector Driver JDBC: " + forname);
			System.out.println("Servidor: " + servidor);
			System.out.println("Nombre de la Base de Datos: " + baseDatos);
			System.out.println("Usuario: " + usuario);
			System.out.println("Clave: " + clave);

		} catch (Exception e) {

		}

	}

	public void conectar() {

		try {
			Class.forName(getForname());

			/*
			 * NOTA IMPORTANTE: getServidor y getBaseDatos lo concatenamos con +
			 * para que lo admita como un unico String
			 */

			this.conexion = DriverManager.getConnection(getServidor() + getBaseDatos(), getUsuario(), getClave());

			System.out.println("La conexión se ha realizado correctamente");

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cerrarConexion() {

		try {
			conexion.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void consultaParaProbar() {
		try {

			Statement sentencia = getConexion().createStatement();

			ResultSet rs = sentencia.executeQuery("SELECT * FROM t_accesos");

			while (rs.next()) {

				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getBaseDatos() {
		return baseDatos;
	}

	public void setBaseDatos(String baseDatos) {
		this.baseDatos = baseDatos;
	}

	public String getForname() {
		return forname;
	}

	public void setForname(String forname) {
		this.forname = forname;
	}

	public String getServidor() {
		return servidor;
	}

	public void setServidor(String servidor) {
		this.servidor = servidor;
	}

}
