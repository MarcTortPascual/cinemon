package cinemon.controler.console;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import cinemon.model.*;
import cinemon.model.exceptions.BadsessiondayException;

public class CineControler {
    private static boolean novalido = true;
    // Función que crea un nuevo cine con un ID dado y detalles proporcionados por el usuario.

    public static Cine newCine (int id, Scanner scanner){
        System.out.println("Nombre del cine:");
        String nombre = scanner.next(); // Se pide el nombre del cine.
        ArrayList<String> direciones = new ArrayList<>(); // Lista para almacenar la dirección.

        // Se solicita la dirección por partes (país, provincia, municipio, calle, número).
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

        // Retorna una nueva instancia de Cine con los datos ingresados.
        return new Cine(id, nombre, direciones, null, null);
    }

    // Función para añadir salas al cine.
    public static void addRooms(Cine cine, Scanner scanner){
        ArrayList<Sala> salas2 = new ArrayList<>();
        boolean mas_salas = true; // Flag para agregar múltiples salas.

        while (mas_salas) {
            System.out.println("Ahora vamos a crear una sala: ");
            System.out.println("Selecione el tipo de sala: ");
            for (int i = 0 ; i < TipoSala.values().length; i++){
                // Muestra los tipos de sala disponibles.
                System.out.println(i+1 + "." + TipoSala.values()[i]);
            }

            // Se selecciona el tipo de sala.
            int tipoVal = 0;
            TipoSala tipoSala = TipoSala.values()[0];
            novalido = true;
            while (novalido) {
                try{
                    tipoVal = Integer.parseInt(scanner.next())-1;
                    tipoSala = TipoSala.values()[tipoVal];
                    novalido = false;
                }catch(NumberFormatException | IndexOutOfBoundsException e){
                    System.out.println("Pon un numero en el rango [0-"+(TipoSala.values().length+1) +"]");
                }
            }
            
            // Se solicitan las dimensiones de la sala (filas y columnas).
            int filas = 0;
            int columnas = 0;
           novalido = true;
            while (novalido) {
                try{
                    System.out.println("Numero de filas: ");
                    filas = Integer.parseInt(scanner.next())-1;
                    novalido = false;
                }catch(NumberFormatException e ){
                    System.out.println("Pon un numero");
                }
            }

           novalido = true;
            while (novalido) {
                try{
                    System.out.println("Numero de columnas: ");
                    columnas = Integer.parseInt(scanner.next())-1;
                    novalido = false;
                }catch(NumberFormatException e ){
                    System.out.println("Pon un numero");
                }
            }
            

            // Crea una matriz de butacas para la sala.
            ArrayList<ArrayList<Butaca>> butacas = new ArrayList<>();
            for (int fila = 1; fila <= (filas+1) ; fila++){
                ArrayList<Butaca> butacas2 = new ArrayList<>();
                for (int columna = 1; columna <= (columnas+1) ; columna ++){
                    // Genera un ID único para cada butaca.
                    String id = Integer.toString(fila) + Integer.toString(columna);
                    System.out.println("Creando butaca con el id: "+ id);
                    // Se crea cada butaca con un estado inicial.
                    butacas2.add(new Butaca(Integer.parseInt(id), TipoButaca.values()[tipoVal], false, false));
                }
                butacas.add(butacas2);
            }

            // Calcula el ID de la sala y la crea.
            int idsala = 0;
            ArrayList<Sala> salasActuales = cine.getSalas();
            if (salasActuales != null){
                idsala = salasActuales.size();
            }
            Sala sala = new Sala(salas2.size() + idsala, tipoSala, null, tipoSala.getPrecio());
            // Añade sesiones a la sala.
            addSessions(sala,scanner,butacas);
            salas2.add(sala);

            // Pregunta si se desea añadir otra sala.
            System.out.println("Desea añadir otra sala [s/n]");
            mas_salas = scanner.next().toLowerCase().equals("s");
        }

        // Agrega las nuevas salas a las ya existentes en el cine.
        ArrayList<Sala> salasActuales = cine.getSalas();
        if (salasActuales != null){
            salas2.addAll(salasActuales);
        }
        cine.setSalas(salas2);
    }

    // Función para añadir sesiones a una sala.
    public static void addSessions(Sala sala , Scanner scanner, ArrayList<ArrayList<Butaca>> butacas){
        ArrayList<Session> sessiones  = new ArrayList<>();
        System.out.println("Ahora vamos a añadir las sessiones de la sala con su respectiva pelicula / serie: ");
        boolean mas_sessiones = true;

        while (mas_sessiones) {
            Proyecion pelicula;

            // Solicita la fecha y las horas de inicio y finalización de la sesión.
            String fecha = "";
           novalido = true;
            while (novalido) {
                try{
                    System.out.println("Fecha en formato dd/mm/aaaa :");
                    fecha = scanner.next();
                    LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    novalido = false;
                }catch(DateTimeParseException e){
                    System.out.println("Formato de fecha Ilegal");
                }
            }
            String hora_inicio = "";
           novalido = true;
            while (novalido) {
                try{
                    System.out.println("Hora de inicio en formato HH:MM :");
                    hora_inicio = fecha + scanner.next();
                    LocalDate.parse(hora_inicio, DateTimeFormatter.ofPattern("dd/MM/yyyyHH:mm"));
                    novalido = false;
                }catch(DateTimeParseException e){
                    System.out.println("Formato de fecha Ilegal");
                }
            }
            String hora_final = "";
           novalido = true;
            while (novalido) {
                try{
                    System.out.println("Hora de final en formato HH:MM :");
                    hora_final = fecha + scanner.next();
                    LocalDate.parse(hora_final, DateTimeFormatter.ofPattern("dd/MM/yyyyHH:mm"));
                    novalido = false;
                }catch(DateTimeParseException e){
                    System.out.println("Formato de fecha Ilegal");
                }
            }
          
            // Recoge los detalles de la película o serie.
            System.out.println("Es una serie [s/n]: ");
            boolean is_serie = scanner.next().toLowerCase().equals("s");
            

            int duracion = 0;
           novalido = true;
            while (novalido) {
                try{
                    System.out.println("Duración: ");
                    duracion = Integer.parseInt(scanner.next());
                    novalido = false;
                }catch(NumberFormatException e ){
                    System.out.println("Pon un numero");
                }
            }

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
                // Si es una serie, solicita temporadas y episodios.

                int temporadas = 0;
               novalido = true;
                while (novalido) {
                    try{
                        System.out.println("Temporadas: ");
                        temporadas =  Integer.parseInt(scanner.next());
                        novalido = false;
                    }catch(NumberFormatException e ){
                        System.out.println("Pon un numero");
                    }
                }
              
                int episodios = 0;
               novalido = true;
                while (novalido) {
                    try{
                        System.out.println("Episodio: ");
                        episodios =  Integer.parseInt(scanner.next());
                        novalido = false;
                    }catch(NumberFormatException e ){
                        System.out.println("Pon un numero");
                    }
                }
                pelicula = new Serie(
                    sessiones.size(), duracion, titulo, director,
                    sipnosis, tema, recomendaciones, genero, temporadas,
                    episodios
                );
            } else {
                // Si no es una serie, se crea como una película normal.
                pelicula = new Proyecion(sessiones.size(), duracion, titulo, director,
                sipnosis, tema, recomendaciones, genero);
            }

            // Crea la sesión con los datos ingresados.
            int idsesion = 0;
            ArrayList<Session> sessionesActuales = sala.getSessiones();
            if (sessionesActuales != null){
                idsesion = sessionesActuales.size();
            }

           novalido = true;
            while (novalido) {
                try{
                    sessiones.add(new Session(sessiones.size() + idsesion, hora_inicio, hora_final, pelicula, fecha, new ArrayList<ArrayList<Butaca>>(butacas)));
                    novalido = false;
                }catch(BadsessiondayException e){
                   novalido = true;
                    while (novalido) {
                        try{
                            System.out.println("Hora de final en formato HH:MM :");
                            hora_final = fecha + scanner.next();
                            LocalDate.parse(fecha+hora_final, DateTimeFormatter.ofPattern("dd/MM/yyyyHH:mm"));
                            novalido = false;
                        }catch(DateTimeParseException e2){
                            System.out.println("Formato de fecha Ilegal");
                        }
                    }
                }
            }
            

            // Pregunta si se desea añadir otra sesión.
            System.out.println("Desea añadir otra session [s/n]");
            mas_sessiones = scanner.next().toLowerCase().equals("s");
        }

        // Agrega las nuevas sesiones a las ya existentes.
        ArrayList<Session> sessionesActuales = sala.getSessiones();
        if (sessionesActuales != null){
            sessiones.addAll(sessionesActuales);
        }
        sala.setSessiones(sessiones);
    }

    // Función para añadir productos a una tienda.
    public static void addProducts(int id, TipoProducto ta , Scanner scanner, ArrayList<Productos> productos){
        boolean mas_productos = true;

        while (mas_productos) {
            System.out.println("Ahora vamos a añadir productos a la tienda: ");

            switch (ta) {
                case SOUVENIR:
                    // Agregar un souvenir con sus detalles.
                    System.out.println("Nombre del souvenir: ");
                    String souvenir_nombre = scanner.next();

                    double souvenir_precio = 0;
                   novalido = true;
                    while (novalido) {
                        try{
                            System.out.println("Precio del souvenir: ");
                            souvenir_precio = Double.parseDouble(scanner.next());
                            novalido = false;
                        }catch(NumberFormatException e ){
                            System.out.println("Pon un numero");
                        }
                    }

                    
                    int souvenir_stock = 0;

                   novalido = true;
                    while (novalido) {
                        try{
                            System.out.println("Stock del souvenir: ");
                            souvenir_stock = Integer.parseInt(scanner.next());
                            novalido = false;
                        }catch(NumberFormatException e ){
                            System.out.println("Pon un numero");
                        }
                    }
                    
                    
                    TipoSouvenir souvenir_tipo = TipoSouvenir.values()[0];
                   novalido = true;
                    while (novalido) {
                        try{
                            System.out.println("Tipo del souvenir: ");
                            for (int i = 0 ; i < TipoSouvenir.values().length; i++){
                                System.out.println(i+1 + "." + TipoSouvenir.values()[i]);
                            }
                            souvenir_tipo = TipoSouvenir.values()[Integer.parseInt(scanner.next())-1];
                            novalido = false;
                        }catch(NumberFormatException | IndexOutOfBoundsException e){
                            System.out.println("Pon un numero en el rango [0-"+(TipoSouvenir.values().length+1) +"]");
                        }
                    }
                    System.out.println("Edicion limitada [s/n]: ");
                    Boolean souvenir_limitado = scanner.next().toLowerCase().equals("s");
                    System.out.println("Franquicia del souvenir: ");
                    String souvenir_franqucia = scanner.next();
                    Souvenir souvenir = new Souvenir(id, souvenir_nombre, souvenir_precio,
                    souvenir_stock, souvenir_tipo, souvenir_limitado, souvenir_franqucia);
                    productos.add(souvenir);
                    break;

                case ALIMENTO:
                    // Agregar un alimento con sus detalles.
                    System.out.println("Nombre del alimento: ");
                    String alimento_nombre = scanner.next();
                    

                    double alimento_precio = 0;
                   novalido = true;
                    while (novalido) {
                        try{
                            System.out.println("Precio del alimento: ");
                            alimento_precio = Double.parseDouble(scanner.next());
                            novalido = false;
                        }catch(NumberFormatException e ){
                            System.out.println("Pon un numero");
                        }
                    }
                    
                    int alimento_stock = 0;
                   novalido = true;
                    while (novalido) {
                        try{
                            System.out.println("Stock del alimento: ");
                            alimento_stock = Integer.parseInt(scanner.next());
                            novalido = false;
                        }catch(NumberFormatException e ){
                            System.out.println("Pon un numero");
                        }
                    }
                    
                    TipoAlimento alimento_tipo = TipoAlimento.values()[0];
                   novalido = true;
                    while (novalido) {
                        try{
                            System.out.println("Tipo del alimento: ");
                            for (int i = 0 ; i < TipoAlimento.values().length; i++){
                                System.out.println(i+1 + "." + TipoAlimento.values()[i]);
                            }
                            alimento_tipo = TipoAlimento.values()[Integer.parseInt(scanner.next())-1];
                            novalido = false;
                        }catch(NumberFormatException | IndexOutOfBoundsException e){
                            System.out.println("Pon un numero en el rango [0-"+(TipoAlimento.values().length+1) +"]");
                        }
                    }

                    
                    Tamanio alimento_tamanio = Tamanio.values()[0];
                   novalido = true;
                    while (novalido) {
                        try{
                            System.out.println("Tamaño del alimento: ");
                            for (int i = 0 ; i < Tamanio.values().length; i++){
                                System.out.println(i+1 + "." + Tamanio.values()[i]);
                            }
                            alimento_tamanio = Tamanio.values()[Integer.parseInt(scanner.next())-1];
                            novalido = false;
                        }catch(NumberFormatException | IndexOutOfBoundsException e){
                            System.out.println("Pon un numero en el rango [0-"+(Tamanio.values().length+1) +"]");
                        }
                    }
                    
                    
                    
                    
                    System.out.println("Vegano[s/n]");
                    boolean vegan = scanner.next().toLowerCase().equals("s");

                    // Agregar alérgenos al alimento.
                    ArrayList<Alergeno> alergenos = new ArrayList<>();
                    System.out.println("¿Tiene alergenos? [s/n]: ");
                    boolean mas_alergenos = scanner.next().toLowerCase().equals("s");
                    while (mas_alergenos) {
                        Alergeno alimento_Alergeno = Alergeno.values()[0];
                       novalido = true;
                        while (novalido) {
                            try{
                                System.out.println("Alergeno: ");
                                for (int i = 0 ; i < Alergeno.values().length; i++){
                                    System.out.println(i+1 + "." + Alergeno.values()[i]);
                                }
                                alimento_Alergeno = Alergeno.values()[Integer.parseInt(scanner.next())-1];
                                alergenos.add(alimento_Alergeno);
                                novalido = false;
                            }catch(NumberFormatException | IndexOutOfBoundsException e){
                                System.out.println("Pon un numero en el rango [0-"+(Alergeno.values().length+1) +"]");
                            }
                        }
                    
                        System.out.println("Desea añadir otro alergeno [s/n]");
                        mas_alergenos = scanner.next().toLowerCase().equals("s");
                    }

                    Alimento alimento = new Alimento(id + productos.size(), alimento_nombre, alimento_precio, alimento_stock, alimento_tipo,
                    alimento_tamanio, vegan, alergenos);
                    productos.add(alimento);
                    break;

                default:
                    break;
            }

            // Pregunta si se desea añadir otro producto.
            System.out.println("Desea añadir otro producto [s/n]");
            mas_productos = scanner.next().toLowerCase().equals("s");
        }
    }

    // Función para añadir tiendas al cine.
    public static void addShops(Cine cine , Scanner scanner){
        boolean mas_tiendas = true;
        ArrayList<Tienda> tiendas = new ArrayList<>();

        while (mas_tiendas) {
            ArrayList<Productos> productos = new ArrayList<>();

            TipoProducto ta = TipoProducto.values()[0];
            novalido = true;
            while (novalido) {
                try{
                    System.out.println("Ahora vamos a crear las tiendas");
                    System.out.println("Tipo de articulos a vender: ");
                    System.out.println("1.Souvenirs");
                    System.out.println("2.Alimentos");
                    ta = TipoProducto.values()[Integer.parseInt(scanner.next())-1];
                    novalido = false;
                }catch(NumberFormatException | IndexOutOfBoundsException e){
                    System.out.println("Pon un numero en el rango [0-"+(TipoProducto.values().length+1) +"]");
                }
            }

            // Se determina el ID de la tienda.
            int idtienda = 0;
            ArrayList<Tienda> tiendasActuales = cine.getTiendas();
            if (tiendasActuales != null){
                idtienda = tiendasActuales.size();
            }
            int idprod = productos.size();
            if (tiendasActuales != null){
                idprod = tiendasActuales.size();
            }

            // Se agregan productos a la tienda.
            addProducts(idprod, ta, scanner, productos);
            tiendas.add(new Tienda(tiendas.size() + idtienda, productos, ta));

            // Pregunta si se desea añadir otra tienda.
            System.out.println("Desea añadir otra tienda [s/n]");
            mas_tiendas = scanner.next().toLowerCase().equals("s");
        }

        // Agrega las nuevas tiendas a las ya existentes.
        ArrayList<Tienda> tiendasActuales = cine.getTiendas();
        if (tiendasActuales != null){
            tiendas.addAll(tiendasActuales);
        }
        cine.setTiendas(tiendas);
    }
}