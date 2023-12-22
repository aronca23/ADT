import clases.Grupo;
import clases.Voto;
import gestor.Gestor;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Main {
    static Scanner sc=new Scanner(System.in);

    public static void main(String[] args) {
        Gestor gestor=new Gestor();
        int menu=elegirOpcion();
        while (menu!=0){
            switch (menu){
                case 1 -> {
                    System.out.println("Introduce el id que quieres mostrar");
                    int id1=sc.nextInt();
                    System.out.println("Introduce el nuevo nombre");
                    sc.nextLine();
                    String nombre=sc.nextLine();
                    System.out.println("Introduce la nueva localidad");
                    String localidad=sc.nextLine();
                    System.out.println("Introduce el nuevo estilo");
                    String estilo=sc.nextLine();
                    System.out.println("Introduce si es grupo o no");
                    String esgrupo= sc.nextLine();
                    System.out.println("Introduce el nuevo año de grabación");
                    int añoGrabacion=sc.nextInt();
                    System.out.println("Introduce el año");
                    int año=sc.nextInt();
                    System.out.println("Introduce el mes");
                    int mes=sc.nextInt();
                    System.out.println("Introduce el dia");
                    int dia=sc.nextInt();
                    System.out.println("Introduce la compañía");
                    sc.nextLine();
                    String compañia=sc.nextLine();
                    System.out.println("Desea actualizar");
                    String preguntar=sc.nextLine();
                    Set<Grupo> ejercicio1=gestor.ejercicio1(id1,preguntar,nombre,localidad,estilo,esgrupo,añoGrabacion,año,mes,dia,compañia);
                    Iterator<Grupo> it=ejercicio1.iterator();
                    while (it.hasNext()){
                        Grupo grupo=it.next();
                        System.out.println(grupo);
                    }
                }
                case 2 -> {
                    System.out.println("Introduce la opcion");
                    int opcion=sc.nextInt();
                    System.out.println("Introduce el usuario");
                    sc.nextLine();
                    String usuario=sc.nextLine();
                    System.out.println("Introduce el año");
                    int año=sc.nextInt();
                    System.out.println("Introduce el mes");
                    int mes=sc.nextInt();
                    System.out.println("Introduce el dia");
                    int dia=sc.nextInt();
                    System.out.println("Introduce el id de la cancion");
                    int cancion=sc.nextInt();
                    Set<Voto> ejercicio2=gestor.ejercicio2(opcion,usuario,año,mes,dia,cancion);
                    Iterator<Voto> it=ejercicio2.iterator();
                    while (it.hasNext()){
                        Voto voto=it.next();
                        System.out.println(voto);
                    }
                }
                case 3 -> {
                    System.out.println("Desea updatear?");
                    String preguntar=sc.nextLine();
                    System.out.println("Introduce el usuario");
                    sc.nextLine();
                    String usuario=sc.nextLine();
                    System.out.println("Introduce el año");
                    int año=sc.nextInt();
                    System.out.println("Introduce el mes");
                    int mes=sc.nextInt();
                    System.out.println("Introduce el dia");
                    int dia=sc.nextInt();
                    Set<Voto> ejercicio3=gestor.ejercicio3(usuario,preguntar,año,mes,dia);
                    Iterator<Voto> it=ejercicio3.iterator();
                    while (it.hasNext()){
                        Voto voto=it.next();
                        System.out.println(voto);
                    }
                }
            }
            menu=elegirOpcion();
        }
    }
    public static int elegirOpcion(){
        int opcion=0;
        do {
            System.out.println("Elige el ejercicio que quieres hacer:\n1. Ejercicio 1\n2. Ejercicio 2\n3. Ejercicio 3");
            opcion=sc.nextInt();
        }while (opcion<0||opcion>3);
        return opcion;
    }
}
