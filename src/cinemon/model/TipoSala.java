package cinemon.model;

public enum TipoSala {
    STANDAR(7.0),
	DELUXE(8.5),
	_3D(10.0),
	IMERSIVA(12.0);
    double precio;

    private TipoSala(double precio) {
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
}
