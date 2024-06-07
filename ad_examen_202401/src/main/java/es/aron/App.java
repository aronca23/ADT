package es.aron;

import es.aron.model.Comentario;
import es.aron.model.Receta;
import es.aron.repository.impl.ComentarioRepositoryImpl;
import es.aron.repository.impl.RecetaRepositoryImpl;
import es.aron.util.GestorFicheros;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class App {
    static Scanner sc=new Scanner(System.in);

    public static void main(String[] args) {
        RecetaRepositoryImpl recetaRepository=new RecetaRepositoryImpl();
        ComentarioRepositoryImpl comentarioRepository=new ComentarioRepositoryImpl();
        GestorFicheros gestorFicheros=new GestorFicheros();
        System.out.println("Desea añadir el fichero de comentarios:\n1. Sí\n2. No");
        int opcion= sc.nextInt();
        if (opcion==1){
            System.out.println("Introduce el nombre del fichero");
            String nombre=sc.next();
            File f=new File(nombre);
            List<Comentario> comentarios=gestorFicheros.leerFicheroComentario(f.toPath());
            for (Comentario comentario:comentarios) {
                boolean insertar= comentarioRepository.insertarComentario(comentario);
                System.out.println(insertar);
            }
        }
        int menu=menu();
        while (menu!=0){
            switch (menu){
                case 1 -> {
                    System.out.println("Introduce el login");
                    String login=sc.next();
                    List<Comentario> comentarios=comentarioRepository.comentarioRecetasAutor(login);
                    for (Comentario comentario:comentarios) {
                        System.out.println(comentario.getTitulo()+comentario.getReceta().getNombre()+comentario.getReceta().getAutor().getNombre());
                    }
                }
                case 2 -> {
                    List<Receta> recetas=recetaRepository.recetaMasVotosPositivos();
                    for (Receta receta:recetas) {
                        System.out.println(receta.getPositivos()+"|"+receta.getNombre()+"\t\t|"+receta.getAutor().getEmail());
                    }
                }
                 case 3 -> {
                    int cont=recetaRepository.incrementarDificultad();
                     System.out.println(cont);
                 }
                 case 4 -> {
                     System.out.println("Introduce el id de la receta");
                     int id=sc.nextInt();
                     List<String> ingredientes=recetaRepository.getNombreIngredienres(id);
                     gestorFicheros.guardarIngredientes(ingredientes,id);
                 }
            }
            menu=menu();
        }
    }
    public static int menu(){
        int opcion=0;
        do {
            System.out.println("Introduce una opción:\n1. Comentario recetas autor\n2. Receta con más votos positivos\n3. Incrementar dificultad\n4. Guardar ingredientes de receta\n0. Salir");
            opcion= sc.nextInt();
        }while (opcion<0||opcion>4);
        return opcion;
    }
}
