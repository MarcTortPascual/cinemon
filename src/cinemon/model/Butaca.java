package cinemon.model;

public class Butaca {
    private int id;
    private TipoButaca tipo;
    private boolean reservado;
    private boolean accesible;
    public Butaca(int id, TipoButaca tipo, boolean reservado, boolean accesible) {
        this.id = id;
        this.tipo = tipo;
        this.reservado = reservado;
        this.accesible = accesible;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public TipoButaca getTipo() {
        return tipo;
    }
    public void setTipo(TipoButaca tipo) {
        this.tipo = tipo;
    }
    public boolean isReservado() {
        return reservado;
    }
    public void setReservado(boolean reservado) {
        this.reservado = reservado;
    }
    public boolean isAccesible() {
        return accesible;
    }
    public void setAccesible(boolean accesible) {
        this.accesible = accesible;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\t Id: "+id+"\n");
        sb.append("\t Tipo: " + tipo+"\n");
        sb.append("\t Reservado: " + (reservado?"Si":"No") +"\n");
        sb.append("\t Accesible: " + (accesible?"Si":"No") +"\n");
        return sb.toString();
    }
}
