package org.example;

import java.util.List;

public class Contacto
{
    private String nombre;
    private long id;
    private String email;
    private List<String> numeros;

    public Contacto(String nombre, long id, String email, List<String> numeros) {
        this.nombre = nombre;
        this.id = id;
        this.email = email;
        this.numeros = numeros;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Contacto{");
        sb.append("nombre='").append(nombre).append('\'');
        sb.append(", id=").append(id);
        sb.append(", email='").append(email).append('\'');
        sb.append(", numeros=").append(numeros);
        sb.append('}');
        return sb.toString();
    }
}
