package Repo;

import model.Alumno;

import java.util.List;
import java.util.Map;

public interface AlumnoRepository {

    List<Alumno> getAlumnosFromCurso(String idCurso);

    void introducirNotasAlumnos(Map<String, Double> mapaAlumnos_Notas);

    List<Alumno> alumnosAprobadosDeCurso(String codigoCurso);

    Alumno getAlumnoById(String codigoAlumno);
}
