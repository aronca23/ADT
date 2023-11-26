package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static Conexion instance;
    private Connection connection;
    private final String URL="jdbc:postgresql://localhost/concursomusica";
    private final String USER="postgres";
    private final String PASSWD="PassWd@10";
    private Conexion(){
        try {
            Class.forName("org.postgresql.Driver");
            this.connection= DriverManager.getConnection(URL,USER,PASSWD);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Connection getConnection(){
        return connection;
    }
    public static Conexion getInstance(){
        try {
            if(instance==null){
                instance=new Conexion();
            } else if (instance.getConnection().isClosed()) {
                instance=new Conexion();
            }
        }catch (SQLException e){
            System.err.println(e);
        }
        return instance;
    }
}
