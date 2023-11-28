package Gestor;

import clases.Grupo;
import conexion.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class Ejercicio1 {
    public Set<Grupo> mostrarGrupos(int id,String preguntar){
        Set<Grupo>grupos=new HashSet<>();
        String sql="SELECT * FROM grupos WHERE id=?";
        try (Connection connection= Conexion.getInstance().getConnection();
             PreparedStatement st= connection.prepareStatement(sql)){
            st.setInt(1,id);
            ResultSet rs=st.executeQuery();

        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+" - "+e.getMessage());
        }
        return grupos;
    }
}
