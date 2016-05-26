package prog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import java.util.Scanner;


public class Eliminacion {

	public static void main(String[] args) {
		{//Conexión
			String cadCon="jdbc:mysql://localhost:3306/dam2016" + "?useUnicode=true&characterEncoding = UTF-8";
			String usuario="root";
			String passwd="Alumno";
			
			System.out.print("Estrellas: ");
			int buscar=Integer.parseInt(teclado.nextLine());
			
			String c1="SELECT *" + "FROM Hoteles" + "ORDER BY nombre";
			String c2="DELETE FROM Hoteles" + "WHERE estrellas=%d";
			
			c1=String.format(c1, buscar);
			c2=String.format(c2, buscar);
			
			try (
				Connection cn=DriverManager.getConnection(cadCon,usuario,passwd);
				Statement st=cn.createStatement();
				ResultSet rs=st.executeQuery(c1);
				)
			{//Cabecera
				System.out.printf("%-20s %7f %5d %5s\n", "Hotel","Precio","Estrellas","Wifi");
				int cont=0;
				while(rs.next())
				{//Acceso a los campos
					String nom=rs.getString("Nombre");
					Double precio=rs.getDouble("Precio");
					int estrellas=rs.getInt("Estrellas");
					Boolean wifi=rs.getBoolean("Wifi");
					
					//Mostrar línea de datos
					System.out.printf("%-20s %7.2f %5s %5s\n",nom,precio,estrellas,wifi? "Si":"No");
					cont++;
				}
				System.out.printf("En lista: %d hoteles\n",cont);
				if(cont>0)
				{
					System.out.printf("¿Eliminar estos hoteles?(s/N)");
					String resp=teclado.nextLine().trim().toLowerCase();
					if(resp.length()>0)
					{
						eliminados=st.executeUpdate(c2);
					}
				}
				}
			}
			

	}

}
