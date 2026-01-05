package Abstractas.Negocio;
import java.util.ArrayList;

public class ClienteEmpresa {
    private String nombre;
    private ArrayList<Proveedor> proveedores = new ArrayList<>();

    public ClienteEmpresa(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void contratarProveedor(Proveedor p) {
        proveedores.add(p);
    }

    public ArrayList<Proveedor> getProveedores() {
        return proveedores;
    }

    public boolean tieneProveedorTipo(String tipo) {
        for (Proveedor p : proveedores) {
            if (p.getTipoProveedor().equalsIgnoreCase(tipo)) {
                return true;
            }
        }
        return false;
    }

    public void listarContratosActivos() {
        System.out.println("Contratos activos del cliente " + nombre + ":");
        boolean tiene = false;
        for (Proveedor p : proveedores) {
            for (Contrato c : p.getContratos()) {
                if (c.estaActivo() && c.getCliente() == this) {
                    System.out.println("- Proveedor: " + p.getNombre() +
                            " | Precio: " + c.getPrecio() + " USD" +
                            " | Duración: " + c.getDuracionMeses() + " meses");
                    tiene = true;
                }
            }
        }
        if (!tiene) {
            System.out.println(" El cliente no tiene contratos activos.");
        }
    }
}
