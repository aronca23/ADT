package org.example.model;

import org.bson.Document;

import java.util.List;

public class Curso {
    private String id,titulo,categoria;
    private int horas;
    private List<Tema> temas;

    public Curso(String id, String titulo, String categoria, int horas, List<Tema> temas) {
        this.id = id;
        this.titulo = titulo;
        this.categoria = categoria;
        this.horas = horas;
        this.temas = temas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public List<Tema> getTemas() {
        return temas;
    }

    public void setTemas(List<Tema> temas) {
        this.temas = temas;
    }
    public static Curso fromDocumentToCurso(Document document)
    {
        List<Document> listaDocumentosTemas = (List<Document>) document.get("temas");
        List<Tema> temas = listaDocumentosTemas.stream().map(Tema::fromDocumentToTema).toList();
        return new Curso(document.getString("id"),
                document.getString("titulo"),
                document.getString("categoria"),
                document.getInteger("horas"),
                temas
        );
    }
    public Document fromTemaToDocument(Tema tema)
    {
        return new Document("id", id)
                .append("titulo",titulo)
                .append("categoria",categoria)
                .append("horas", horas)
                .append("temas",temas);
    }
}
