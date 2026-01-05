package Abstractas.Negocio;

public class ProveedorPasarelasPago extends Proveedor {

    public ProveedorPasarelasPago(String nombre, String pais) {
        super(nombre, pais);
    }

    @Override
    public String getTipoProveedor() {
        return "Proveedor de Pasarelas de Pago";
    }
}


