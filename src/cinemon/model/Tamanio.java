package cinemon.model;

public enum Tamanio {
    Pequeño(0),
    Mediano(1.5),
    Grande(3);
    double precio;
    Tamanio(double precio){
        this.precio = precio;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
}
