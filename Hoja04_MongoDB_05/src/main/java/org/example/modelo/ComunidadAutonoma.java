package org.example.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComunidadAutonoma {
    private String codigo;
    private String nombre;
    private String abreviatura;
    private Capital capital;
    private int habitantes;
    private int extension;
    public static ComunidadAutonoma fromDocumentToComunidadAutonoma(Document document)
    {
        return new ComunidadAutonoma(document.getString("codigo"),
                document.getString("nombre"),
                document.getString("abreviatura"),
                Capital.fromDocumentToCapital(document.get("capital", Document.class)),
                document.getInteger("habitantes"),
                document.getInteger("extension")
        );
    }
    public static Document fromComunidadAutonomaToDocument(ComunidadAutonoma comunidadAutonoma)
    {
        return new Document("codigo", comunidadAutonoma.getCodigo())
                .append("nombre", comunidadAutonoma.getNombre())
                .append("abreviatura", comunidadAutonoma.getAbreviatura())
                .append("capital", Capital.fromCapitalToDocument(comunidadAutonoma.getCapital()))
                .append("habitantes", comunidadAutonoma.getHabitantes())
                .append("extension",comunidadAutonoma.getExtension());
    }
}
