import gestor.Gestor;

import java.util.Scanner;

public class Main {
    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
        Gestor gestor=new Gestor();
        System.out.println("Introduce el número de grupo");
        int numgrupo=sc.nextInt();
        sc.nextLine();
        System.out.println("Introduce el título de la canción");
        String titulo=sc.nextLine();
        while (!titulo.equals("")){
            System.out.println("Introduce el número de la canción");
            int numcancion=sc.nextInt();
            System.out.println("Introduce la duración de la canción");
            int duracion=sc.nextInt();
            gestor.insertarCanciones(numgrupo,numcancion,titulo,duracion);
            sc.nextLine();
            System.out.println("Introduce el título de la canción");
            titulo=sc.nextLine();
        }
    }
}
