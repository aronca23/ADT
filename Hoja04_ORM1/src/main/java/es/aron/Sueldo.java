package es.aron;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Sueldo {
    private double salario;
    private double comision;

    public Sueldo() {
    }

    public Sueldo(double salario, double comision) {
        this.salario = salario;
        this.comision = comision;
    }
}
