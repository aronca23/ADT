package model;

import com.mongodb.internal.operation.EstimatedDocumentCountOperation;
import lombok.*;
import org.bson.Document;

import java.util.List;
import java.util.function.DoubleConsumer;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Alumno {

  private String id, nombre, apellidos, curso;
  private double nota_media;
  private List<Double> notas;


  public static Alumno fromDocToAlumno(Document doc){
      return new Alumno(doc.getString("_id"),
              doc.getString("nombre"),
              doc.getString("apellidos"),
              doc.getString("curso"),
              doc.getDouble("nota_media"),
              doc.getList("notas", double.class));
  }

  public Document fromAlumnoToDocument(Alumno alumno){
      return new Document("id", id)
              .append("nombre", nombre)
              .append("apellidos", apellidos)
              .append("curso", curso)
              .append("nota_media", nota_media)
              .append("notas", notas);
    }

    public Alumno(String id, String nombre, String apellidos){
      this.id= id;
      this.nombre=nombre;
      this.apellidos=apellidos;
    }
}
