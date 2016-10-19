/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FicherosAleatorios;

import java.util.*;
import java.io.*;


/**
 *
 * @author JavierPC
 */
public class Ejercicio1FicherosAleatorios {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception
    {
        // TODO code application logic here
        // creamos un scanner para leer de teclado el nombre del fichero
        File fichero = null;
        Scanner sc = new Scanner(System.in);
        
        // Pedimos el nombre del fichero en caso de que no lo tengamos
        if(args.length == 1)
        {
            fichero = new File(args[0]);
        }
        else
        {
            System.out.println("Escribe el nombre para el fichero: ");
            fichero = new File(sc.nextLine());
        }
        
        // creamos un switch para el menú del programa
        int opcion;
        
        do
        {
            System.out.println("==== MENÚ DEL PROGRAMA ====");
            System.out.println("Elige la opción que necesites");
            System.out.println("1. Escribir un fichero");
            System.out.println("2. Leer un fichero");
            System.out.println("3. Leer un solo empleado");
            System.out.println("4. Introducir un empleado");
            System.out.println("5. Modificar un empleado");
            
            opcion = sc.nextInt(); // leemos de teclado la opción elegida y la asignamos
            
            switch(opcion)
            {
                case 1: EscrituraFicheroAleatorio(fichero); break;
                case 2: LecturaFicheroAleatorio(fichero);   break;
                case 3: LecturaUnEmpleado(fichero);         break;
                case 4: IntroducirEmpleado(fichero);        break;
                case 5: ModificarEmpleado(fichero);         break;
                
                default: opcion = 0;                        break;
            }
        }while(opcion != 0);
        
    }
    
    static void EscrituraFicheroAleatorio(File fichero) throws Exception
    {
        RandomAccessFile raf = new RandomAccessFile(fichero,"rw");
        
        String apellido[] = {"Garcia","Hernandez","Cifuentes","Lorca","Martinez"};
        int departamento[] = {1,2,3,4,5};
        double salario[] = {1200.0,2300.5,1000.0,679.0,900.5};
        
        StringBuffer buffer = null;
        
        //ahora escribimos el fichero
        for(int i=0; i<apellido.length; i++)
        {
            // creamos y damos tamaño al buffer
            raf.writeInt(i+1);
            buffer = new StringBuffer(apellido[i]);
            buffer.setLength(10);
            
            // escribimos el fichero con los parámetros
            raf.writeChars(buffer.toString());
            raf.writeInt(departamento[i]);
            raf.writeDouble(salario[i]);
        }
        raf.close(); //cerramos el randomaccessfile
    }
    
    static void LecturaFicheroAleatorio(File fichero) throws Exception
    {
        RandomAccessFile raf = new RandomAccessFile(fichero,"r");
        
        // vamos a necesitar un id para posicionarnos en el fichero
        int posicion, id, depart;
        char apellido[] = new char[10];
        double salario;
        
        posicion = 0;
        
        while(posicion != raf.length())
        {
            raf.seek(posicion);
            id = raf.readInt();
            
            for(int i=0; i<apellido.length; i++)
            {
                apellido[i] = raf.readChar();
            }
            String apeEmple = new String(apellido);
            depart = raf.readInt();
            salario = raf.readDouble();
            
            System.out.println("Id: "+id+" Apellido: "+apeEmple+" Departamento: "+depart+" Salario: "+salario);
            posicion = posicion + 36;
        }
        raf.close(); //cerramos el RandomAccessFile
    }
    
    static void LecturaUnEmpleado(File fichero) throws Exception
    {
        RandomAccessFile raf = new RandomAccessFile(fichero,"r");
        Scanner sc = new Scanner(System.in);
        
        int id, idNuevo, depart;
        double salario;
        char apellido[] = new char[10];
        
        System.out.println("Dime el ID del empleado: ");
        id = sc.nextInt();
        
        raf.seek((id-1)*36); // nos posicionamos en el id del empleado
        
        idNuevo = raf.readInt();
        for(int i=0; i<apellido.length; i++)
        {
            apellido[i] = raf.readChar();
        }
        String apeEmple = new String(apellido);
        depart = raf.readInt();
        salario = raf.readDouble();
        
        System.out.println("El ID introducido es "+id+" cuyos datos son "+idNuevo+", apellido "+apeEmple+" y salario "+salario);
        raf.close();
    }
    
    static void IntroducirEmpleado(File fichero) throws Exception
    {
        RandomAccessFile raf = new RandomAccessFile(fichero,"rw");
        Scanner sc = new Scanner(System.in);
        
        String apellido = "";
        int id = 0, depart = 0;
        double salario = 0;
        
        StringBuffer buffer = null; //creamos un buffer que más tarde usaremos para el apellido
        
        // Pedimos y asignamos
        System.out.println("Introduce el apellido del empleado: ");
        apellido = sc.nextLine();
        System.out.println("Introduce el departamento del empleado: ");
        depart = sc.nextInt();
        System.out.println("Introduce el salario del empleado: ");
        salario = sc.nextDouble();
        
        id = (int)((raf.length()/36)+1);
        raf.seek(raf.length());
        
        raf.writeInt(id);
        buffer = new StringBuffer(apellido);    // el buffer contiene el apellido
        buffer.setLength(10); // y tiene de tamaño 10 caracteres
        raf.writeChars(buffer.toString());
        raf.writeInt(depart);
        raf.writeDouble(salario);
        
        raf.close();
    }
    
    static void ModificarEmpleado(File fichero) throws Exception
    {
        RandomAccessFile raf = new RandomAccessFile(fichero,"rw");
        Scanner sc = new Scanner(System.in);
        StringBuffer buffer = null;
        
        String apellido = "";
        int depart = 0, id = 0;
        double salario = 0;
        
        System.out.println("¿Deseas modificar los detalles de un empleado?");
        System.out.println("¿Cuál es su ID? ");
        id = sc.nextInt();
        sc.nextLine(); //tengo buffer de teclado. Lo mando a tomar por saco.
        System.out.println("¿Cuál es su apellido? ");
        apellido = sc.nextLine();
        System.out.println("¿Cuál es su departamento? ");
        depart = sc.nextInt();
        System.out.println("¿Cuál es su salario? ");
        salario = sc.nextDouble();
        
        raf.seek((id-1)*36); // hacemos que el cursor apunte al empleado solicitado por el usuario
              
        raf.writeInt(id);
        buffer = new StringBuffer(apellido);
        buffer.setLength(10);
        raf.writeChars(buffer.toString());
        raf.writeInt(depart);
        raf.writeDouble(salario);
               
        raf.close();
    }
    
}
