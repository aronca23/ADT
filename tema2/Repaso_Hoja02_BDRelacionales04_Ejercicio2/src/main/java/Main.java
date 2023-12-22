import clases.Cancion;
import clases.Grupo;
import gestor.Gestor;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Map;
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
                    Set<Grupo> m1=gestor.listadoGrupos();
                    Iterator<Grupo> it=m1.iterator();
                    while (it.hasNext()){
                        Grupo grupo=it.next();
                        System.out.println(grupo.getId()+" - "+grupo.getNombre()+" - "+grupo.getLocalidad()+" - "+grupo.getEstilo());
                    }
                }
                case 2 -> {
                    Set<Grupo> m2=gestor.listadoCanciones();
                    Iterator<Grupo> it=m2.iterator();
                    while (it.hasNext()){
                        Grupo grupo=it.next();
                        System.out.printf(grupo.getNombre()+"\n%s\t"+grupo.mostrarCancion());
                    }
                }
                case 3 -> {
                    Map<Set<Grupo>,Integer> m3=gestor.numeroCancionesGrupo();
                    Set<Set<Grupo>> claves=m3.keySet();
                    Iterator<Set<Grupo>> it=claves.iterator();
                    while (it.hasNext()){
                        Set<Grupo> clave=it.next();
                        int valor=m3.get(clave);
                        System.out.println(clave+" - "+valor);
                    }
                }
                case 4 -> {
                    System.out.println("Introduce un nombre de grupo");
                    String nombre=sc.nextLine();
                    Set<Cancion> m4=gestor.cancionesGrupo(nombre);
                    Iterator<Cancion> it=m4.iterator();
                    while (it.hasNext()){
                        Cancion cancion=it.next();
                        System.out.println(cancion.getId()+" - "+cancion.getTitulo()+" - "+cancion.getDuracion());
                    }
                }
                case 5 -> {
                    Set<Grupo> m5=gestor.cancionesMasVotadas();
                    Iterator<Grupo> it=m5.iterator();
                    while (it.hasNext()){
                        Grupo grupo=it.next();
                        System.out.println(grupo.getNombre()+" - "+grupo.mostrarCancion());
                    }
                }
                case 6 -> {
                    Set<Grupo> m6=gestor.gruposSinCanciones();
                    Iterator<Grupo> it=m6.iterator();
                    while (it.hasNext()){
                        Grupo grupo=it.next();
                        System.out.println(grupo.getNombre());
                    }
                }
                case  7 -> {
                    Set<Grupo> m7=gestor.votosMasRecientes();
                    Iterator<Grupo> it=m7.iterator();
                    while (it.hasNext()){
                        Grupo grupo=it.next();
                        System.out.println(grupo.getNombre()+" - "+grupo.mostrarCancion2());
                    }
                }
                case 8 -> {
                    System.out.println("Introduce el nombre");
                    String nombre=sc.nextLine();
                    gestor.eliminarCanciones(nombre);
                }
                case 9 -> {
                    String estilo="",localidad="",compañia="";
                    int añoGrabacion=0,año=0,mes=0,dia=0;
                    System.out.println("Introduce el nombre");
                    String nombre=sc.nextLine();
                    System.out.println("¿Qué campo deseas modificar?");
                    String preguntar=sc.nextLine();
                    if (preguntar.equalsIgnoreCase("estilo")){
                        System.out.println("Introduce el estilo");
                        estilo=sc.nextLine();
                    } else if (preguntar.equalsIgnoreCase("añograbacion")) {
                        System.out.println("Introduce el año de grabación");
                        añoGrabacion= sc.nextInt();
                    }else if (preguntar.equalsIgnoreCase("fechaestreno")) {
                        System.out.println("Introduce el año");
                        año=sc.nextInt();
                        System.out.println("Introduce el mes");
                        mes=sc.nextInt();
                        System.out.println("Introduce el día");
                        dia=sc.nextInt();
                    }else if (preguntar.equalsIgnoreCase("localidad")) {
                        System.out.println("Introduce la localidad");
                        sc.nextLine();
                        localidad=sc.nextLine();
                    }else if (preguntar.equalsIgnoreCase("compañia")) {
                        System.out.println("Introduce la compañia");
                        compañia=sc.nextLine();
                    }
                    Set<Grupo> m9=gestor.modificarGrupo(nombre,preguntar,estilo,añoGrabacion,año,mes,dia,localidad,compañia);
                    Iterator<Grupo> it= m9.iterator();
                    while (it.hasNext()){
                        Grupo grupo=it.next();
                        System.out.println(grupo.getEstilo()+" - "+grupo.getAñoGrabacion()+" - "+grupo.getFechaEstreno()+" - "+grupo.getLocalidad()+" - "+grupo.getCompañia());
                    }
                }
            }
        }
    }
    public static int elegirOpcion(){
        int opcion=0;
        do {
            System.out.println("Introduce una opción:\n1. Listado de grupos\n2. Listado de canciones\n3. Nº canciones por grupo\n4. Canciones de un grupo\5. Las 5 canciones más votadas\n6. Grupos sin canciones\n7. Últimos 5 votos\n8. Eliminar canciones de un grupo\n 9. Modificar datos de grupo");
            opcion=sc.nextInt();
        }while (opcion<0||opcion>9);
        return opcion;
    }
}
