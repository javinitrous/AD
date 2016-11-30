/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posibleExamen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author alumno
 */
public class posibleExamen {
    
    /*
    1. Muestra todos los departamentos. (Statement)
    2. Muestra los empleados de un departamento. (prepared statement)
    3. Permita la modificación de los datos de un departamento. (PreparedStatement -> executeUpdate)
    4. Inserción de un empleado. (PreparedStatement -> ExecuteUpdate)
    5. Ejecución de un script.  (Statement -> ExecuteUpdate)
    6. Ejecución de un procedimiento de la base de datos. (CallableStatement)
    7. Creación de un informe sobre departamentos. (JasperReport  Compile)
    8. Obtener las columnas de la tabla departamentos. (GetMetadata -> getcolumns)
    */
    
    public static void main(String args[])
    {
        int opcion = 0;
        Scanner sc = new Scanner(System.in);
                
        do
        {
            System.out.println(" === MENÚ BASE DE DATOS === ");
            System.out.println("1. Mostrar todos los departamentos.");
            System.out.println("2. Mostrar los empleados de un departamento.");
            System.out.println("3. Modificar los datos de un departamento.");
            System.out.println("4. Insertar un empleado.");
            System.out.println("5. Ejecutar un script.");
            System.out.println("6. Ejecutar un procedimiento de la base de datos.");
            System.out.println("7. Crear un informe sobre departamentos");
            System.out.println("8. Obtener las columnas de la tabla departamentos");
            System.out.println("0. Salir del programa.");
            System.out.println(" =========================== ");
            
            System.out.println("Introduce una opción: ");
            opcion = sc.nextInt();
            
            switch(opcion)
            {
                case 1:
                    try
                    {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection conexion1 = DriverManager.getConnection("jdbc:mysql://localhost/bbdd_ejemplo","root","admin");
                        Statement sentencia = conexion1.createStatement();
                        String sql = "SELECT dnombre FROM departamentos";
                        ResultSet resultado = sentencia.executeQuery(sql);
                        
                        while(resultado.next())
                        {
                            System.out.println(" Departamento: "+resultado.getString("dnombre"));
                        }
                        System.out.println("");
                        
                        resultado.close();
                        sentencia.close();
                        conexion1.close();
                    }
                    catch(ClassNotFoundException | SQLException e){ e.printStackTrace();}
                    break;
                    
                case 2:
                    try
                    {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection conexion2 = DriverManager.getConnection("jdbc:mysql://localhost/bbdd_ejemplo","root","admin");
                        String sql = "SELECT apellido FROM empleados,departamentos WHERE dept_no.empleados = dept_no.departamentos AND dept_no = ?";
                        PreparedStatement sentenciaPreparada = conexion2.prepareStatement(sql);
                        
                        System.out.println("¿Qué departamento quieres consultar?");
                        Scanner sc2 = new Scanner(System.in);
                        String depto = sc2.nextLine();
                        
                        sentenciaPreparada.setInt(1,Integer.parseInt(depto));
                        ResultSet resultado = sentenciaPreparada.executeQuery();
                        
                        while(resultado.next())
                        {
                            System.out.println(resultado);
                        }
                        System.out.println("");
                        resultado.close();
                        sentenciaPreparada.close();
                        conexion2.close();
                    }
                    catch(ClassNotFoundException | SQLException e) {e.printStackTrace();}
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 0:
                    System.out.println(" --- FIN DEL PROGRAMA --- ");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
            
        }while(opcion != 0);
    }
    
}
