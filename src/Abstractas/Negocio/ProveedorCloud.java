package Abstractas.Negocio;

public class ProveedorCloud extends Proveedor {

    public ProveedorCloud(String nombre, String pais) {
        super(nombre, pais);
    }

    @Override
    public String getTipoProveedor() {
        return "Proveedor Cloud";
    }
}

