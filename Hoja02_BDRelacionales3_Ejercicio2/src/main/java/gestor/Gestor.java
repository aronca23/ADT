package gestor;

import conexion.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Gestor {
    public void insertarCanciones(int numgrupo,int numcancion,String titulo,int duracion){
        String sql="INSERT INTO canciones(numcancion,titulo,duracion,numgrupo,votos) VALUES(?,?,?,?,?)";
        try (Connection connection=Conexion.getInstance().getConnection();
             PreparedStatement statement= connection.prepareStatement(sql)){
            statement.setInt(4,numgrupo);
            statement.setInt(1,numcancion);
            statement.setString(2,titulo);
            statement.setInt(3,duracion);
            statement.setInt(5,0);
            statement.execute();
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+" - "+e.getMessage());
        }
    }
}
