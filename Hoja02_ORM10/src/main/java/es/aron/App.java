package es.aron;

import es.aron.repository.impl.CancionRepoImpl;
import es.aron.repository.impl.GrupoRepoImpl;
import es.aron.model.Cancione;
import es.aron.model.Grupo;
import es.aron.model.Usuario;
import es.aron.repository.impl.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    static Scanner sc=new Scanner(System.in);

    public static void main(String[] args) {
        GrupoRepoImpl grupoRepository=new GrupoRepoImpl();
        CancionRepoImpl cancionRepository=new CancionRepoImpl();
        UsuarioRepoImpl usuarioRepository=new UsuarioRepoImpl();
        int menu=menu();
        while (menu!=0){
            switch (menu){
                case 1 -> {
                    System.out.println("Introduce el año");
                    int anio= sc.nextInt();
                    List<Usuario> usuarios=usuarioRepository.usuariosNacidosAnioMAyorA(anio);
                    for (Usuario usuario:usuarios) {
                        System.out.println(usuario.getNombre()+" "+usuario.getApellidos());
                    }
                }
                case 2 -> {
                    System.out.println("Introduce el nº de canciones");
                    int numCancion= sc.nextInt();
                    List<Grupo> grupos=grupoRepository.gruposConCancionesMayoresA(numCancion);
                    for (Grupo grupo:grupos) {
                        System.out.println("Nombre: "+grupo.getNombre()+", compañía: "+grupo.getCompañia()+", localidad: "+grupo.getLocalidad()+", estilo: "+grupo.getEstilo()+", año de grabación: "+grupo.getAñograbacion()+", fecha de estreno: "+grupo.getFechaestreno());
                    }
                }
                case 3 -> {
                    List<String> grupos=new ArrayList<>();
                    System.out.println("Desea introducir el nombre de un grupo:\n1. Sí\n2. No");
                    int opcion= sc.nextInt();
                    while (opcion!=2){
                        System.out.println("Introduce el nombre de un grupo");
                        sc.nextLine();
                        String grupo=sc.nextLine();
                        grupos.add(grupo);
                        System.out.println("Desea introducir el nombre de un grupo:\n1. Sí\n2. No");
                        opcion= sc.nextInt();
                    }
                    List< Cancione> canciones=cancionRepository.getCancionesGrupo(grupos);
                    for (String grupo:grupos) {
                        for (Cancione cancione:canciones) {
                            System.out.println(grupo+" - "+cancione.getTitulo());
                        }
                    }
                }
                case 4 -> {
                    System.out.println("Introduce la localidad");
                    sc.nextLine();
                    String localidad=sc.nextLine();
                    System.out.println("Introduce el año");
                    int anio= sc.nextInt();
                    List<Grupo> grupos=grupoRepository.localidadesAnio(localidad,anio);
                    for (Grupo grupo:grupos) {
                        System.out.println("Nombre: "+grupo.getNombre()+", compañía: "+grupo.getCompañia()+", localidad: "+grupo.getLocalidad()+", estilo: "+grupo.getEstilo()+", año de grabación: "+grupo.getAñograbacion()+", fecha de estreno: "+grupo.getFechaestreno());
                    }
                }
            }
            menu=menu();
        }
    }
    public static int menu(){
        int opcion=0;
        do {
            System.out.println("Introduce una opción:\n1. Listado de usuarios nacidos a partir de año...\n2. Grupos que tienen más nº de canciones\n3. Canciones de grupo\n4. Grupos de localidad con primer disco en año antes de año\n0. Salir");
            opcion= sc.nextInt();
        }while (opcion<0||opcion>4);
        return opcion;
    }
}
