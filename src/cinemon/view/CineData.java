package cinemon.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import cinemon.controler.console.CineControler;
import cinemon.model.*;
import cinemon.model.exceptions.BadsessiondayException;
import cinemon.model.exceptions.DoubleReserveException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CineData {
    
    public static ArrayList<Session> sessiones = new ArrayList<>();
    public static ArrayList<Sala> salas = new ArrayList<>();
    public static ArrayList<Cine> cine = new ArrayList<>();
    public static ArrayList<ArrayList<Butaca>> butacas = new ArrayList<>();

    public CineData() {
        String [] titulos_peliculas = {
            "Toy story",
            "forest gump",
            "Inventado 1",
            "Inventado 2",
            "Inventado 3",
            "Inventado 4",
            "Inventado 5",
            "Inventado 6",
            "Inventado 7",
            "Inventado 8",
            "Inventado 9",
            "Inventado 10",
            "Inventado 11",
            "Inventado 12",
            "Inventado 13",
            "Inventado 14",
            "Inventado 15",
            "Inventado 16",
            "Inventado 17",
            "Inventado 18",
            "Inventado 19",
            "Inventado 20",
            "Inventado 21",
            "Inventado 22",
            "Inventado 23",
            "Inventado 24",
            
            
          
        };

        
        for (int i= 0 ;  i <= 10; i++ ){
            ArrayList<Butaca> e = new ArrayList<Butaca>();
            
            for (int j= 0 ;  j <= 10; j++ ){
                e.add(new Butaca(
                         Integer.parseInt(Integer.toString(i) + Integer.toString(j)),
                        TipoButaca.standar,
                        false,
                        true
                                ));
                
            }
            butacas.add(e);
        }
        
        for (int i= 0 ;  i <=10; i++ ){
            
            butacas = new ArrayList<>();
            for (int i2= 0 ;  i2 <= 10; i2++ ){
            ArrayList<Butaca> e = new ArrayList<Butaca>();
            
            for (int j= 0 ;  j <= 10; j++ ){
                e.add(new Butaca(
                         Integer.parseInt(Integer.toString(i2) + Integer.toString(j)),
                        TipoButaca.standar,
                        false,
                        true
                                ));
                
            }
            butacas.add(e);
        }
            
            
            
            try {
                sessiones.add(
                        new Session(
                                i,
                                LocalDate.now(),
                                LocalDate.now(),
                                new Proyecion(
                                        i,
                                        i*i,
                                        titulos_peliculas[(i+5)%(titulos_peliculas.length-1)],
                                        "Jordi Conill",
                                        titulos_peliculas[(i+i)%(titulos_peliculas.length-1)],
                                        "Jesus",
                                        "",
                                        "Terror"
                                        
                                ),
                                LocalDate.now(),
                                butacas)
                );
            } catch (BadsessiondayException ex) {
                System.out.println("Mal dia");
            }
        }
        for (int i= 0 ;  i <=10; i++ ){
            salas.add(new Sala(i,TipoSala.values()[i%(TipoSala.values().length)],sessiones,Math.pow((double)i, (double)i)));
        }
        
    }
}
