package prog;

import java.sql.*;

public class Milistado {

	public static void main(String[] args)
	{//Conexión
		String cadCon="jdbc:mysql://localhost:3306/dam2016" + "?useUnicode=true&characterEncoding = UTF-8";
		String usuario="root";
		String passwd="Alumno";
		
		String cons="SELECT *" + "FROM Hoteles" + "ORDER BY nombre";
		
		try (
		Connection cn=DriverManager.getConnection(cadCon,usuario,passwd);
		Statement st=cn.createStatement();
		ResultSet rs=st.executeQuery(cons);
		)
		{
			//Cabecera del listado
			System.out.printf("%-20s %7f %5d %5s\n", "Hotel","Precio","Estrellas","Wifi");
			int cont=0;
			while(rs.next())
			{
				//Acceso a los campos
				String nom=rs.getString("Nombre");
				Double precio=rs.getDouble("Precio");
				int estrellas=rs.getInt("Estrellas");
				Boolean wifi=rs.getBoolean("Wifi");
				
				//Tratamiento
				System.out.printf("%-20s %7.2f %5s %5s\n",nom,precio,estrellas,wifi? "Si":"No");
				cont++;
			}
			System.out.println("En lista: "+cont+"hoteles");
		}
		
		catch(SQLException e)
		{
			System.out.println("BD> "+e.getMessage());
		}
				
		
	}

}