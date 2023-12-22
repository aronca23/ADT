package es.aron;

import com.sun.istack.NotNull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.units.qual.Length;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    @Column(length = 50)
    private String oficio;
    @Column(name = "fecha_alta")
    private LocalDate fechaAlta;
    @Embedded
    private Sueldo sueldo;
    @ManyToOne
    private Departamento departamento;

    public Empleado(String nombre, String oficio, LocalDate fechaAlta, double salario, double comision) {
        this.nombre = nombre;
        this.oficio = oficio;
        this.fechaAlta = fechaAlta;
        sueldo=new Sueldo(salario,comision);
    }

    public Empleado(Long id, String nombre, String oficio, LocalDate fechaAlta, double salario, double comision) {
        this.id = id;
        this.nombre = nombre;
        this.oficio = oficio;
        this.fechaAlta = fechaAlta;
        sueldo=new Sueldo(salario,comision);
    }

    public Empleado() {
    }
}
