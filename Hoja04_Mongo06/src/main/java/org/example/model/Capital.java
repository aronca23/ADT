package org.example.model;

import org.bson.Document;

public class Capital {
    private String nombre;
    private int numHabitantes;

    public Capital() {
    }

    public Capital(String nombre, int numHabitantes) {
        this.nombre = nombre;
        this.numHabitantes = numHabitantes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumHabitantes() {
        return numHabitantes;
    }

    public void setNumHabitantes(int numHabitantes) {
        this.numHabitantes = numHabitantes;
    }
    public static Capital fromDocumentToCapital(Document document)
    {
        Integer num=document.getInteger("numHabitantes");
        Integer habitantes=0;
        if (num!=null) habitantes=document.getInteger("numHabitantes");
        else habitantes=0;
        return new Capital(document.getString("nombre"),
                habitantes
        );
    }
    public Document fromCapitalToDocument(Capital capital)
    {
        return new Document("nombre", nombre)
                .append("numHabitantes", numHabitantes);
    }
}
