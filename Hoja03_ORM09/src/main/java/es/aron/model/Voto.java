package es.aron.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "votos")
public class Voto {
    @EmbeddedId
    private VotoId id;

    @MapsId("usuario")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cancion", nullable = false)
    private Cancione cancion;

    public VotoId getId() {
        return id;
    }

    public void setId(VotoId id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cancione getCancion() {
        return cancion;
    }

    public void setCancion(Cancione cancion) {
        this.cancion = cancion;
    }

}