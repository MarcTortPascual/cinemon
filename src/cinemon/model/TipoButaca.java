package cinemon.model;

public enum TipoButaca {
    standar(0),
    vip (4),
    delux (3),
    reclinableExtra(2.5);
   
    
    double precio;

    private TipoButaca(double precio) {
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    

}
