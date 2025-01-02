package cinemon.model;

public class Souvenir extends Productos{
    private TipoSouvenir tipo;
    private boolean edicionLimitada;
    private String franquicia;
    public Souvenir(int id,String nombre, double precio, int stock, TipoSouvenir tipo, boolean edicionLimitada,
            String franquicia) {
        super(id,nombre, precio, stock);
        this.tipo = tipo;
        this.edicionLimitada = edicionLimitada;
        this.franquicia = franquicia;
    }
    public TipoSouvenir getTipo() {
        return tipo;
    }
    public void setTipo(TipoSouvenir tipo) {
        this.tipo = tipo;
    }
    public boolean isEdicionLimitada() {
        return edicionLimitada;
    }
    public void setEdicionLimitada(boolean edicionLimitada) {
        this.edicionLimitada = edicionLimitada;
    }
    public String getFranquicia() {
        return franquicia;
    }
    public void setFranquicia(String franquicia) {
        this.franquicia = franquicia;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString());
        sb.append("\t Tipo: "+ tipo.toString() + "\n");
        sb.append("\t EdicionLimitada: "+ (edicionLimitada?"Si":"No") + "\n");
        sb.append("\t Franquicia: "+ franquicia + "\n");

        return sb.toString();

    }

}
