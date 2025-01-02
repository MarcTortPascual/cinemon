package cinemon.controler.console;
import java.util.ArrayList;
import java.util.Scanner;

import cinemon.model.*;
public class CineControler {



    public static Cine newCine (int id, Scanner scanner){
        System.out.println("Nombre del cine:");
        String nombre = scanner.next();
        ArrayList<String> direciones = new ArrayList<>();
        System.out.println("Pais del cine:");
        direciones.add(scanner.next());
        System.out.println("Provincia del cine:");
        direciones.add(scanner.next());
        System.out.println("Municipio del cine:");
        direciones.add(scanner.next());
        System.out.println("Calle del cine:");
        direciones.add(scanner.next());
        System.out.println("Numero del cine:");
        direciones.add(scanner.next());

        return new Cine(id, nombre, direciones, null, null);

    }

    

    public static void addRooms(Cine cine, Scanner scanner){
        ArrayList<Sala> salas2 = new ArrayList<>();
        boolean mas_salas = true;
        while (mas_salas) {
            System.out.println("Ahora vamos a crear una sala: ");
            System.out.println("Selecione el tipo de sala: ");
            for (int i = 0 ; i < TipoSala.values().length; i++){
                System.out.println(i+1 + "." + TipoSala.values()[i]);
            }
            int tipoVal = Integer.parseInt(scanner.next())-1;
            TipoSala tipoSala = TipoSala.values()[tipoVal];
            System.out.println("Numero de filas: ");
            int filas = Integer.parseInt(scanner.next())-1;
            System.out.println("Numero de columnas: ");
            int columnas = Integer.parseInt(scanner.next())-1;

            ArrayList<ArrayList<Butaca>> butacas = new ArrayList<>();
            for (int fila = 1; fila <= (filas+1) ; fila++){
                ArrayList<Butaca> butacas2 = new ArrayList<>();
                
                for (int columna = 1; columna <= (columnas+1) ; columna ++){
                    String id = Integer.toString(fila) + Integer.toString(columna);
                    System.out.println("Creando butaca con el id: "+ id);
                    butacas2.add(new Butaca(Integer.parseInt(id), TipoButaca.values()[tipoVal], false, false));
                }
                butacas.add(butacas2);
            }
            int idsala = 0;
            ArrayList<Sala> salasActuales = cine.getSalas();
            if (salasActuales != null){
                idsala = salasActuales.size();
            }
           
            Sala sala = new Sala(salas2.size() + idsala, tipoSala, null, tipoSala.getPrecio());
            addSessions(sala,scanner,butacas);
            salas2.add(sala);
            System.out.println("Desea añadir otra sala [s/n]");
            mas_salas = scanner.next().toLowerCase().equals("s");
        }
        ArrayList<Sala> salasActuales = cine.getSalas();
        if (salasActuales != null){
            salas2.addAll(salasActuales);
        }
        
        cine.setSalas(salas2);
    }



    public static void addSessions(Sala sala , Scanner scanner,ArrayList<ArrayList<Butaca>> butacas){
        ArrayList<Session> sessiones  = new ArrayList<>();
        System.out.println("Ahora vamos a añadir las sessiones de la sala con sus respectiva pelicula / serie: ");
        boolean mas_sessiones = true;
        while (mas_sessiones) {
            Proyecion pelicula;
            
            System.out.println("Fecha en formato dd/mm/aaaa :");
            String fecha = scanner.next();
            System.out.println("Hora de inicio en formato HH:MM :");
            String hora_inicio = fecha+scanner.next();
            System.out.println("Hora de final en formato HH:MM :");
            String hora_final = fecha+scanner.next();
           
            
            System.out.println("Ahora indique los datos de la pelicula/serie: ");
            System.out.println("Es una serie [s/n]: ");
            boolean is_serie = scanner.next().toLowerCase().equals("s");
            System.out.println("Duración: ");
            int duracion = Integer.parseInt(scanner.next());
            System.out.println("Titulo ");
            String titulo = scanner.next();
            System.out.println("Director");
            String director = scanner.next();
            System.out.println("Sipnosis");
            String sipnosis = scanner.next();
            System.out.println("Tema");
            String tema = scanner.next();
            System.out.println("Recomenciones");
            String recomendaciones = scanner.next();
            System.out.println("Genero");
            String genero = scanner.next();


            if (is_serie){
                System.out.println("Temporadas: ");
                int temporadas =  Integer.parseInt(scanner.next());
                System.out.println("Episodio: ");
                int episodios = Integer.parseInt(scanner.next());
                pelicula = new Serie(
                    sessiones.size(), duracion,titulo,director,
                    sipnosis,tema,recomendaciones,genero,temporadas,
                    episodios
                );
            }else{
                pelicula =   new Proyecion(sessiones.size(), duracion,titulo,director,
                sipnosis,tema,recomendaciones,genero);
            }
            int idsesion= 0 ;
            ArrayList<Session> sessionesActuales = sala.getSessiones();
            if (sessionesActuales != null){
                idsesion = sessionesActuales.size();
            }
            sessiones.add(new Session(sessiones.size() + idsesion, hora_inicio, hora_final, pelicula, fecha, new ArrayList<ArrayList<Butaca>>(butacas)));
            System.out.println("Desea añadir otra session [s/n]");
            mas_sessiones = scanner.next().toLowerCase().equals("s");
        }
        ArrayList<Session> sessionesActuales = sala.getSessiones();
        if (sessionesActuales != null){
            sessiones.addAll(sessionesActuales);
        }
        sala.setSessiones(sessiones);
    }

    public static void addProducts(int id,TipoProducto ta , Scanner scanner,ArrayList<Productos> productos){
        boolean mas_productos = true;
        while (mas_productos) {
            System.out.println("Ahora vamos a añadir productos a la tienda: ");
            
            switch (ta) {
                case SOUVENIR:
                    System.out.println("Nombre del souvenir: ");
                    String souvenir_nombre = scanner.next();
                    System.out.println("Precio del souvenir: ");
                    double souvenir_precio = Double.parseDouble(scanner.next());
                    System.out.println("Stock del souvenir: ");
                    int souvenir_stock =Integer.parseInt(scanner.next());
                    System.out.println("Tipo del souvenir: ");
                    for (int i = 0 ; i < TipoSouvenir.values().length; i++){
                        System.out.println(i+1 + "." + TipoSouvenir.values()[i]);
                    }
                    TipoSouvenir souvenir_tipo = TipoSouvenir.values()[Integer.parseInt(scanner.next())-1];

                    System.out.println("Edicion limitada [s/n]: ");
                    Boolean souvenir_limitado = scanner.next().toLowerCase().equals("s");
                    System.out.println("Franquicia del souvenir: ");
                    String souvenir_franqucia = scanner.next();
                    Souvenir souvenir = new Souvenir(id,souvenir_nombre, souvenir_precio,
                    souvenir_stock, souvenir_tipo,souvenir_limitado, souvenir_franqucia);
                    productos.add(souvenir);
                    
                    break;
                case ALIMENTO:
                    System.out.println("Nombre del alimento: ");
                    String alimento_nombre = scanner.next();
                    System.out.println("Precio del alimento: ");
                    double alimento_precio = Double.parseDouble(scanner.next());
                    System.out.println("Stock del alimento: ");
                    int alimento_stock =Integer.parseInt(scanner.next());
                    System.out.println("Tipo del alimento: ");
                    for (int i = 0 ; i < TipoAlimento.values().length; i++){
                        System.out.println(i+1 + "." + TipoAlimento.values()[i]);
                    }
                    TipoAlimento alimento_tipo = TipoAlimento.values()[Integer.parseInt(scanner.next())-1];

                    System.out.println("Tamaño del alimento: ");
                    for (int i = 0 ; i < Tamanio.values().length; i++){
                        System.out.println(i+1 + "." + Tamanio.values()[i]);
                    }
                    Tamanio alimento_tamanio = Tamanio.values()[Integer.parseInt(scanner.next())-1];    
                    System.out.println("Vegano[s/n]");
                    boolean vegan = scanner.next().toLowerCase().equals("s");

                    ArrayList<Alergeno> alergenos = new ArrayList<>();
                    System.out.println("¿Tiene alergenos? [s/n]: ");
                    boolean mas_alergenos = scanner.next().toLowerCase().equals("s");
                    while (mas_alergenos) {
                        System.out.println("Alergeno: ");
                        for (int i = 0 ; i < Alergeno.values().length; i++){
                            System.out.println(i+1 + "." + Alergeno.values()[i]);
                        }
                        Alergeno alimento_Alergeno = Alergeno.values()[Integer.parseInt(scanner.next())-1];   
                        alergenos.add(alimento_Alergeno);
                        System.out.println("Desea añadir otro alergeno [s/n]");
                        mas_alergenos = scanner.next().toLowerCase().equals("s");
                    }

                    Alimento alimento = new Alimento(id+productos.size(),alimento_nombre, alimento_precio, alimento_stock, alimento_tipo, 
                    alimento_tamanio, vegan, alergenos);
                    productos.add(alimento);
                    break;
                default:
                    break;
                
            }
            System.out.println("Desea añadir otro producto [s/n]");
            mas_productos = scanner.next().toLowerCase().equals("s"); 
            
        }
    }

    public static void addShops(Cine cine , Scanner scanner){
        boolean mas_tiendas = true;
        ArrayList<Tienda> tiendas = new ArrayList<>();
        while (mas_tiendas) {
            ArrayList<Productos> productos = new ArrayList<>();
            System.out.println("Ahora vamos a crear las tiendas");
            System.out.println("Tipo de articulos a vender: ");
            System.out.println("1.Souvenirs");
            System.out.println("2.Alimentos");
            TipoProducto ta = TipoProducto.values()[Integer.parseInt(scanner.next())-1];
           
            int idtienda = 0;
            ArrayList<Tienda> tiendasActuales =  cine.getTiendas();
            if (tiendasActuales != null){
                idtienda = tiendasActuales.size();
            }
            int idprod = productos.size();
            if (tiendasActuales != null){
                idprod = tiendasActuales.size();
            }
            addProducts(idprod,ta, scanner, productos);
            tiendas.add(new Tienda(tiendas.size()+idtienda, productos,ta));
            System.out.println("Desea añadir otra tienda [s/n]");
            mas_tiendas = scanner.next().toLowerCase().equals("s");
            
        }
        ArrayList<Tienda> tiendasActuales =  cine.getTiendas();
        if (tiendasActuales != null){
            tiendas.addAll(tiendasActuales);
        }
        cine.setTiendas(tiendas);
    }
}
