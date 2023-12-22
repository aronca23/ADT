package es.aron;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class Departamento {
    @Id
    private Long id;
    private String nombre;
    private String oficio;
    @Column(name = "fecha_alta")
    private LocalDate fechaAlta;
    @OneToMany(mappedBy = "departamento")
    private List<Empleado> empleados;

    public Departamento(Long id, String nombre, String oficio, LocalDate fechaAlta, List<Empleado> empleados) {
        this.id = id;
        this.nombre = nombre;
        this.oficio = oficio;
        this.fechaAlta = fechaAlta;
        this.empleados = empleados;
    }
    public Empleado addEmpleado(Empleado empleado){
        getEmpleados().add(empleado);
        empleado.setDepartamento(this);
        return empleado;
    }
    public Empleado removeEmpleado(Empleado empleado){
        getEmpleados().remove(empleado);
        empleado.setDepartamento(this);
        return empleado;
    }
}
