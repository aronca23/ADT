package org.example.model;

import org.bson.Document;

import java.util.List;

public class Tema {
    private String titulo;
    private int horas;

    public Tema() {
    }

    public Tema(String titulo, int horas) {
        this.titulo = titulo;
        this.horas = horas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }
    public static Tema fromDocumentToTema(Document document)
    {
        return new Tema(document.getString("titulo"),
                document.getInteger("horas")
        );
    }
    public Document fromTemaToDocument(Tema tema)
    {
        return new Document("titulo", titulo)
                .append("horas", horas);
    }
}
