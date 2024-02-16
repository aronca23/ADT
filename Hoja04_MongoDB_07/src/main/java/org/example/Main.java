package org.example;

import org.example.modelo.Alumno;
import org.example.modelo.Curso;
import org.example.modelo.Tema;
import org.example.repository.impl.AlumnoRepoImpl;
import org.example.repository.impl.CursoRepoImpl;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static Scanner tec= new Scanner(System.in);
    public static void main(String[] args) {
        menu();
        int opcion= tec.nextInt();;
        tec.nextLine();
        do{
            accion(opcion);
            menu();
            opcion= tec.nextInt();
            tec.nextLine();
        }while(opcion!=0);


    }
    public static void menu(){
        System.out.println("1.- Añadir notas a alumnos de curso\n" +
                "2.- Añadir tema a curso\n" +
                "3.- Obtener alumnos de curso\n" +
                "4.- Número de alumnos por curso\n" +
                "5.- Modificar nota media de alumno\n" +
                "6.- Modificar horas de curso\n" +
                "7.- Datos de alumno \n" +
                "8.- Nota media en curso\n" +
                "0.- Salir\n"+
                "Introduzca una opción");


    }

    public static void accion(int opcion){
        AlumnoRepoImpl alumnoRepo= new AlumnoRepoImpl();
        CursoRepoImpl cursoRepo= new CursoRepoImpl();

        switch (opcion){
            case 1: {
                System.out.println("Código");
                String codigo= tec.nextLine();
                Iterator<Alumno> it= alumnoRepo.getAlumnosFromCurso(codigo).iterator();
                if(it.hasNext()){
                    Map<String, Double> datos= new LinkedHashMap<>();
                    while(it.hasNext()){
                        Alumno a= it.next();
                        System.out.println("Introduce la nota a asignar para el alumno " +a.getNombre() +" "+ a.getApellidos()+ " de id "+a.getId());
                        double nota= tec.nextDouble();
                        datos.put(a.getId(), nota);
                    }
                    alumnoRepo.introducirNotasAlumnos(datos);}
                else System.out.println("No existe el curso introducido");
                break;
            }

            case 2:{
                System.out.println("Código");
                String codigo= tec.nextLine();
                Curso c=cursoRepo.getCursoById(codigo);
                if(c!=null){
                    System.out.println("Titulo del tema");
                    String titulo= tec.nextLine();
                    System.out.println("Horas del tema");
                    int horas = tec.nextInt();
                    cursoRepo.introducirTemaEnCurso(codigo, new Tema(titulo, horas));
                }
                else System.out.println("No existe el curso con el código introducido");
                break;
            }
            case 3:{
                System.out.println("Código");
                String codigo= tec.nextLine();
                Curso c=cursoRepo.getCursoById(codigo);
                if(c!=null){
                    alumnoRepo.alumnosAprobadosDeCurso(c.getId()).forEach(f-> System.out.println("Nombre y apellidos: "+f.getNombre()+" "+f.getApellidos()+" Nota media "+f.getNota_media()));
                }
                else System.out.println("No existe el curso con el código introducido");
                break;
            }

            case 4:{
                cursoRepo.getNombresCursos().forEach(System.out::println);
                break;
            }

            case 5:{
                System.out.println("Id de alumno");
                String codigo= tec.nextLine();
                Alumno alumno= alumnoRepo.getAlumnoById(codigo);
                if(alumno!=null){
                    //falla al intentar castear a double cuando lo coge de la colección, dijo que eso no entraba en el examen así que chill
                }
                else System.out.println("El id introducido no corresponde a ningún alumno");
                break;
            }

            case 6:{
                System.out.println("Código");
                String codigo= tec.nextLine();
                Curso c=cursoRepo.getCursoById(codigo);
                int horas;
                if(c!=null){
                    horas = c.getTemas().stream().mapToInt(Tema::getHoras).sum();
                    cursoRepo.actualizarHorasDeCurso(c.getId(), horas);
                }
                else System.out.println("El id introducido no pertenece a ningún curso");
                break;
            }

            case 7:{
                System.out.println("Id de alumno");
                String codigo= tec.nextLine();
                Alumno alumno= alumnoRepo.getAlumnoById(codigo);
                if(alumno!=null){
                    String tituloCurso= cursoRepo.getCursoById(alumno.getCurso()).getTitulo();
                    System.out.println("El alumno: "+alumno.getNombre()+" "+alumno.getApellidos()+" Está matriculado en: "+tituloCurso);
                }
                else System.out.println("El id introducido no corresponde a ningún alumno");
                break;
            }

            case 8:{
                System.out.println("Código");
                String codigo= tec.nextLine();
                Curso c=cursoRepo.getCursoById(codigo);
                if(c!=null){
                    System.out.printf("Nota media: %f \n" , cursoRepo.getMediaCurso(codigo));


                }
                else System.out.println("El id introducido no pertenece a ningún curso");
                break;
            }

        }
    }
}