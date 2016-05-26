package prog;

import java.util.Scanner;
import java.sql.*;

public class ActualizarPrecios {

	public static void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		
		//Conexión con la base de datos
		String cadenaConexion="jdbc:mysql://localhost:3306/sql02_jardineria" + "useUnicode=true&characterEncoding=UTF-8";
		String usuario="root";
		String passwd="alumno";
		
		String cons1="select CodigoProducto, Nombre, Gama, " +
		"PrecioVenta, PrecioVenta * 0.90 as NuevoVenta " +
		"FROM productos" +
		"WHERE Gama='Herramientas'" +
		"ORDER BY Nombre";
		
		String cons2="UPDATE productos set PrecioVenta=PrecioVenta * 0.90 " +
		"WHERE Gama='Herramientas'";
		
		try (
				Connection cn=DriverManager.getConnection(cadenaConexion,usuario,passwd);
				//Instrucción SQL sin parámetros
				Statement st=cn.createStatement();
				//Ejecución de la consulta SQL
				ResultSet rs=st.executeQuery(cons1);
			)
		{
			System.out.printf("%-12s %-60s %-15s %8s %8s\n","Codigo","Producto","Gama","Precio","Nuevo");
			System.out.printf("%-12s %-60s %-15s %8s %8s\n","Codigo","Producto","Gama","Precio","Nuevo");
		
			int cont=0;
			while(rs.next())
			{
				//Acceso a los campos
				String codigo=rs.getString("CodigoProducto");
				String producto=rs.getString("Nombre");
				String gama=rs.getString("Gama");
				Double precio=rs.getDouble("Precio");
				Double nuevo=rs.getDouble("Nuevo");
				
				//Listado
				System.out.printf("%-12s %-60s %-15s %8.2f %8.2f\n","Codigo","Producto","Gama","Precio","Nuevo");
				cont++;
			}
			System.out.printf("Total productos en lista: %d productos\n",cont);
			if(cont>0)
			{
				System.out.print("¿Actualizar precios? (s/N)");
				String resp=teclado.nextLine().trim().toLowerCase();
				cont=0;
				if(resp.length()>0)
				{
					char r=resp.charAt(0);
					if(r=='s')
					{
						cont=st.executeUpdate(cons2);
					}
				}
				System.out.printf("Se han actualizado los precios de %d productos",cont);
			}
		}
		catch (SQLException e)
		{
			System.out.println("Error BD: "+e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println("Error: "+e.getMessage());
		}
	}
}