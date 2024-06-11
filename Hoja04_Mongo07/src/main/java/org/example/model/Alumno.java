package org.example.model;

import org.bson.Document;

import java.util.List;

public class Alumno {
    private String id,nombre,apellidos;
    private Curso curso;
    private double nota_media;
    private List<Double> notas;

    public Alumno() {
    }

    public Alumno(String id, String nombre, String apellidos, Curso curso, double nota_media, List<Double> notas) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.curso = curso;
        this.nota_media = nota_media;
        this.notas = notas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public double getNota_media() {
        return nota_media;
    }

    public void setNota_media(double nota_media) {
        this.nota_media = nota_media;
    }

    public List<Double> getNotas() {
        return notas;
    }

    public void setNotas(List<Double> notas) {
        this.notas = notas;
    }
    public static Alumno fromDocumentToAlumno(Document document)
    {
        Document document1= (Document) document.get("capital");
        Curso curso=null;
        if (document1!=null){
            curso=Curso.fromDocumentToCurso(document1);
        }
        return new Alumno(document.getString("_id"),
                document.getString("nombre"),
                document.getString("apellidos"),
                curso,
                document.getDouble("nota_media"),
                (List<Double>) document.get("notas")
        );
    }
    public Document fromAlumnoToDocument(Alumno tema)
    {
        return new Document("id", id)
                .append("nombre", nombre)
                .append("apellidos",apellidos)
                .append("curso",curso)
                .append("nota_media",nota_media)
                .append("notas",notas);
    }
}
