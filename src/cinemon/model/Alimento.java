package cinemon.model;

import java.util.ArrayList;

public class Alimento extends Productos  {
    
    private TipoAlimento tipo;
    private Tamanio tamanio;
    private boolean veganFrendly;
    private ArrayList<Alergeno> alergenos;
    public Alimento(int id,String nombre, double precio, int stock, TipoAlimento tipo, Tamanio tamanio, boolean veganFrendly,
            ArrayList<Alergeno> alergenos) {
        super(id,nombre, precio, stock);
        this.tipo = tipo;
        this.tamanio = tamanio;
        this.veganFrendly = veganFrendly;
        this.alergenos = alergenos;
    }
    public TipoAlimento getTipo() {
        return tipo;
    }
    public void setTipo(TipoAlimento tipo) {
        this.tipo = tipo;
    }
    public Tamanio getTamanio() {
        return tamanio;
    }
    public void setTamanio(Tamanio tamanio) {
        this.tamanio = tamanio;
    }
    public boolean isVeganFrendly() {
        return veganFrendly;
    }
    public void setVeganFrendly(boolean veganFrendly) {
        this.veganFrendly = veganFrendly;
    }
    public ArrayList<Alergeno> getAlergenos() {
        return alergenos;
    }
    public void setAlergenos(ArrayList<Alergeno> alergenos) {
        this.alergenos = alergenos;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString());
        sb.append("\t tipo:  " + tipo + "\n");
        sb.append("\t tamaño: " + tamanio.toString() + "\n");
        sb.append("\t Vegano: " + (veganFrendly? "Si":"No") + "\n");
        sb.append("\t Alergenos: \n");
        for (Alergeno a : alergenos){
            sb.append("\t\t"+ a.toString() + "\n");
        }

        return sb.toString();

    }
    
}
