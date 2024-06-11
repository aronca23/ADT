package org.example;

import org.bson.Document;
import org.example.model.Alumno;
import org.example.model.Curso;
import org.example.model.Tema;
import org.example.repository.impl.AlumnoRepoImpl;
import org.example.repository.impl.CursoRepoImpl;

import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
        AlumnoRepoImpl alumnoRepo=new AlumnoRepoImpl();
        CursoRepoImpl cursoRepo=new CursoRepoImpl();
        int menu=elegirOpcion();
        while (menu!=0){
            switch (menu){
                case 1 -> {
                    System.out.println("Introduce el id de un curso");
                    String id= sc.next();
                    Curso curso=cursoRepo.getCurso(id);
                    if (curso!=null){
                        List<Alumno> alumnos=alumnoRepo.getAlumnos(id);
                        for (Alumno alumno:alumnos) {
                            System.out.println(alumno.getId()+" - "+alumno.getNombre()+" "+alumno.getApellidos());
                            System.out.println("Introduce la nota");
                            double nota= sc.nextDouble();
                            alumnoRepo.anadirNota(alumno.getId(), nota);
                        }
                    }
                }
                case 2 -> {
                    System.out.println("Introduce el id de un curso");
                    String id= sc.next();
                    Curso curso=cursoRepo.getCurso(id);
                    if (curso!=null){
                        System.out.println("Introduce el título de un tema");
                        sc.nextLine();
                        String titulo= sc.nextLine();
                        System.out.println("Introduce las horas");
                        int horas=sc.nextInt();
                        Tema tema=new Tema(titulo,horas);
                        Document document=tema.fromTemaToDocument(tema);
                        cursoRepo.anadirTemaCurso(id,document);
                    }
                }
                case 3 -> {
                    System.out.println("Introduce el id de un curso");
                    String id= sc.next();
                    Curso curso=cursoRepo.getCurso(id);
                    if (curso!=null){
                        List<Alumno> alumnos=alumnoRepo.getAlumnos(id);
                        for (Alumno alumno:alumnos) {
                            if (alumno.getNota_media()>=5){
                                System.out.println("El alumno "+alumno.getNombre()+" "+alumno.getApellidos()+" tiene una nota media de "+alumno.getNota_media());
                            }
                        }
                    }
                }
                case 4 -> {
                    System.out.println("Introduce el id de un alumno");
                    String id=sc.next();
                    Alumno alumno=alumnoRepo.getAlumno(id);
                    if (alumno!=null){

                    }
                }
                case 5 -> {
                    System.out.println("Introduce el id de un alumno");
                    String id=sc.next();
                    Alumno alumno=alumnoRepo.getAlumno(id);
                    if (alumno!=null){
                        alumnoRepo.calcularNotaMedia(id);
                    }
                }
                case 6 -> {
                    System.out.println("Introduce el id de un curso");
                    String id= sc.next();
                    Curso curso=cursoRepo.getCurso(id);
                    int horas=0;
                    if (curso!=null) {
                        if (curso.getTemas() == null) System.out.println("Horas: " + horas);
                        else {
                            for (Tema  tema:curso.getTemas()) horas+= tema.getHoras();
                            System.out.println("Horas: "+horas);
                        }
                    }
                }
                case 7 -> {
                    System.out.println("Introduce el id de un alumno");
                    String id=sc.next();
                    Alumno alumno=alumnoRepo.getAlumno(id);
                    if (alumno!=null){
                        System.out.println(alumno.getNombre()+" "+alumno.getApellidos()+" - "+alumno.getCurso().getTitulo());
                    }
                }
                case 8 -> {
                    System.out.println("Introduce el id de un curso");
                    String id= sc.next();
                    Curso curso=cursoRepo.getCurso(id);
                    if (curso!=null){
                        double sumaNotas=0;
                        int cont=0;
                        List<Alumno> alumnos=alumnoRepo.getAlumnosCurso(curso.getId());
                        for (Alumno alumno:alumnos) {
                            for (Double nota:alumno.getNotas()) {
                                cont++;
                                sumaNotas+=nota;
                            }
                        }
                        double notaMedia=sumaNotas/cont;
                        System.out.println(notaMedia);
                    }
                }
            }
            menu=elegirOpcion();
        }
    }
    private static int elegirOpcion(){
        int opcion=0;
        do {
            System.out.println("Elige una opción:\n1. Añadir notas a alumnos de curso\n2.Añadir tema a curso\n3. Obtener alumnos de curso\n4. Nº de alumnos de curso\n5. Modificar nota media de curso\n6. Modificar horas de curso\n7. Datos de alumno\n8. Nota media de curso\n9. Salir");
            opcion=sc.nextInt();
        }while (opcion<0||opcion>9);
        return opcion;
    }
}