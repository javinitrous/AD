/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CrearFicheroBinario;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 *
 * @author JavierPC
 */
public class CrearFicheroBinario {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        File fichero;
        Scanner sc = new Scanner(System.in);
        Scanner scFichero = new Scanner(System.in);
        String nombreFichero = null;
        int opcion = 0;
        
        if(args.length != 0)
        {
            fichero = new File(args[0]);
        }
        
        do
        {
            System.out.println("===== ESCRITURA/LECTURA DE FICHERO BINARIO =====");
            System.out.println("Escoge la opción que necesites:");
            System.out.println("1. Escribir un fichero binario.");
            System.out.println("2. Leer un fichero binario.");
            System.out.println("0. Salir del programa.");
            opcion = sc.nextInt();
            
            switch(opcion)
            {
                case 1:
                {
                    if(args.length == 0)
                    {
                        System.out.println("Introduce el nombre del fichero que vamos a escribir: ");
                        nombreFichero = scFichero.nextLine();
                    }
                    fichero = new File(nombreFichero);
                    EscribirFicheroBineario(fichero);
                    break;
                }
                case 2:
                {
                    if(args.length == 0)
                    {
                        System.out.println("Introduce el nombre del fichero que vamos a leer: ");
                        nombreFichero = scFichero.nextLine();
                    }
                    fichero = new File(nombreFichero);
                    LeerFicheroBinario(fichero);
                }
                case 0:
                {
                    System.out.println(" -- FIN DEL PROGRAMA -- ");
                    break;
                }
            }
        }while(opcion != 0);
    }

    public static void EscribirFicheroBineario(File fichero) throws Exception
    {
        FileOutputStream fos = new FileOutputStream(fichero);
        DataOutputStream dos = new DataOutputStream(fos);
        
        String nombre[] = {"María","Pepita","Sofía","Penélope","Asunción"};
        int edad[] = {21,56,35,42,49};
        
        for(int i = 0; i<nombre.length; i++)
        {
            dos.writeUTF(nombre[i]);
            dos.writeInt(edad[i]);
        }
        dos.close();
        fos.close();
    }
    
    public static void LeerFicheroBinario(File fichero) throws Exception
    {
        RandomAccessFile raf = new RandomAccessFile(fichero,"r");
        
        int posicion = 0;                   // la posición que le vamos a indicar para ir leyendo el fichero
        int id;                             // el id para cada entrada del fichero
        char nombre[] = new char[10];        // el nombre de cada persona hecho con caracteres
        int edad;                           // la edad
        
        while(posicion != raf.length())
        {
            raf.seek(posicion);
            id = raf.readInt();
            
            for(int i = 0; i<nombre.length; i++)
            {
                nombre[i] = raf.readChar();
            }
            String nombre2 = new String(nombre);
            
            edad = raf.readInt();
            
            System.out.println("Nombre: "+nombre2+". Edad: "+edad);
            posicion = posicion + 16;
        }
        raf.close();
    }
}
