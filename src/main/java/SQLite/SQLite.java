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
        String query;
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            conexion = DriverManager.getConnection("jdbc:sqlite:C:/BaseDatos/SQLite/ejemplo1.db");
            stmt = conexion.createStatement();
            
            query = "SELECT * FROM empleados";
            
            result = stmt.executeQuery(query);
            
            while(result.next())
            {
                System.out.println("ID: "+result.getInt(1)+" Apellido: "+result.getString(2)+" Oficio: "+result.getString(3)+" Ciudad: "+result.getInt(4));
            }
            
            result.close();
            stmt.close();
            conexion.close();
        }
        catch(SQLException sql){ System.out.println("Error: "+sql.getMessage());    }
        catch(ClassNotFoundException cnfe)  { System.out.println("Error: "+cnfe.getMessage());  }
    }
}
