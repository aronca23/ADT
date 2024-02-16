package org.example.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Capital {
    private String nombre;
    private int habitantes;
    public static Capital fromDocumentToCapital(Document document)
    {
        return new Capital(document.getString("nombre"),
                document.getInteger("habitantes")
        );
    }
    public static Document fromCapitalToDocument(Capital capital)
    {
        return new Document("nombre", capital.getNombre())
                .append("habitantes", capital.getHabitantes());
    }
}
