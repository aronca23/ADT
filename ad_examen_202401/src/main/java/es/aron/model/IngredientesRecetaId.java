package es.aron.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class IngredientesRecetaId implements Serializable {
    private static final long serialVersionUID = -6999458966745896423L;
    @Column(name = "ingrediente_id", nullable = false)
    private Integer ingredienteId;

    @Column(name = "receta_id", nullable = false)
    private Integer recetaId;

    public Integer getIngredienteId() {
        return ingredienteId;
    }

    public void setIngredienteId(Integer ingredienteId) {
        this.ingredienteId = ingredienteId;
    }

    public Integer getRecetaId() {
        return recetaId;
    }

    public void setRecetaId(Integer recetaId) {
        this.recetaId = recetaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        IngredientesRecetaId entity = (IngredientesRecetaId) o;
        return Objects.equals(this.ingredienteId, entity.ingredienteId) &&
                Objects.equals(this.recetaId, entity.recetaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredienteId, recetaId);
    }

}