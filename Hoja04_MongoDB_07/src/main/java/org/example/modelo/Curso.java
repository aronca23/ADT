package org.example.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.Document;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curso {
    private String id;
    private String titulo;
    private String categoria;
    private int horas;
    private List<Tema> temas;
    public static Curso fromDocumentToCapital(Document document)
    {
        List<Document> docTemas= (List<Document>) document.get("temas");
        Iterator<Document> temasIT= docTemas.stream().iterator();
        List<Tema> temas= new LinkedList<>();
        for(Document tema: docTemas){
            temas.add(new Tema(tema.getString("titulo"), tema.getInteger("horas")));
        }
        return new Curso(document.getString("_id"),
                document.getString("titulo"),
                document.getString("categoria"),
                document.getInteger("horas"),
                temas);
    }
    public static Document fromCapitalToDocument(Curso curso)
    {
        return new Document("id", curso.getId())
                .append("titulo", curso.getTitulo())
                .append("categoria",curso.getCategoria())
                .append("horas",curso.getHoras())
                .append("temas",curso.getTemas());
    }
}
