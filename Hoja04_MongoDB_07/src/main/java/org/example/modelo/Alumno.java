package org.example.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alumno {
    private String id,nombre,apellidos,curso;
    private double nota_media;
    private List<Double> notas;
    public static Alumno fromDocumentToAlumno(Document document)
    {
        return new Alumno(document.getString("id"),
                document.getString("nombre"),
                document.getString("apellidos"),
                document.getString("curso"),
                document.getDouble("nota_media"),
                document.getList("notas", Double.class)
        );
    }
    public static Document fromAlumnoToDocument(Alumno alumno)
    {
        return new Document("id", alumno.getId())
                .append("nombre", alumno.getNombre())
                .append("apellidos",alumno.getApellidos())
                .append("curso",alumno.getCurso())
                .append("nota_media",alumno.getNota_media())
                .append("notas",alumno.getNotas());
    }
}
