/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQLite;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author alumno
 */
public class SQLite {
    public static void main(String args[])
    {
        Connection conexion;
        Statement stmt;
        ResultSet result;
        String query1;
        String query2;
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            conexion = DriverManager.getConnection("jdbc:sqlite:C:/BaseDatos/SQLite/ejemplo1.db");
            stmt = conexion.createStatement();
            
            // Query1: Obtener el apellido, oficio, salario de los empleados del departamento 30.
            query1 = "SELECT apellido,oficio,salario FROM empleados WHERE dept_no = 30";
            
            // Query2: Apellidos, nombre del departamento y salario del empleado con mayor salario.
            query2 = "SELECT apellido,dnombre,salario FROM empleados,departamentos WHERE empleados.dept_no = departamentos.dept_no AND salario = (SELECT MAX(salario) FROM empleados)";
            
            result = stmt.executeQuery(query1);
            System.out.println("Resultado de la 1ª consulta: ");
            while(result.next())
            {
                
                System.out.println("Apellido: "+result.getString(1)+". Oficio: "+result.getString(2)+". Salario: "+result.getInt(3));
            }
            System.out.println(" ");
            result = stmt.executeQuery(query2);
            System.out.println("Resultado de la 2ª consulta: ");
            while(result.next())
            {
                System.out.println("Apellido: "+result.getString(1)+". Departamento: "+result.getString(2)+". Salario: "+result.getInt(3));
            }
            
            result.close();
            stmt.close();
            conexion.close();
        }
        catch(SQLException sql){ System.out.println("Error: "+sql.getMessage());    }
        catch(ClassNotFoundException cnfe)  { System.out.println("Error: "+cnfe.getMessage());  }
    }
}