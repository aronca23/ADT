package org.example.model;

import org.bson.Document;

import java.util.List;

public class ComunidadAutonoma {
    private String codigo;
    private String nombre,abreviatura;
    private Capital capital;
    private Integer habitantes,extension;
    private List<String> provincias;

    public ComunidadAutonoma(String codigo, String nombre, String abreviatura, Capital capital, int habitantes, int extension, List<String> provincias) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.abreviatura = abreviatura;
        this.capital = capital;
        this.habitantes = habitantes;
        this.extension = extension;
        this.provincias = provincias;
    }

    public ComunidadAutonoma() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public Capital getCapital() {
        return capital;
    }

    public void setCapital(Capital capital) {
        this.capital = capital;
    }

    public int getHabitantes() {
        return habitantes;
    }

    public void setHabitantes(int habitantes) {
        this.habitantes = habitantes;
    }

    public int getExtension() {
        return extension;
    }

    public void setExtension(int extension) {
        this.extension = extension;
    }

    public void setHabitantes(Integer habitantes) {
        this.habitantes = habitantes;
    }

    public void setExtension(Integer extension) {
        this.extension = extension;
    }

    public List<String> getProvincias() {
        return provincias;
    }

    public void setProvincias(List<String> provincias) {
        this.provincias = provincias;
    }

    public static ComunidadAutonoma fromDocumentToComunidadAutonoma(Document document)
    {
        Integer extension= document.getInteger("extension");
        Document document1= (Document) document.get("capital");
        Capital capital=null;
        if (document1!=null){
            capital=Capital.fromDocumentToCapital(document1);
        }
        Integer e=0;
        if (extension!=null) e=document.getInteger("extension");
        else e=0;
        return new ComunidadAutonoma(document.getString("codigo"),
                document.getString("nombre"),
                document.getString("abreviatura"),
                capital,
                document.getInteger("habitantes"),
                e,
                (List<String>) document.get("provincias")
        );
    }
    public Document fromComunidadAutonomaToDocument(ComunidadAutonoma comunidadAutonoma)
    {
        return new Document("codigo", codigo)
                .append("nombre", nombre)
                .append("abreviatura", abreviatura)
                .append("capital", new Document(capital.getNombre(),capital.getNumHabitantes()))
                .append("habitantes", habitantes)
                .append("extension",extension);
    }

    public ComunidadAutonoma(String codigo, String nombre, String abreviatura, Integer habitantes, Integer extension, List<String> provincias) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.abreviatura = abreviatura;
        this.habitantes = habitantes;
        this.extension = extension;
        this.provincias = provincias;
    }
}
