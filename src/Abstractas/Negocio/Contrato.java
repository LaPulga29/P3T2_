package Abstractas.Negocio;
public class Contrato {
    private double precio;
    private int duracionMeses;
    private ClienteEmpresa cliente;
    public Contrato(double precio, int duracionMeses, ClienteEmpresa cliente) {
        this.precio = precio;
        this.duracionMeses = duracionMeses;
        this.cliente = cliente;
    }
    public double getPrecio() { return precio; }
    public int getDuracionMeses() { return duracionMeses; }
    public ClienteEmpresa getCliente() { return cliente; }
    public boolean estaActivo() {
        return duracionMeses > 0;
    }
    @Override
    public String toString() {
        return "Contrato{" +
                "precio=" + precio +
                ", duracionMeses=" + duracionMeses +
                ", cliente=" + cliente.getNombre() +
                '}';
    }
}
