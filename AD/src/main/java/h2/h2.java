/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author JavierPC
 */
public class h2 
{
    public static void main(String args[])
    {
        Connection conexion;
        Statement stmt;
        ResultSet result;
        String query = "SELECT apellido,dnombre,salario FROM empleados,departamentos WHERE empleados.dept_no = departamentos.dept_no AND salario = (SELECT MAX(salario) FROM empleados)";
        
        try
        {
            Class.forName("org.h2.Driver");
            
            conexion = DriverManager.getConnection("jdbc:h2:C:/BasesDatos/H2/bbdd/ejemplo1.db", "javier", "");
            stmt = conexion.createStatement();
            result = stmt.executeQuery(query);
            
            while(result.next())
            {
                System.out.println("----------- RESULTADO  ----------");
                System.out.println("---------------------------------");
                System.out.println("| Apellido: "+result.getString(1)+"\t\t|\n| Departamento: "+result.getString(2)+"\t\t|\n| Salario: "+result.getDouble(3)+"\t\t|");
                System.out.println("---------------------------------");
            }
            
            result.close();
            stmt.close();
            conexion.close();
        }
        catch(SQLException | ClassNotFoundException e)  { e.printStackTrace();}
        
    }
}
