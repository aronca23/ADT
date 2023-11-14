package gestor;

import conexion.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Gestor {
    public void incrementarVotos(int numcancion){
        String sql="UPDATE canciones SET votos=votos+1 WHERE numcancion=?";
        try (Connection connection= Conexion.getInstance().getConnection();
                PreparedStatement statement=connection.prepareStatement(sql)){
            statement.setInt(1,numcancion);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
