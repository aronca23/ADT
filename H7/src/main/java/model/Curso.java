package model;

import lombok.*;
import org.bson.Document;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Curso {
    private String id, titulo, categoria;
    private int horas;
    private List<Tema> temas;

    public Curso(String id, String titulo) {
        this.id=id;
        this.titulo=titulo;
    }

    public static Curso fromDocToCurso(Document doc){
       List<Document> docTemas= (List<Document>) doc.get("temas");
        Iterator<Document> temasIT= docTemas.stream().iterator();
        List<Tema> temas= new LinkedList<>();
        for(Document tema: docTemas){
            temas.add(new Tema(tema.getString("titulo"), tema.getInteger("horas")));
        }
        /*while(temasIT.hasNext()){
            Document docTema= temasIT.next();
            temas.add(new Tema(docTema.getString("titulo"), doc.getInteger("horas")));
        }*/
        return new Curso(doc.getString("_id"),
                doc.getString("titulo"),
                doc.getString("categoria"),
                doc.getInteger("horas"),
                temas);
    }

    public Document fromCursoToDocument(Document doc){
        return new Document("id", id)
                .append("titulo", titulo)
                .append("categoria", categoria)
                .append("horas", horas)
                .append("temas", temas);
    }
}
