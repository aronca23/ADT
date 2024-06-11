package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bson.Document;
import org.bson.json.JsonObject;
import org.example.model.Capital;
import org.example.model.ComunidadAutonoma;
import org.example.repository.impl.CCAARepositoryImpl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
        int menu=elegirOpcion();
        CCAARepositoryImpl comunidadAutonomaRepositori=new CCAARepositoryImpl();
        while (menu!=0){
            switch (menu) {
                case 1 -> {
                    List<ComunidadAutonoma> ccaas=comunidadAutonomaRepositori.getCCAAYCapitales();
                    for (ComunidadAutonoma ccaa:ccaas) {
                        if (ccaa.getCapital()!=null) System.out.println("CCAA: "+ccaa.getNombre()+" - Capital: "+ccaa.getCapital().getNombre());
                        else System.out.println("CCAA: "+ccaa.getNombre());
                    }
                }
                case 2 -> {
                    System.out.println("Introduce los habitantes  mínimos");
                    int min=sc.nextInt();
                    System.out.println("Introduce los habitantes  máximos");
                    int max=sc.nextInt();
                    List<ComunidadAutonoma> ccaas=comunidadAutonomaRepositori.getCCAAHabitantesEntre(min,max);
                    for (ComunidadAutonoma ccaa:ccaas) {
                        if (ccaa.getCapital()!=null) System.out.println("Capital: "+ccaa.getCapital().getNombre()+" - "+ccaa.getCapital().getNumHabitantes());
                        else System.out.println("Nada");
                    }
                }
                case 3 -> {
                    List<ComunidadAutonoma> ccaas=comunidadAutonomaRepositori.getCCAAUniprovinciales();
                    for (ComunidadAutonoma ccaa:ccaas) {
                        System.out.println(ccaa.getNombre());
                    }
                }
                case 4 -> {
                    System.out.println("Introduce el nº de habitantes");
                    int habitantes=sc.nextInt();
                    List<ComunidadAutonoma> ccaas=comunidadAutonomaRepositori.getCapitalesMasHabitantes(habitantes);
                    for (ComunidadAutonoma ccaa:ccaas) {
                        System.out.println(ccaa.getCapital().getNombre());
                    }
                }
                case 5 -> {
                    List<ComunidadAutonoma> ccaas=comunidadAutonomaRepositori.getCCAASinFechaEstatuto();
                    for (ComunidadAutonoma ccaa:ccaas) {
                        System.out.println(ccaa.getNombre());
                    }
                }
                case 6 -> {
                    System.out.println("Introduce el código de la CCAA");
                    String codigo=sc.next();
                    ComunidadAutonoma ccaa=comunidadAutonomaRepositori.getProvinciasCCAA(codigo);
                    System.out.println(ccaa.getProvincias());
                }
                case 7 -> {
                    System.out.println("Introduce la ruta del fichero");
                    String fichero=sc.next();
                    File file=new File(fichero);
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    List<Document> documents=comunidadAutonomaRepositori.getCCAA();
                    try (BufferedWriter escritor= Files.newBufferedWriter(file.toPath())){
                        for (Document document:documents) {
                            JsonObject jsonObject = new JsonObject(document.toJson());
                            gson.toJson(jsonObject,escritor );
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            menu=elegirOpcion();
        }
    }
    private static int elegirOpcion(){
        int opcion=0;
        do {
            System.out.println("Elige una opción:\n1. Obtener CCAA y capitales\n2. Obtener CCAA con habitantes comprendidos entre valores\n3. Obtener CCAA uniprovinciales\n4. Obtener capitales de CCAA con más habitantes que\n5.Obtener CCAA sin fecha de estatuto\n6. Obtener provincias de CCAA\n7. Crear fichero JSON\n0. Salir");
            opcion=sc.nextInt();
        }while (opcion<0||opcion>9);
        return opcion;
    }
}