package org.example;

import org.example.model.CCAA;
import org.example.model.Capital;
import org.example.repository.impl.CCAARepoImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
        int menu=elegirOpcion();
        CCAARepoImpl comunidadAutonomaRepositori=new CCAARepoImpl();
        while (menu!=0){
            switch (menu){
                case 1 -> {
                    System.out.println("Introduce un codigo");
                    String codigo=sc.next();
                    System.out.println("Introduce un nombre");
                    sc.nextLine();
                    String nombre=sc.nextLine();
                    System.out.println("Introduce la abreviatura");
                    String abreviatura=sc.next();
                    System.out.println("Introduce el nº de habitantes");
                    int habitantes=sc.nextInt();
                    System.out.println("Introduce la extensión");
                    int extension=sc.nextInt();
                    List<String> provincias=new ArrayList<>();
                    System.out.println("Introduce el nombre de la capital");
                    sc.nextLine();
                    String nombreCapital=sc.nextLine();
                    System.out.println("Introduce los habitantes de la capital");
                    int habitantesCapital=sc.nextInt();
                    Capital capital=new Capital(nombreCapital,habitantesCapital);
                    CCAA comunidadAutonoma=new CCAA(codigo,nombre,abreviatura,capital,habitantes,extension,provincias);
                    comunidadAutonomaRepositori.insertar(comunidadAutonoma);
                }
                case 2 -> {
                    System.out.println("Introduce un codigo");
                    String codigo=sc.next();
                    CCAA comunidadAutonoma=comunidadAutonomaRepositori.getComunidadAutonoma(codigo);
                    if (comunidadAutonoma.getCapital()!=null) System.out.println("Nombre: "+comunidadAutonoma.getNombre()+", habitantes: "+comunidadAutonoma.getHabitantes()+", extensión: "+comunidadAutonoma.getExtension()+", nombre de la capital: "+comunidadAutonoma.getCapital().getNombre());
                    else System.out.println("Nombre: "+comunidadAutonoma.getNombre()+", habitantes: "+comunidadAutonoma.getHabitantes()+", extensión: "+comunidadAutonoma.getExtension()+", nombre de la capital: ");
                }
                case 3 -> {
                    List<String> provincias=new ArrayList<>();
                    System.out.println("Introduce un codigo");
                    String codigo=sc.next();
                    CCAA comunidadAutonoma=comunidadAutonomaRepositori.getComunidadAutonoma(codigo);
                    if (comunidadAutonoma!=null){
                        System.out.println("Deséa introducir una provincia:\n1. Sí\n2. No");
                        int opcion= sc.nextInt();
                        while (opcion!=2){
                            System.out.println("Introduce el nombre de una provincia");
                            sc.nextLine();
                            String provincia=sc.nextLine();
                            provincias.add(provincia);
                            System.out.println("Deséa introducir una provincia:\n1. Sí\n2. No");
                            opcion= sc.nextInt();
                        }
                        Long cont= comunidadAutonomaRepositori.addProvinciasComunidad(codigo,provincias);
                        System.out.println(cont);
                    }
                }
                case 4 -> {
                    System.out.println("Introduce un codigo");
                    String codigo=sc.next();
                    CCAA comunidadAutonoma=comunidadAutonomaRepositori.getComunidadAutonoma(codigo);
                    if (comunidadAutonoma!=null){
                        System.out.println("Introduce el nombre de una provincia");
                        sc.nextLine();
                        String provincia=sc.nextLine();
                        Long cont= comunidadAutonomaRepositori.addProvinciaComunidad(codigo,provincia);
                        System.out.println(cont);
                    }
                }
                case 5 -> {
                    System.out.println("Introduce un codigo");
                    String codigo=sc.next();
                    CCAA comunidadAutonoma=comunidadAutonomaRepositori.getComunidadAutonoma(codigo);
                    if (comunidadAutonoma!=null){
                        System.out.println("Introduce un nombre");
                        sc.nextLine();
                        String nombre=sc.nextLine();
                        comunidadAutonomaRepositori.actualizarNombre(codigo,nombre);
                    }
                }
                case 6 -> {
                    System.out.println("Introduce un codigo");
                    String codigo=sc.next();
                    CCAA comunidadAutonoma=comunidadAutonomaRepositori.getComunidadAutonoma(codigo);
                    if (comunidadAutonoma!=null){
                        System.out.println("Introduce el nombre de una provincia");
                        sc.nextLine();
                        String provincia=sc.nextLine();
                        Long cont= comunidadAutonomaRepositori.eliminarProvinciaComunidad(codigo,provincia);
                        System.out.println(cont);
                    }
                }
                case 7 -> {
                    System.out.println("Introduce un codigo");
                    String codigo=sc.next();
                    CCAA comunidadAutonoma=comunidadAutonomaRepositori.getComunidadAutonoma(codigo);
                    if (comunidadAutonoma!=null&&comunidadAutonoma.getCapital()==null){
                        System.out.println("Introduce el nombre de la capital");
                        sc.nextLine();
                        String nombreCapital=sc.nextLine();
                        System.out.println("Introduce los habitantes de la capital");
                        int habitantesCapital=sc.nextInt();
                        comunidadAutonomaRepositori.asignarCapital(codigo,nombreCapital,habitantesCapital);
                    }
                }
                case 8 -> {
                    System.out.println("Introduce un codigo");
                    String codigo=sc.next();
                    CCAA comunidadAutonoma=comunidadAutonomaRepositori.getComunidadAutonoma(codigo);
                    if (comunidadAutonoma!=null){
                        System.out.println("Introduce el año");
                        int year= sc.nextInt();
                        System.out.println("Inntroduce el mes");
                        int month= sc.nextInt();
                        System.out.println("Introduce el día");
                        int day=sc.nextInt();
                        LocalDate fecha=LocalDate.of(year,month,day);
                        String fechaEstatuto=fecha.toString();
                        comunidadAutonomaRepositori.asignarFechaEstatuto(codigo,fechaEstatuto);
                    }
                }
                case 9 -> {
                    System.out.println("Introduce un codigo");
                    String codigo=sc.next();
                    CCAA comunidadAutonoma=comunidadAutonomaRepositori.getComunidadAutonoma(codigo);
                    if (comunidadAutonoma!=null){
                        System.out.println(comunidadAutonoma.fromComunidadAutonomaToDocument(comunidadAutonoma));
                        System.out.println("Deséa eliminarla:\n1. Sí\n2. No");
                        int opcion= sc.nextInt();
                        if (opcion==1) comunidadAutonomaRepositori.eliminar(codigo);
                    }
                }
            }
            menu=elegirOpcion();
        }
    }
    private static int elegirOpcion(){
        int opcion=0;
        do {
            System.out.println("Elige una opción:\n1. Añadir CCAA sin provincias\n2. Consultar CCAA\n3. Asignar provincias a CCAA\n4. Añadir provincia a CCAA\n5. Modificar nombre CCAA\n6. Eliminar provincia en CCAA\n7. Asignar capital a CCAA\n8. Asignar fecha Estatuto Autonomía\n9. Eliminar CCAA");
            opcion=sc.nextInt();
        }while (opcion<0||opcion>9);
        return opcion;
    }
    }
}