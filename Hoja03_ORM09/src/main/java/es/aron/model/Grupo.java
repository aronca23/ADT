package es.aron.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "grupos")
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;

    @Column(name = "localidad", nullable = false, length = 20)
    private String localidad;

    @Column(name = "estilo", nullable = false, length = 20)
    private String estilo;

    @Column(name = "esgrupo", nullable = false)
    private Boolean esgrupo = false;

    @Column(name = "\"añograbacion\"")
    private Integer añograbacion;

    @Column(name = "fechaestreno")
    private LocalDate fechaestreno;

    @Column(name = "\"compañia\"", length = 35)
    private String compañia;

    @OneToMany(mappedBy = "grupo")
    private Set<Cancione> canciones = new LinkedHashSet<>();

    @OneToMany(mappedBy = "grupo")
    private Set<Componente> componentes = new LinkedHashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public Boolean getEsgrupo() {
        return esgrupo;
    }

    public void setEsgrupo(Boolean esgrupo) {
        this.esgrupo = esgrupo;
    }

    public Integer getAñograbacion() {
        return añograbacion;
    }

    public void setAñograbacion(Integer añograbacion) {
        this.añograbacion = añograbacion;
    }

    public LocalDate getFechaestreno() {
        return fechaestreno;
    }

    public void setFechaestreno(LocalDate fechaestreno) {
        this.fechaestreno = fechaestreno;
    }

    public String getCompañia() {
        return compañia;
    }

    public void setCompañia(String compañia) {
        this.compañia = compañia;
    }

    public Set<Cancione> getCanciones() {
        return canciones;
    }

    public void setCanciones(Set<Cancione> canciones) {
        this.canciones = canciones;
    }

    public Set<Componente> getComponentes() {
        return componentes;
    }

    public void setComponentes(Set<Componente> componentes) {
        this.componentes = componentes;
    }

}