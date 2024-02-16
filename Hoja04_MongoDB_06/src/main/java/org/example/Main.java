package org.example;

import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.json.JsonWriterSettings;
import org.example.modelo.Capital;
import org.example.modelo.ComunidadAutonoma;
import org.example.repository.impl.CCAARepositoryImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
        CCAARepositoryImpl repository=new CCAARepositoryImpl();
        ComunidadAutonoma comunidadAutonoma=new ComunidadAutonoma();
        Capital capital=new Capital();
        int menu=elegirOpcion();
        while (menu!=0){
            switch (menu){
                case 1 -> {
                    StringBuilder sb=new StringBuilder("[");
                    MongoCursor<Document> cursor=repository.obtenerNombreProvinciaYCapital();
                    iterar(sb, cursor);
                }
                case 2 -> {
                    System.out.println("Introduce los habitantes mínimos");
                    int habitantesMin=sc.nextInt();
                    System.out.println("Introduce los habitantes máximos");
                    int habitantesMax=sc.nextInt();
                    StringBuilder sb=new StringBuilder("[");
                    MongoCursor<Document> cursor=repository.obtenerNombresYHabitantesEntreHabitantes(habitantesMin,habitantesMax);
                    iterar(sb, cursor);
                }
                case 3 -> {
                    StringBuilder sb=new StringBuilder("[");
                    MongoCursor<Document> cursor=repository.obtenerCCAAUniprovinciales();
                    iterar(sb, cursor);
                }
                case 4 -> {
                    System.out.println("Introduce los habitantes mínimos");
                    int habitantesMin=sc.nextInt();
                    List<ComunidadAutonoma> comunidadAutonomas=repository.obtenerNombreCapitalPorHabitantes2(habitantesMin);
                    for (ComunidadAutonoma ccaa : comunidadAutonomas) {
                        System.out.println(ccaa.getCapital().getNombre());
                    }
                }
                case 5 -> {
                    StringBuilder sb=new StringBuilder("[");
                    MongoCursor<Document> cursor=repository.obtenerCCAASinFechaEstatuto();
                    iterar(sb, cursor);
                }
                case 6 -> {
                    System.out.println("Introduce el código");
                    String codigo= sc.next();
                    StringBuilder sb=new StringBuilder("[");
                    MongoCursor<Document> cursor=repository.obtenerProvincias(codigo);
                    iterar(sb, cursor);
                }
                case 7 -> {
                    repository.crearJSON();
                }
            }
            menu=elegirOpcion();
        }
    }

    private static void iterar(StringBuilder sb, MongoCursor<Document> cursor) {
        while (cursor.hasNext())
        {
            Document doc = cursor.next();
            //Configurador de salida JSON para que haya indentaciones
            JsonWriterSettings configSalidaJson = JsonWriterSettings.builder().indent(true).build();
            sb.append(doc.toJson(configSalidaJson));
            //Quitar la , del último elemento
            if (cursor.hasNext())
                sb.append(",\n");
            else
                sb.append("\n");
        }
        sb.append("]");
        System.out.println(sb);
    }

    public static int elegirOpcion(){
        int opcion=0;
        do {
            System.out.println("Introduce una opción:\n1 Obtener CCAA y capitales\n2. Obtener CCAA entre habitantes\n3.Obtener CCAA uniprovinciales\n4. Obtener capitales de CCAA con más habitantes que\n5. Obtener CCAA sin fecha estatuto\n6. Obtener provincia CCAA\n7. Crear fichero JSON");
            opcion= sc.nextInt();
        }while (opcion<0||opcion>9);
        return opcion;
    }
}