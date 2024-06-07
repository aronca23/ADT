package es.aron.model;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "recetas")
public class Receta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 200)
    private String nombre;

    @Lob
    @Column(name = "elaboracion", nullable = false)
    private String elaboracion;

    @Column(name = "comensales", nullable = false)
    private Byte comensales;

    @Column(name = "tiempo", nullable = false)
    private Byte tiempo;

    @Column(name = "dificultad", nullable = false)
    private Integer dificultad;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "autor", nullable = false)
    private Usuario autor;

    @Column(name = "fecha", nullable = false)
    private Instant fecha;

    @Column(name = "positivos", nullable = false)
    private Integer positivos;

    @Column(name = "negativos", nullable = false)
    private Integer negativos;

    @OneToMany(mappedBy = "receta")
    private Set<Comentario> comentarios = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "recetas")
    private Set<Ingrediente> ingredientes = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getElaboracion() {
        return elaboracion;
    }

    public void setElaboracion(String elaboracion) {
        this.elaboracion = elaboracion;
    }

    public Byte getComensales() {
        return comensales;
    }

    public void setComensales(Byte comensales) {
        this.comensales = comensales;
    }

    public Byte getTiempo() {
        return tiempo;
    }

    public void setTiempo(Byte tiempo) {
        this.tiempo = tiempo;
    }

    public Integer getDificultad() {
        return dificultad;
    }

    public void setDificultad(Integer dificultad) {
        this.dificultad = dificultad;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Instant getFecha() {
        return fecha;
    }

    public void setFecha(Instant fecha) {
        this.fecha = fecha;
    }

    public Integer getPositivos() {
        return positivos;
    }

    public void setPositivos(Integer positivos) {
        this.positivos = positivos;
    }

    public Integer getNegativos() {
        return negativos;
    }

    public void setNegativos(Integer negativos) {
        this.negativos = negativos;
    }

    public Set<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(Set<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Set<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(Set<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

}