import gestor.Gestor;

import java.util.Scanner;

public class Main {
    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
        Gestor gestor=new Gestor();
        System.out.println("Introduce el número de canción");
        int numcancion=sc.nextInt();
        while (numcancion!=0){
            gestor.incrementarVotos(numcancion);
            System.out.println("Introduce el número de canción");
            numcancion=sc.nextInt();
        }
    }
}
