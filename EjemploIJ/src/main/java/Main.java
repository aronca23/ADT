import java.sql.Connection;
import java.sql.PreparedStatement;

public class Main {
    public static void main(String[] args) {
        String sql="SELECT c.titulos,c.duracion FROM TITULOS C INNER JOIN grupos g ON c.numgrupo=g.numgrupo
        try (Connection connection=Conexion.getInstance().getConnection();
             PreparedStatement statement= connection.prepareStatement()){

        }
    }
}
