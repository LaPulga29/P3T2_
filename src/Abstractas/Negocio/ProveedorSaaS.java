package Abstractas.Negocio;
public class ProveedorSaaS extends Proveedor {
    public ProveedorSaaS(String nombre, String pais) {
        super(nombre, pais);
    }
    @Override
    public String getTipoProveedor() {
        return "Proveedor SaaS";
    }
}
