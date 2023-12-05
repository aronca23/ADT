import clases.Curso;
import gestor.Gestor;

import java.util.*;

public class Main {
    static Scanner sc=new Scanner(System.in);

    public static void main(String[] args) {
        Gestor gestor=new Gestor();
        int menu=elegitOpcion();
        while (menu!=0){
            switch (menu){
                case 1 -> {
                    System.out.println("Introduce el id del tutor que quieres ver");
                    int id=sc.nextInt();
                    Set<String>m1=gestor.tutorCurso(id);
                    for (String m:m1) {
                        System.out.println(m);
                    }
                }
                case 2 -> {
                    Set<String> m2=gestor.cursosSinTutor();
                    for (String m:m2) {
                        System.out.println(m);
                    }
                }
                case 3 -> {
                    Set<String> m3=gestor.cursosSinAlumnos();
                    for (String m:m3) {
                        System.out.println(m);
                    }
                }
                case 4 -> {
                    Set<String>m4=gestor.profesoresNoTutores();
                    for (String m:m4) {
                        System.out.println(m);
                    }
                }
                case 5 -> {
                    Map<Set<String>,Integer>m5=gestor.numeroAlumnosEnCursos();
                    Set<Set<String>> claves=m5.keySet();
                    Iterator<Set<String>> it=claves.iterator();
                    while (it.hasNext()){
                        Set<String> clave=it.next();
                        int valor=m5.get(clave);
                        System.out.println(clave+" - "+valor);
                    }
                }
                case 6 -> {
                    System.out.println("Introduce un número de alumnos");
                    int num=sc.nextInt();
                    Set<String>m6=gestor.cursosConMenosAlumnos(num);
                    for (String m:m6) {
                        System.out.println(m);
                    }
                }
                case 7 -> {
                    System.out.println("Introduce un id");
                    int id=sc.nextInt();
                    Set<String> m7=gestor.alumnosMismoCurso(id);
                    for (String m:m7) {
                        System.out.println(m);
                    }
                }
                case 8 -> {
                    Set<String> m8=gestor.cursoMasAlumnos();
                    for (String m:m8) {
                        System.out.println(m);
                    }
                }
                case 9 -> {
                    System.out.println("Introduce un límite");
                    int limite=sc.nextInt();
                    Set<Curso> m9=gestor.alumnosConMayorNotaMedia(limite);
                    for (Curso m:m9) {
                        System.out.println(m.toString());
                    }
                }
            }
            menu=elegitOpcion();
        }
    }
    public static int elegitOpcion(){
        int opcion=0;
        do {
            System.out.println("Escoge una opcion:\n1. Tutor de un curso\n2. Cursos sin tutor\n3. Cursos sin alumnos\n4. Profesores no tutores\n5. Nº de alumnos en cada curso\n6. Cursos con menos alumnos que\n7. Alumnos del mismo curso que\n8. Curso que tiene más alumnos\n9. Alumnos con mayor nota media\n0. Salir");
            opcion=sc.nextInt();
        }while (opcion<0||opcion>9);
        return opcion;
    }
}
