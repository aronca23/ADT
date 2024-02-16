package org.example;

import org.example.modelo.Capital;
import org.example.modelo.ComunidadAutonoma;
import org.example.repository.impl.CCAARepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
        CCAARepositoryImpl repository=new CCAARepositoryImpl();
        ComunidadAutonoma comunidadAutonoma=new ComunidadAutonoma();
        Capital capital=new Capital();
        int menu=elegirOpcion();
        while (menu!=0){
            switch (menu){
                case 1 -> {
                    System.out.println("Introduce el código");
                    String codigo=sc.next();
                    System.out.println("Introduce el nombre de la provincia");
                    sc.nextLine();
                    String nombre=sc.nextLine();
                    System.out.println("Introduce la abreviatura de la provincia");
                    String abreviatura=sc.next();
                    System.out.println("Introduce el nombre de la capital");
                    sc.nextLine();
                    String nombreCapital=sc.nextLine();
                    System.out.println("Introduce los habitantes de la capital");
                    int habitantesCapital= sc.nextInt();
                    System.out.println("Introduce los habitantes de la provincia");
                    int habitantes=sc.nextInt();
                    System.out.println("Introduce la extensión de la provincia");
                    int extension=sc.nextInt();
                    comunidadAutonoma.setCodigo(codigo);
                    comunidadAutonoma.setNombre(nombre);
                    comunidadAutonoma.setAbreviatura(abreviatura);
                    capital.setNombre(nombreCapital);
                    capital.setHabitantes(habitantesCapital);
                    comunidadAutonoma.setCapital(capital);
                    comunidadAutonoma.setHabitantes(habitantes);
                    comunidadAutonoma.setExtension(extension);
                    repository.insertar(comunidadAutonoma);
                }
                case 2 -> {
                    System.out.println("Introduce el código");
                    String codigo=sc.next();
                    comunidadAutonoma=repository.getComunidadAutonoma(codigo);
                    if (comunidadAutonoma!=null)
                        System.out.println(comunidadAutonoma.fromComunidadAutonomaToDocument(comunidadAutonoma).toJson());
                }
                case 3 -> {
                    List<String> provincias=new ArrayList<>();
                    System.out.println("Introduce el código");
                    String codigo=sc.next();
                    comunidadAutonoma=repository.getComunidadAutonoma(codigo);
                    if (comunidadAutonoma!=null) {
                        System.out.println("¿Desea introducir un nombre?\n1. Sí\n2. No");
                        int respuesta= sc.nextInt();
                        while (respuesta==1){
                            System.out.println("Introduce el nombre de la provincia");
                            sc.nextLine();
                            String nombre = sc.nextLine();
                            provincias.add(nombre);
                            System.out.println("¿Desea introducir un nombre?\n1. Sí\n2. No");
                            respuesta= sc.nextInt();
                        }
                        Long comprobar= repository.addProvinciaComunidad(codigo,provincias);
                        System.out.println(comprobar);
                    }
                }
                case 4 -> {
                    System.out.println("Introduce el código");
                    String codigo=sc.next();
                    comunidadAutonoma=repository.getComunidadAutonoma(codigo);
                    if (comunidadAutonoma!=null) {
                        System.out.println("Introduce el nombre de la provincia");
                        sc.nextLine();
                        String nombre = sc.nextLine();
                        Long comprobar= repository.addProvinciaComunidad(codigo,nombre);
                        System.out.println(comprobar);
                    }
                }
                case 5 -> {
                    System.out.println("Introduce el código");
                    String codigo=sc.next();
                    comunidadAutonoma=repository.getComunidadAutonoma(codigo);
                    if (comunidadAutonoma!=null) {
                        System.out.println("Introduce un nuevo nombre de la provincia");
                        sc.nextLine();
                        String nombre = sc.nextLine();
                        repository.actualizarNombre(codigo,nombre);
                    }
                }
                case 6 -> {
                    System.out.println("Introduce el código");
                    String codigo=sc.next();
                    comunidadAutonoma=repository.getComunidadAutonoma(codigo);
                    if (comunidadAutonoma!=null) {
                        System.out.println("Introduce un nuevo nombre de la provincia");
                        sc.nextLine();
                        String nombre = sc.nextLine();
                        Long comprobar=repository.eliminarProvinciasComunidad(codigo,nombre);
                        System.out.println(comprobar);
                    }
                }
                case 7 -> {
                    System.out.println("Introduce el código");
                    String codigo=sc.next();
                    comunidadAutonoma=repository.getComunidadAutonoma(codigo);
                    if (comunidadAutonoma!=null) {
                        System.out.println("Introduce un nuevo nombre de la capital");
                        sc.nextLine();
                        String nombre = sc.nextLine();
                        System.out.println("Introduce los habitantes de la capital");
                        int habitantesCapital= sc.nextInt();
                        repository.asignarCapital(codigo,nombre,habitantesCapital);
                    }
                }
                case 8 -> {
                    System.out.println("Introduce el código");
                    String codigo=sc.next();
                    comunidadAutonoma=repository.getComunidadAutonoma(codigo);
                    if (comunidadAutonoma!=null) {
                        System.out.println("Introduce la fecha");
                        String fecha= sc.next();
                        repository.asignarFechaEstatuto(codigo,fecha);
                    }
                }
                case 9 -> {
                    System.out.println("Introduce el código");
                    String codigo=sc.next();
                    comunidadAutonoma=repository.getComunidadAutonoma(codigo);
                    if (comunidadAutonoma!=null) {
                        repository.eliminar(codigo);
                    }
                }
            }
            menu=elegirOpcion();
        }
    }
    public static int elegirOpcion(){
        int opcion=0;
        do {
            System.out.println("Introduce una opción:\n1.Añadir CCAA sin provincias\n2. Consultar CCAA\n3. Asignar provincias a CCAA\n4. Añadir provincia a CCAA\n5. Modificar nombre CCAA\n6. Eliminar provincia CCAA\n7. Asignar capital CCAA\n8. Asignar fecha estatuto\n9. Eliminar CCAA");
            opcion= sc.nextInt();
        }while (opcion<0||opcion>9);
        return opcion;
    }
}