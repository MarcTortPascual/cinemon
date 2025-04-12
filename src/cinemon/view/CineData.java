package cinemon.view;

import cinemon.model.ButacaDeserializer;
import java.util.ArrayList;

import cinemon.model.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONObject;


public class CineData {
    

    public static ArrayList<Sala> salas = new ArrayList<>();
     public static ArrayList<Sala> convertirJsonASalas(String jsonString) {
        ArrayList<Sala> salas = new ArrayList<>();
        JSONArray salasJson = new JSONArray(jsonString);

        for (int i = 0; i < salasJson.length(); i++) {
            JSONObject salaJson = salasJson.getJSONObject(i);

            // Obtener atributos básicos de la sala
            int id = salaJson.getInt("id");
            String tipoSala = salaJson.getString("tipoSala");
            double precio = salaJson.getDouble("precio");

            // Obtener las sesiones
            ArrayList<Session> sessiones = new ArrayList<>();
            JSONArray sessionesJson = salaJson.getJSONArray("sessiones");

            for (int j = 0; j < sessionesJson.length(); j++) {
                JSONObject sesionJson = sessionesJson.getJSONObject(j);

                // Obtener atributos de la sesión
                int sesionId = sesionJson.getInt("id");
                String horaInicio = sesionJson.getString("horaInicio");
                String horaFinal = sesionJson.getString("horaFinal");
                String fecha = sesionJson.getString("fecha");

                // Obtener las butacas de la sesión
                ArrayList<Butaca> _butacas = new ArrayList<>();
                JSONArray butacasJson = sesionJson.getJSONArray("butacas");

                for (int k = 0; k < butacasJson.length(); k++) {
                    JSONObject butacaJson = butacasJson.getJSONObject(k);
                    int butacaId = butacaJson.getInt("id");
                    String tipo = butacaJson.getString("tipo");
                    boolean reservado = butacaJson.getBoolean("reservado");
                    boolean accesible = butacaJson.getBoolean("accesible");

                    // Crear objeto Butaca
                    Butaca butaca = new Butaca(butacaId, TipoButaca.valueOf(tipo), reservado, accesible);
                    _butacas.add(butaca);
                }

                // Obtener la película de la sesión
                JSONObject peliculaJson = sesionJson.getJSONObject("pelicula");
                Proyecion pelicula = new Proyecion(
                        peliculaJson.getInt("id"),
                        peliculaJson.getInt("duracion"),
                        peliculaJson.getString("titulo"),
                        peliculaJson.getString("director"),
                        peliculaJson.getString("sipnosis"),
                        peliculaJson.getString("tema"),
                        peliculaJson.getString("recomendaciones"),
                        peliculaJson.getString("genero")
                );

                // Crear objeto Sesion y agregarlo a la lista de sesiones
                ArrayList<ArrayList<Butaca>> buts = new ArrayList<>();
                buts.add(_butacas);
                Session sesion = new Session(sesionId, horaInicio, horaFinal, pelicula,fecha,buts);
                sessiones.add(sesion);
            }

            // Crear objeto Sala y agregarlo a la lista de salas
            Sala sala = new Sala(id,TipoSala.valueOf(tipoSala), sessiones, precio);
            salas.add(sala);
        }
        return salas;
    }

    public CineData() {
      
        try{
            URL url = new URL("http://localhost:8080/sala");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            InputStream is = con.getInputStream();
            String _json = new String(is.readAllBytes(), StandardCharsets.UTF_8);

             
            salas = convertirJsonASalas(_json);

        }catch(IOException e){
               
            System.out.println(e.getMessage());
        }

    }   
}
