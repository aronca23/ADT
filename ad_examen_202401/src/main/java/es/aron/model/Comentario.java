package es.aron.model;

import jakarta.persistence.*;

@Entity
@Table(name = "comentarios")
@NamedQuery(name = "Comentario.getComentarios", query = "from Comentario c where c.receta.autor.login=:login")
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "receta_id", nullable = false)
    private Receta receta;

    @Column(name = "titulo", nullable = false, length = 50)
    private String titulo;

    @Lob
    @Column(name = "texto", nullable = false)
    private String texto;

    public Comentario(Receta receta, String titulo, String texto) {
        this.receta = receta;
        this.titulo = titulo;
        this.texto = texto;
    }

    public Comentario() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

}