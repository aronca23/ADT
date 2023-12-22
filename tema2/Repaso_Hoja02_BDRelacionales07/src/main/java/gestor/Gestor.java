package gestor;

import clases.Alumno;
import clases.Curso;
import clases.Profesor;
import conexion.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Gestor {
    public Set<String> tutorCurso(int id) {
        Set<String> profesores=new HashSet<>();
        String sql="SELECT p.nombre FROM profesor p INNER JOIN curso c ON p.id=c.tutor_id WHERE c.id=?";
        try (Connection connection= Conexion.getInstance().getConec();
             PreparedStatement st= connection.prepareStatement(sql)){
            st.setInt(1,id);
            ResultSet rs=st.executeQuery();
            while (rs.next()) {
                Profesor profesor=new Profesor();
                profesor.setNombre(rs.getString("nombre"));
                profesores.add(profesor.getNombre());
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+" - "+e.getMessage());
        }
        return profesores;
    }
    public Set<String> cursosSinTutor(){
        Set<String> cursos=new HashSet<>();
        String sql="SELECT nombre FROM curso WHERE tutor_id NOT IN (SELECT id FROM profesor)";
        try (Connection connection=Conexion.getInstance().getConec();
        PreparedStatement st= connection.prepareStatement(sql)){
            ResultSet rs= st.executeQuery();
            while (rs.next()) {
                Curso curso=new Curso();
                curso.setNombre(rs.getString("nombre"));
                cursos.add(curso.getNombre());
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+" - "+e.getMessage());
        }
        return cursos;
    }
    public Set<String> cursosSinAlumnos(){
        Set<String> cursos=new HashSet<>();
        String sql="SELECT c.nombre FROM curso c INNER JOIN alumno a ON c.id=a.curso WHERE c.id NOT IN (SELECT curso FROM alumno)";
        try (Connection connection=Conexion.getInstance().getConec();
             PreparedStatement st= connection.prepareStatement(sql)){
            ResultSet rs= st.executeQuery();
            while (rs.next()) {
                Curso curso=new Curso();
                curso.setNombre(rs.getString("nombre"));
                cursos.add(curso.getNombre());
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+" - "+e.getMessage());
        }
        return cursos;
    }
    public Set<String> profesoresNoTutores(){
        Set<String> profesores=new HashSet<>();
        String sql="SELECT nombre FROM profesor WHERE id NOT IN (SELECT tutor_id FROM curso)";
        try (Connection connection=Conexion.getInstance().getConec();
        PreparedStatement st= connection.prepareStatement(sql)){
            ResultSet rs=st.executeQuery();
            while (rs.next()){
                Profesor profesor=new Profesor();
                profesor.setNombre("nombre");
                profesores.add(profesor.getNombre());
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+" - "+e.getMessage());
        }
        return profesores;
    }
    public Map<Set<String>,Integer> numeroAlumnosEnCursos(){
        int cont=0;
        Set<String> cursos=new HashSet<>();
        Map<Set<String>,Integer> cursoAlumnos=new HashMap<>();
        String sql1="SELECT nombre FROM curso";
        /*String sql2="SELECT * FROM alumno a INNER JOIN curso c ON c.id=a.curso";*/
        String sql2="SELECT COUNT(a.id) AS nAlumnos FROM alumno a INNER JOIN curso c ON c.id=a.curso";
        try (Connection connection=Conexion.getInstance().getConec();
        PreparedStatement st1= connection.prepareStatement(sql1);
             PreparedStatement st2= connection.prepareStatement(sql2)){
            ResultSet rs1=st1.executeQuery();
            ResultSet rs2=st2.executeQuery();
            while (rs1.next()){
                Curso curso=new Curso();
                curso.setNombre(rs1.getString("nombre"));
                while (rs2.next()){
                    cont=rs2.getInt("nAlumnos");
                }
                cursos.add(curso.getNombre());
                cursoAlumnos.put(cursos,cont);
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+" - "+e.getMessage());
        }
        return cursoAlumnos;
    }
    public Set<String> cursosConMenosAlumnos(int num){
        Set<String> cursos=new HashSet<>();
        String sql="SELECT c.nombre FROM curso c INNER JOIN alumno a ON c.id=a.curso WHERE (SELECT COUNT(id) AS nAlumnos FROM alumno)<"+num;
        try (Connection connection=Conexion.getInstance().getConec();
        PreparedStatement st= connection.prepareStatement(sql)){
            ResultSet rs=st.executeQuery();
            while (rs.next()){
                Curso curso=new Curso();
                curso.setNombre(rs.getString("nombre"));
                cursos.add(curso.getNombre());
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+" - "+e.getMessage());
        }
        return cursos;
    }
    public Set<String> alumnosMismoCurso(int id){
        Set<String> alumnos=new HashSet<>();
        String sql="SELECT nombre FROM alumno WHERE id IN (SELECT * FROM curso c INNER JOIN alumno a ON c.id=a.curso WHERE a.id=)";
        try (Connection connection=Conexion.getInstance().getConec();
        PreparedStatement st= connection.prepareStatement(sql)){

        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+" - "+e.getMessage());
        }
        return alumnos;
    }
    public Set<String> cursoMasAlumnos(){
        Set<String> cursos=new HashSet<>();
        String sql="SELECT c.nombre FROM curso c INNER JOIN alumno a ON c.id=a.curso  WHERE a.id =(SELECT MAX(a.id) AS curs FROM alumno a INNER JOIN curso c ON c.id=a.curso)";
        try (Connection connection=Conexion.getInstance().getConec();
        PreparedStatement st= connection.prepareStatement(sql)){
            ResultSet rs=st.executeQuery();
            while (rs.next()){
                Curso curso=new Curso();
                curso.setNombre(rs.getString("nombre"));
                cursos.add(curso.getNombre());
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+" - "+e.getMessage());
        }
        return cursos;
    }
    public Set<Curso> alumnosConMayorNotaMedia(int limite){
        Set<Curso> cursos=new HashSet<>();
        String sql="SELECT a.nombre,c.nombre,a.nota_media FROM alumno a INNER JOIN curso c ON c.id=a.curso ORDER BY nota_media DESC LIMIT "+limite;
        try (Connection connection=Conexion.getInstance().getConec();
        PreparedStatement st= connection.prepareStatement(sql)){
            ResultSet rs=st.executeQuery();
            while (rs.next()){
                Curso curso=new Curso();
                Alumno alumno=new Alumno();
                alumno.setNombre(rs.getString("nombre"));
                alumno.setNotaMedia(rs.getDouble("nota_media"));
                curso.setNombre(rs.getString("nombre"));
                curso.getAlumnos().add(alumno);
                cursos.add(curso);
            }
        } catch (SQLException e) {
            System.err.println(e.getErrorCode()+" - "+e.getMessage());
        }
        return cursos;
    }
}
