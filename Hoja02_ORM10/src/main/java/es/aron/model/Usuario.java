package es.aron.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @Column(name = "usuario", nullable = false, length = 15)
    private String usuario;

    @Column(name = "password", nullable = false, length = 32)
    private String password;

    @Column(name = "nombre", nullable = false, length = 15)
    private String nombre;

    @Column(name = "apellidos", nullable = false, length = 30)
    private String apellidos;

    @Column(name = "fechanacimiento")
    private LocalDate fechanacimiento;

    @OneToMany(mappedBy = "usuario")
    private Set<Voto> votos = new LinkedHashSet<>();

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public LocalDate getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(LocalDate fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public Set<Voto> getVotos() {
        return votos;
    }

    public void setVotos(Set<Voto> votos) {
        this.votos = votos;
    }

}