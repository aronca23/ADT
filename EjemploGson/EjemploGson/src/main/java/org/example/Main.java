package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Main {
    public static void main(String[] args)
    {
        try {
            BufferedReader lector =
                    Files.newBufferedReader(Path.of("agenda.json"));
            Gson gson = new Gson();
            Contacto[] contactos = gson.fromJson(lector, Contacto[].class);
            Arrays.stream(contactos)
                    .forEach(System.out::println);

            lector.close();
        }
        catch (IOException e) {
            System.err.println("Error de E/S");
            System.err.println(e);
        }
    }
    public static void escribirGson(File f){
        try (BufferedWriter escritor=Files.newBufferedWriter(f.toPath())){
            Gson gson=new GsonBuilder().setPrettyPrinting().create();
            Contacto contacto=new Contacto("a",111,"a@a.com",null);
            gson.toJson(contacto,escritor);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}