package es.aron;

import es.aron.model.Alumno;
import es.aron.model.Curso;
import es.aron.model.Profesor;
import es.aron.repository.impl.AlumnoRepoImpl;
import es.aron.repository.impl.CursoRepoImpl;
import es.aron.repository.impl.ProfesorRepoImpl;
import org.checkerframework.checker.units.qual.C;

import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
        CursoRepoImpl cursoRepo=new CursoRepoImpl();
        AlumnoRepoImpl alumnoRepo=new AlumnoRepoImpl();
        ProfesorRepoImpl profesorRepo=new ProfesorRepoImpl();
        int menu=menu();
        while (menu!=0){
            switch (menu){
                case 1 -> {
                    System.out.println("Introduce el id del alumno");
                    int id=sc.nextInt();
                    Alumno alumno=alumnoRepo.datosAlumno(id);
                    System.out.println(alumno.getNombre()+" - "+alumno.getCurso().getId());
                }
                case 2 -> {
                    System.out.println("Introduce el id del curso");
                    String id=sc.next();
                    Curso curso= cursoRepo.datosCurso(id);
                    System.out.println(curso.getNombre()+" - "+curso.getTutor().getNombre());
                }
                case 3 -> {
                    List<Curso> cursos=cursoRepo.listadoCursos();
                    for (Curso curso:cursos) System.out.println(curso.toString());
                }
                case 4 -> {
                    System.out.println("Introduce el id del curso");
                    String id=sc.next();
                    Curso curso= cursoRepo.datosCurso(id);
                    if (curso!=null){
                        List<Alumno> alumnos=alumnoRepo.listadoAlumnosCurso(id);
                        for (Alumno alumno:alumnos) {
                            System.out.println(alumno.toString());
                        }
                    }
                }
                case 5 -> {
                    System.out.println("Introduce el id del curso");
                    String id=sc.next();
                    Curso curso= cursoRepo.datosCurso(id);
                    if (curso!=null){
                        System.out.println(curso.getNombre());
                        System.out.println("Introduce un nuevo titulo");
                        sc.nextLine();
                        String titulo=sc.nextLine();
                        Curso c=new Curso(curso.getId(),titulo);
                        cursoRepo.modifarTituloCurso(c);
                    }
                }
                case 6 -> {
                    System.out.println("Introduce el id del curso");
                    String id=sc.next();
                    Curso curso= cursoRepo.datosCurso(id);
                    if (curso==null){
                        System.out.println("Introduce el id");
                        String identificador=sc.next();
                        System.out.println("Introduce el título");
                        sc.nextLine();
                        String titulo=sc.nextLine();
                        Curso c=new Curso(id,titulo);
                        cursoRepo.addCurso(c);
                    }
                }
                case 7 -> {
                    System.out.println("Introduce el id");
                    String identificador=sc.next();
                    System.out.println("Introduce el título");
                    sc.nextLine();
                    String titulo=sc.nextLine();
                    Curso curso=cursoRepo.getCurso(identificador,titulo);
                    if (curso!=null){
                        System.out.println("Introduce un nuevo título");
                        String titulo2=sc.nextLine();
                        Curso c=new Curso(identificador,titulo2);
                        cursoRepo.addModificarCurso(identificador,titulo,c);
                    } else {
                        Curso c=new Curso(identificador,titulo);
                        cursoRepo.addModificarCurso(identificador,titulo,c);
                    }
                }
                case 8 -> {
                    System.out.println("Introduce el id del curso");
                    String id=sc.next();
                    Curso curso= cursoRepo.datosCurso(id);
                    if (curso!=null){
                        System.out.println(curso.getNombre());
                        System.out.println("Introduce el id del tutor");
                        int identificador=sc.nextInt();
                        Profesor profesor= profesorRepo.getProfesor(identificador);
                        Curso c=new Curso(curso.getId(), curso.getNombre(), profesor);
                    }
                }
                case 9 -> {
                    System.out.println("Introduce el id del alumno");
                    int id=sc.nextInt();
                    alumnoRepo.eliminarAlumno(id);
                }
            }
            menu=menu();
        }
    }
    public static int menu(){
        int opcion=0;
        do {
            System.out.println("Introduce una opción:\n1. Datos de alumno\n2. Datos de curso\n3. Listado de cursos\n4. Listado de alumnos de curso\n5. Modificar título curso\n6. Añadir curso\n7. Añadir o modificar curso\n8. Modificar tutor curso\n9. Eliminar alumno");
            opcion= sc.nextInt();
        }while (opcion<0||opcion>9);
        return opcion;
    }
}
