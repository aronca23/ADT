package gestor;

import clases.Cancion;
import clases.Grupo;
import clases.Voto;
import conexion.Conexion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Gestor {
    public Set<Grupo> listadoGrupos(){
        Set<Grupo> grupos=new HashSet<>();
        String sql="SELECT id,nombre,localidad,estilo FROM grupos ORDER BY id";
        try (Connection connection= Conexion.getInstance().getConec();
             PreparedStatement st= connection.prepareStatement(sql)){
            ResultSet rs=st.executeQuery();
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
        return grupos;
    }
    public Set<Grupo> listadoCanciones(){
        Set<Grupo> grupos=new HashSet<>();
        String sql1="SELECT  id,nombre FROM grupos";
        String sql2="SELECT titulo FROM canciones WHERE grupo=?";
        try (Connection connection=Conexion.getInstance().getConec();
        PreparedStatement st1= connection.prepareStatement(sql1);
        PreparedStatement st2= connection.prepareStatement(sql2)){
            ResultSet rs1=st1.executeQuery();

            while (rs1.next()){
                Grupo grupo=new Grupo();
                grupo.setId(rs1.getInt("id"));
                grupo.setNombre(rs1.getString("nombre"));
                st2.setInt(1,rs1.getInt("id"));
                ResultSet rs2=st2.executeQuery();
                while (rs2.next()){
                    Cancion cancion=new Cancion();
                    cancion.setTitulo(rs2.getString("titulo"));
                    grupo.getCanciones().add(cancion);
                }
                grupos.add(grupo);
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+" - "+e.getMessage());
        }
        return grupos;
    }
    public Map<Set<Grupo>,Integer> numeroCancionesGrupo(){
        int cont=0;
        Set<Grupo> grupos=new HashSet<>();
        Map<Set<Grupo>,Integer> grupoCancion=new HashMap<>();
        String sql1="SELECT nombre FROM grupos";
        String sql2="SELECT COUNT(c.id) AS numCancion FROM canciones c INNER JOIN grupos g ON g.id=c.grupo";
        try (Connection connection=Conexion.getInstance().getConec();
        PreparedStatement st1= connection.prepareStatement(sql1);
        PreparedStatement st2= connection.prepareStatement(sql2)){
            ResultSet rs1= st1.executeQuery();
            ResultSet rs2=st2.executeQuery();
            while (rs1.next()){
                Grupo grupo=new Grupo();
                grupo.setNombre(rs1.getString("nombre"));
                while (rs2.next()){
                    cont=rs2.getInt("numCancion");
                }
                grupos.add(grupo);
                grupoCancion.put(grupos,cont);
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+" - "+e.getMessage());
        }
        return grupoCancion;
    }
    public Set<Cancion> cancionesGrupo(String nombre){
        Set<Cancion> canciones=new HashSet<>();
        String sql="SELECT  c.id,c.titulo,c.duracion FROM canciones c INNER JOIN grupos g ON g.id=c.grupo WHERE g.nombre=?";
        try (Connection connection=Conexion.getInstance().getConec();
        PreparedStatement st= connection.prepareStatement(sql)){
            st.setString(1,nombre);
            ResultSet rs=st.executeQuery();
            while (rs.next()){
                Cancion cancion=new Cancion();
                cancion.setId(rs.getInt("id"));
                cancion.setTitulo(rs.getString("titulo"));
                cancion.setDuracion(rs.getTime(LocalTime.ofSecondOfDay(rs.getInt("duracion")).format(DateTimeFormatter.ofPattern("mm:ss"))).toLocalTime());

            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+" - "+e.getMessage());
        }
        return canciones;
    }
    public Set<Grupo> cancionesMasVotadas(){
        Set<Grupo> grupos=new HashSet<>();
        String sql="SELECT g.nombre,c.titulo FROM grupos g INNER JOIN canciones c ON g.id=c.grupo INNER JOIN votos v ON c.id=v.cancion ORDER BY v.cancion DESC LIMIT 5";
        try (Connection connection=Conexion.getInstance().getConec();
        PreparedStatement st= connection.prepareStatement(sql)){
            ResultSet rs= st.executeQuery();
            while (rs.next()){
                Grupo grupo=new Grupo();
                Cancion cancion=new Cancion();
                grupo.setNombre(rs.getString("nombre"));
                cancion.setTitulo(rs.getString("titulo"));
                grupo.getCanciones().add(cancion);
                grupos.add(grupo);
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+" - "+e.getMessage());
        }
        return grupos;
    }
    public Set<Grupo> gruposSinCanciones(){
        Set<Grupo> grupos=new HashSet<>();
        String sql="SELECT nombre FROM grupos WHERE id not in(SELECT g.id FROM grupos g INNER JOIN canciones c ON g.id=c.grupo)";
        try (Connection connection=Conexion.getInstance().getConec();
        PreparedStatement st= connection.prepareStatement(sql)){
            ResultSet rs= st.executeQuery();
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
    public Set<Grupo> votosMasRecientes(){
        Set<Grupo> grupos=new HashSet<>();
        String sql="SELECT g.nombre, c.titulo,v.cancion FROM grupos g INNER JOIN canciones c ON g.id=c.grupo INNER JOIN votos v ON c.id=v.cancion ORDER BY v.fecha LIMIT 5";
        try (Connection connection=Conexion.getInstance().getConec();
        PreparedStatement st= connection.prepareStatement(sql)){
            ResultSet rs=st.executeQuery();
            while (rs.next()){
                Grupo grupo=new Grupo();
                Cancion cancion=new Cancion();
                Voto voto=new Voto();
                grupo.setNombre(rs.getString("nombre"));
                cancion.setTitulo(rs.getString("titulo"));
                voto.setCancion(rs.getInt("cancion"));
                cancion.getVotos().add(voto);
                grupo.getCanciones().add(cancion);
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+" - "+e.getMessage());
        }
        return grupos;
    }
    public void eliminarCanciones(String nombre){
        String sql1="delete from votos where cancion in(select cancion c from votos v inner join canciones c on c.id=v.cancion inner join grupos g on g.id=c.grupo where g.nombre=?)";
        String sql2="DELETE FROM canciones where grupo in (select c.grupo from canciones c INNER JOIN grupos g ON g.id=c.grupo WHERE g.nombre=?)";
        try (Connection connection=Conexion.getInstance().getConec();
        PreparedStatement st1= connection.prepareStatement(sql1);
        PreparedStatement st2= connection.prepareStatement(sql2)){
            st1.setString(1,nombre);
            st2.setString(1,nombre);
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+" - "+e.getMessage());
        }
    }
    public Set<Grupo> modificarGrupo(String nombre,String preguntar,String estilo,int añoGrabacion,int año,int mes,int dia,String localidad,String compañia){
        Set<Grupo> grupos=new HashSet<>();
        String sql="SELECT estilo,añograbacion,fechaestreno,localidad,compañia FROM grupos WHERE nombre=?";
        String sql2="";
        try (Connection connection=Conexion.getInstance().getConec();
        PreparedStatement st= connection.prepareStatement(sql);
        PreparedStatement st2= connection.prepareStatement(sql2)){
            st.setString(1,nombre);
            ResultSet rs= st.executeQuery();

            while (rs.next()){
                Grupo grupo=new Grupo();
                grupo.setEstilo(rs.getString("estilo"));
                grupo.setAñoGrabacion(rs.getInt("añograbacion"));
                grupo.setFechaEstreno(rs.getDate("fechaestreno").toLocalDate());
                grupo.setLocalidad(rs.getString("localidad"));
                grupo.setCompañia(rs.getString("compañia"));
                if (preguntar.equalsIgnoreCase("estilo")){
                    sql="UPDATE grupos SET nombre=?";
                    st.setString(1,estilo);
                } else if (preguntar.equalsIgnoreCase("añograbacion")) {
                    sql="UPDATE grupos SET nombre=?";
                    st.setInt(1,añoGrabacion);
                }else if (preguntar.equalsIgnoreCase("fechaestreno")) {
                    sql="UPDATE grupos SET nombre=?";
                    LocalDate fecha=LocalDate.of(año,mes,dia);
                    st.setDate(1, Date.valueOf(fecha));
                }else if (preguntar.equalsIgnoreCase("localidad")) {
                    sql="UPDATE grupos SET nombre=?";
                    st.setString(1,localidad);
                }else if (preguntar.equalsIgnoreCase("compañia")) {
                    sql="UPDATE grupos SET nombre=?";
                    st.setString(1,compañia);
                }
                st2.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+" - "+e.getMessage());
        }
        return grupos;
    }
}
