package model;

import lombok.*;
import org.bson.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Tema {
    private String titulo;
    private int horas;

    public static Tema fromDocToTema (Document doc){
        return new Tema(doc.getString("titulo"), doc.getInteger("horas"));
    }

    public Document fromTemaToDoc (){
        return new Document("titulo", titulo).append("horas", horas);
    }
}
