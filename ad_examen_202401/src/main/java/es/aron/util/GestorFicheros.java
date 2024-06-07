package es.aron.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import es.aron.model.Comentario;
import es.aron.model.Receta;
import es.aron.repository.RecetaRepository;
import es.aron.repository.impl.RecetaRepositoryImpl;
import org.checkerframework.checker.units.qual.C;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GestorFicheros {
    public GestorFicheros() {
    }
    public List<Comentario> leerFicheroComentario(Path f){
        List<Comentario> comentarios=new ArrayList<>();
        RecetaRepositoryImpl recetaRepository=new RecetaRepositoryImpl();
        try (BufferedReader lector= Files.newBufferedReader(f)){
            String linea=lector.readLine();
            while (linea!=null){
                String[] l=linea.split(";");
                Receta receta= recetaRepository.getReceta(Integer.parseInt(l[0]));
                String titulo=l[1];
                String texto=l[2];
                Comentario comentario=new Comentario(receta,titulo,texto);
                if (!comentarios.contains(comentario)) comentarios.add(comentario);
                linea=lector.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return comentarios;
    }
    public void guardarIngredientes(List<String> ingredientes, int numeroReceta){
        RecetaRepositoryImpl recetaRepository=new RecetaRepositoryImpl();
        File f=new File("receta"+numeroReceta+".json");
        try (BufferedWriter escritor=Files.newBufferedWriter(f.toPath())){
            Gson gson=new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(ingredientes,escritor);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
