package gestor;

import BD.Conexion;
import clases.Cancion;
import clases.Grupo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Gestor {
    public Set<Grupo> listadoGrupos(){
        Set<Grupo> grupos=new HashSet<>();
        String sql="SELECT id,nombre,localidad,localidad,estilo FROM grupos ORDER BY id";

        try (Connection connection= Conexion.getInstance().getConnection();
             PreparedStatement statement=connection.prepareStatement(sql)){
            ResultSet rs=statement.executeQuery();
            while (rs.next()){
                Grupo grupo=new Grupo();
                grupo.setId(rs.getInt("id"));
                grupo.setNombre(rs.getString("nombre"));
                grupo.setLocalidad(rs.getString("localidad"));
                grupo.setEstilo(rs.getString("estilo"));
                grupos.add(grupo);
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+" - "+e.getMessage());
        }
        return  grupos;
    }
    public HashMap<String,Set<Cancion>> listadoCanciones(){
        HashMap<String,Set<Cancion>> cv=new HashMap<>();
        Set<Cancion>canciones=new HashSet<>();
        //String sql="SELECT g.nombre,c.titulo FROM canciones c INNER JOIN grupos g ON c.grupo=g.id ORDER BY g.nombre";
        String sql1="SELECT nombre,id FROM grupos ORDER BY nombre";
        String sql2="SELECT c.titulo FROM  canciones c INNER JOIN grupos g ON c.grupo=?";
        try (Connection connection=Conexion.getInstance().getConnection();
        PreparedStatement statement= connection.prepareStatement(sql1);
             PreparedStatement st= connection.prepareStatement(sql2)){
            ResultSet rs=statement.executeQuery();
            while (rs.next()){
                Grupo grupo=new Grupo();
                grupo.setId(rs.getInt("id"));
                grupo.setNombre("nombre");
                st.setInt(1,grupo.getId());
                ResultSet resultSet=st.executeQuery();
                while (resultSet.next()){
                    Cancion cancion=new Cancion();
                    cancion.setTitulo(rs.getString("titulo"));
                    canciones.add(cancion);
                    grupo.setCanciones(canciones);
                }
                cv.put(grupo.getNombre(),grupo.getCanciones());
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+" - "+e.getMessage());
        }
        return cv;
    }
    public HashMap<String,Integer> contarCanciones(){
        int i=0;
        HashMap<String,Integer> cv=new HashMap<>();
        Set<Cancion>canciones=new HashSet<>();
        //String sql="SELECT g.nombre,c.titulo FROM canciones c INNER JOIN grupos g ON c.grupo=g.id ORDER BY g.nombre";
        String sql1="SELECT nombre,id FROM grupos ORDER BY nombre";
        String sql2="SELECT * FROM  canciones c INNER JOIN grupos g ON c.grupo=?";
        try (Connection connection=Conexion.getInstance().getConnection();
             PreparedStatement statement= connection.prepareStatement(sql1);
             PreparedStatement st= connection.prepareStatement(sql2)){
            ResultSet rs=statement.executeQuery();
            while (rs.next()){
                Grupo grupo=new Grupo();
                grupo.setId(rs.getInt("id"));
                grupo.setNombre("nombre");
                st.setInt(1,grupo.getId());
                ResultSet resultSet=st.executeQuery();
                while (resultSet.next()){
                    i++;
                }
                cv.put(grupo.getNombre(),i);
                i=0;
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+" - "+e.getMessage());
        }
        return cv;
    }
    public Set<Grupo> cancionesPorGrupo(String nombre){
        Set<Grupo>grupos=new HashSet<>();
        String sql="SELECT c.id,c.titulo,c.duracion FROM canciones c INNER JOIN grupos g ON c.grupo=g.id WHERE g.nombre=?";
        try (Connection connection=Conexion.getInstance().getConnection();
        PreparedStatement st= connection.prepareStatement(sql)){
            st.setString(1,nombre);
            ResultSet rs=st.executeQuery();
            while (rs.next()){
                Cancion cancion=new Cancion();
                Grupo grupo=new Grupo();
                cancion.setId(rs.getInt("id"));
                cancion.setTitulo(rs.getString("titulo"));
                cancion.setDuracion(rs.getTime("duracion").toLocalTime());
                grupo.getCanciones().add(cancion);
               grupos.add(grupo);
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+" - "+e.getMessage());
        }
        return grupos;
    }
    public Set<Grupo> cancionesMasVotos(){ // modificar
        Set<Grupo>grupos=new HashSet<>();
        String sql="SELECT g.nombre,c.titulo,c.votos FROM canciones c INNER JOIN grupos g ORDER BY votos DESC LIMIT 5";
        try (Connection connection=Conexion.getInstance().getConnection();
             PreparedStatement st= connection.prepareStatement(sql)){
            ResultSet rs=st.executeQuery();
            while (rs.next()){
                Cancion cancion=new Cancion();
                Grupo grupo=new Grupo();
                grupo.setNombre(rs.getString("nombre"));
                cancion.setTitulo(rs.getString("titulo"));
                cancion.setNumVotos(rs.getInt("votos"));
                grupo.getCanciones().add(cancion);
                grupos.add(grupo);
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+" - "+e.getMessage());
        }
        return grupos;
    }
    public Set<Grupo> gruposSinCanciones(){
        Set<Grupo>grupos=new HashSet<>();
        String sql="SELECT g.nombre FROM grupos g INNER JOIN canciones c ON c.grupo=g.id WHERE g.id NOT IN c.grupo";
        try (Connection connection=Conexion.getInstance().getConnection();
        PreparedStatement st= connection.prepareStatement(sql)){
            ResultSet rs=st.executeQuery();
            while (rs.next()){
                Grupo grupo=new Grupo();
                grupo.setNombre(rs.getString("nombre"));
                grupos.add(grupo);
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+" - "+e.getMessage());
        }
        return grupos;
    }
    public Set<Grupo> votosRecientes(){

    }
    public
}
