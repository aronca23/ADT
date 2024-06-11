package es.aron;

import es.aron.model.Grupo;
import es.aron.model.Usuario;
import es.aron.repository.impl.CancionRepositoryImpl;
import es.aron.repository.impl.GrupoRepositoryImpl;
import es.aron.repository.impl.UsuarioRepositoryImpl;

import java.util.List;
import java.util.Scanner;

public class App {
    static Scanner sc=new Scanner(System.in);

    public static void main(String[] args) {
        GrupoRepositoryImpl grupoRepository=new GrupoRepositoryImpl();
        CancionRepositoryImpl cancionRepository=new CancionRepositoryImpl();
        UsuarioRepositoryImpl usuarioRepository=new UsuarioRepositoryImpl();
        int menu=menu();
        while (menu!=0){
            switch (menu){
                case 1 -> {
                    List<Grupo> grupos=grupoRepository.listadoGrupos();
                    for (Grupo grupo:grupos) {
                       if (grupo.getCompañia()!=null){
                           System.out.println("Nombre: "+grupo.getNombre()+" - localidad: "+grupo.getLocalidad()+" - compañía: "+grupo.getCompañia()+" - Fecha de estreno: "+grupo.getFechaestreno());
                       } else {
                           System.out.println("Nombre: "+grupo.getNombre()+" - localidad: "+grupo.getLocalidad()+" - Fecha de estreno: "+grupo.getFechaestreno());
                       }
                    }
                }
                case 2 -> {
                    List<Usuario> usuarios=usuarioRepository.usuariosSinVotos();
                    for (Usuario usuario:usuarios) {
                        System.out.println("Nombre: "+usuario.getNombre()+" - apellidos: "+usuario.getApellidos()+" - fecha de nacimiento: "+usuario.getFechanacimiento());
                    }
                }
                case 3 -> {
                    List<Usuario> usuarios=usuarioRepository.usuariosPosterioresA1990();
                    for (Usuario usuario:usuarios) {
                        System.out.println("Nombre: "+usuario.getNombre()+" - apellidos: "+usuario.getApellidos()+" - fecha de nacimiento: "+usuario.getFechanacimiento());
                    }
                }
                case 4 -> {
                    List<Grupo> grupos=grupoRepository.gruposSinComponentes();
                    for (Grupo grupo:grupos) {
                        System.out.println(grupo.getNombre());
                    }
                }
                case 5 -> {
                    List<Grupo> grupos=grupoRepository.gruposSinCompania();
                    for (Grupo grupo:grupos) {
                        System.out.println(grupo.getNombre());
                    }
                }
                 case 6 -> {
                     System.out.println("Introduce la localidad");
                     sc.nextLine();
                     String localidad=sc.nextLine();
                     System.out.println("Introduce el año de salida de su primer disco");
                     int anio= sc.nextInt();
                     List<Grupo> grupos=grupoRepository.gruposDeAnioEstreno(localidad,anio);
                     for (Grupo grupo:grupos) {
                         System.out.println(grupo.getNombre());
                     }
                 }
                 case 7 -> {
                     System.out.println("Introduce la localidad");
                     sc.nextLine();
                     String localidad=sc.nextLine();
                     Long grupos=grupoRepository.numeroGruposDe(localidad);
                     System.out.println(grupos  );
                 }
                 case 8 -> {
                     System.out.println("Introduce la localidad del grupo");
                     sc.nextLine();
                     String localidad=sc.nextLine();
                    List<Object[]> cancionesGrupo=cancionRepository.numCancionesGrupos(localidad);
                     for (Object[] o:cancionesGrupo) {
                         String nombre= (String) o[0];
                         Long numGrupos= (Long) o[1];
                         System.out.println("El grupo "+nombre+" tiene "+numGrupos+" canciones");
                     }
                 }
                  case 9 -> {
                      List<Object[]> cancionesGrupo=cancionRepository.duracionMediaCancionesGrupos();
                      for (Object[] o:cancionesGrupo) {
                          String nombre= (String) o[0];
                          double duracion= (double) o[1];
                          System.out.println("El grupo "+nombre+" tiene una duracion de "+duracion);
                      }
                  }
            }
            menu=menu();
        }
    }
    public static int menu(){
        int opcion=0;
        do {
            System.out.println("Introduce una opción:\n1. Listado de grupos\n2. Listado de usuarios que no han votado ninguna vez\n3. Listado de usuarios nacidos a partir de 1990\n4. Grupos sin componentes cargados\n5. Grupos sin compañías cargadas\n6. Grupos de Barcelona con primer disco antes de 2019\n7. Nº de grupos de Madrid\n8. Nº de canciones de cada grupo de Madrid\n9. Duración media de las canciones de cada grupo\n0. Salir");
            opcion= sc.nextInt();
        }while (opcion<0||opcion>9);
        return opcion;
    }
}
