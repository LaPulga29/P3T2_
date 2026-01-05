package Abstractas.Negocio;
import java.util.ArrayList;

public abstract class Proveedor {
    private String nombre;
    private String pais;
    private ArrayList<Contrato> contratos;

    public Proveedor(String nombre, String pais) {
        this.nombre = nombre;
        this.pais = pais;
        this.contratos = new ArrayList<>();
    }

    public String getNombre() { return nombre; }
    public String getPais() { return pais; }
    public ArrayList<Contrato> getContratos() { return contratos; }

    public void agregarContrato(Contrato c) {
        contratos.add(c);
    }

    public abstract String getTipoProveedor();

    @Override
    public String toString() {
        return "Proveedor{" +
                "contratos=" + contratos +
                ", nombre='" + nombre + '\'' +
                ", pais='" + pais + '\'' +
                '}';
    }
}
