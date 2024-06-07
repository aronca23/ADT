package es.aron.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ingredientes_recetas")
public class IngredientesReceta {
    @EmbeddedId
    private IngredientesRecetaId id;

    @MapsId("ingredienteId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ingrediente_id", nullable = false)
    private Ingrediente ingrediente;

    @MapsId("recetaId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "receta_id", nullable = false)
    private Receta receta;

    public IngredientesRecetaId getId() {
        return id;
    }

    public void setId(IngredientesRecetaId id) {
        this.id = id;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

}