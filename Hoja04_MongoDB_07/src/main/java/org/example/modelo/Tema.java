package org.example.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tema {
    private String titulo;
    private int horas;
    public static Tema fromDocumentToTema(Document document)
    {
        return new Tema(document.getString("titulo"),
                document.getInteger("horas")
        );
    }
    public static Document fromTemaToDocument(Tema tema)
    {
        return new Document("titulo", tema.getTitulo())
                .append("horas", tema.getHoras());
    }
}
