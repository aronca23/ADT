package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static Conexion instance;
    private Connection conec;
    private final String url="jdbc:mariadb://localhost:3316/cursos";
    private final String driver= "org.mariadb.jdbc.Driver";
    private final String username="root";
    private final String password="root";
    private Conexion(){


        try{
            Class.forName(driver);
            this.conec= DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Conexion getInstance(){
        try{
            if(instance==null){
                instance=new Conexion();
            } else if (instance.getConec().isClosed()) {
                instance= new Conexion();
            }
        } catch (SQLException e) {
            System.err.println("Error al devolver la instancia");
        }
        return instance;
    }

    public Connection getConec() {
        return conec;
    }
}
