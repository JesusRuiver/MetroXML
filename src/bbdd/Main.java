package bbdd;

public class Main {
	
	private Conexion miConexion = new Conexion();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Conexion conexion = new Conexion();
		
		conexion.leerXML();//Comprobación de si estamos leyendo el XML y capturando los datos para la conexion.
		
		conexion.conectar();//Conectamos a la base de datos
		
		conexion.consultaParaProbar(); //Hacemos una consulta de prueba y la mostramos por consola.
		
		conexion.cerrarConexion();
	}

}
