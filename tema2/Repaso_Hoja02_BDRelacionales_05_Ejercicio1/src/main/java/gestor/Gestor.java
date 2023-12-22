package gestor;

import clases.Grupo;
import clases.Voto;
import conexion.Conexion;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class Gestor {
    public Set<Grupo> ejercicio1(int id1,String preguntar,String nombre,String localidad,String estilo,String esgrupo,int añoGrabacion,int año,int mes,int dia, String compañia){
        Set<Grupo> grupos=new HashSet<>();
        String sql="SELECT * FROM grupos WHERE id=?";
        try (Connection connection= Conexion.getInstance().getConec();
        PreparedStatement st= connection.prepareStatement(sql)){
            st.setInt(1,id1);
            ResultSet rs=st.executeQuery();
            while (rs.next()){
                Grupo grupo=new Grupo();
                grupo.setId(rs.getInt("id"));
                grupo.setNombre(rs.getString("nombre"));
                grupo.setLocalidad(rs.getString("localidad"));
                grupo.setEstilo(rs.getString("estilo"));
                grupo.setEsGrupo(rs.getBoolean("esgrupo"));
                grupo.setAñoGrabacion(rs.getInt("añograbacion"));
                grupo.setFechaEstreno(rs.getDate("fechaestreno").toLocalDate());
                grupo.setCompañia(rs.getString("compañia"));
                if (preguntar.equalsIgnoreCase("si")){
                    rs.updateString("nombre",nombre);
                    rs.updateString("localidad",localidad);
                    rs.updateString("estilo",estilo);
                    boolean esGrupo=false;
                    if (esgrupo.equalsIgnoreCase("si")) esGrupo=true;
                    else esGrupo=false;
                    rs.updateBoolean("esgrupo",esGrupo);
                    rs.updateInt("añograbacion",añoGrabacion);
                    LocalDate fechaEstreno=LocalDate.of(año,mes,dia);
                    rs.updateDate("fechaestreno", Date.valueOf(fechaEstreno));
                    rs.updateString("localidad",localidad);
                    rs.updateRow();
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+" - "+e.getMessage());
        }
        return grupos;
    }
    public Set<Voto> ejercicio2(int opcion,String usuario,int año,int mes,int dia,int cancion){
        Set<Voto> votos=new HashSet<>();
        String sql="SELECT * FROM votos ORDER BY fecha DESC LIMIT 5";
        try (Connection connection=Conexion.getInstance().getConec();
        PreparedStatement st= connection.prepareStatement(sql)){
            ResultSet rs=st.executeQuery();
            while (rs.next()){
                Voto voto=new Voto();
                voto.setUsuario(rs.getString("usuario"));
                if (opcion==1){
                    rs.updateString("usuario",usuario);
                    rs.updateRow();
                }else {
                    rs.deleteRow();
                }
                voto.setFecha(rs.getDate("fecha").toLocalDate());
                LocalDate fecha=LocalDate.of(año,mes,dia);
                if (opcion==1){
                    rs.updateDate("fecha", Date.valueOf(fecha));
                    rs.updateRow();
                }else {
                    rs.deleteRow();
                }
                voto.setCancion(rs.getInt("cancion"));
                if (opcion==1){
                    rs.updateInt("cancion",cancion);
                    rs.updateRow();
                }else {
                    rs.deleteRow();
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+" - "+e.getMessage());
        }
        return votos;
    }
    public Set<Voto> ejercicio3(String usuario,String preguntar,int año,int mes,int dia){
        Set<Voto> votos=new HashSet<>();
        String sql="SELECT * FROM votos WHERE usuario=?";
        try (Connection connection=Conexion.getInstance().getConec();
        PreparedStatement st= connection.prepareStatement(sql)){
            ResultSet rs=st.executeQuery();
            st.setString(1,usuario);
            while (rs.next()){
                rs.getString("usuario");
                rs.getDate("fecha");
                LocalDate fecha=LocalDate.of(año
                ,mes,dia);
                if (preguntar.equalsIgnoreCase("si")){
                    rs.updateDate("fecha", Date.valueOf(fecha));
                    rs.updateRow();
                }
                rs.getInt("cancion");
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+" - "+e.getMessage());
        }
        return votos;
    }
}
