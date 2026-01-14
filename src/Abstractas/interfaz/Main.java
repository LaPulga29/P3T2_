package Abstractas.interfaz;
import Abstractas.Util.Utilitario;
import Abstractas.Negocio.*;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        Utilitario util = new Utilitario();
        ArrayList<Proveedor> proveedores = new ArrayList<>();
        ArrayList<ClienteEmpresa> clientes = new ArrayList<>();
        java.util.Scanner sc = new java.util.Scanner(System.in);
        int opc;
        do {
            util.menu();
            opc = Integer.parseInt(sc.nextLine());
            switch (opc) {
                case 1:
                    try {
                        Proveedor p = util.crearProveedor();
                        if (p != null) {
                            proveedores.add(p);
                            System.out.println("Proveedor creado con éxito");
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        ClienteEmpresa c = util.crearCliente();
                        clientes.add(c);
                        System.out.println("Cliente creado con éxito");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    if (proveedores.isEmpty() || clientes.isEmpty()) {
                        System.out.println("Error: Debe haber al menos un proveedor y un cliente registrados");
                        break;
                    }

                    try {
                        System.out.println("\n--- Asociar proveedor a cliente ---");

                        // Seleccionar cliente
                        System.out.println("Seleccione el cliente:");
                        int idxCliente = util.seleccionarCliente(clientes);
                        ClienteEmpresa cliente = clientes.get(idxCliente);

                        // Seleccionar proveedor (todos los disponibles)
                        System.out.println("Seleccione el proveedor a asociar:");
                        int idxProveedor = util.seleccionarProveedor(proveedores);
                        Proveedor proveedor = proveedores.get(idxProveedor);

                        // Verificar si ya están asociados
                        if (cliente.getProveedores().contains(proveedor)) {
                            System.out.println("Error: Este proveedor ya está asociado a este cliente");
                        } else {
                            cliente.contratarProveedor(proveedor);
                            System.out.println("Proveedor asociado al cliente con éxito");
                        }
                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Error inesperado: " + e.getMessage());
                    }
                    break;

                case 4:
                    // Validar que haya clientes
                    if (clientes.isEmpty()) {
                        System.out.println("Error: No hay clientes registrados");
                        break;
                    }

                    try {
                        // 1. Seleccionar cliente
                        System.out.println("\n--- Seleccionar cliente ---");
                        int idxCliente = util.seleccionarCliente(clientes);
                        ClienteEmpresa clienteSeleccionado = clientes.get(idxCliente);

                        // 2. Verificar que el cliente tenga proveedores
                        if (clienteSeleccionado.getProveedores().isEmpty()) {
                            System.out.println("Error: Este cliente no tiene proveedores asociados. " +
                                    "Primero debe asociar un proveedor (opción 3)");
                            break;
                        }

                        // 3. Seleccionar proveedor (solo los de este cliente)
                        int idxProveedorEnCliente = util.seleccionarProveedorCliente(clienteSeleccionado);
                        Proveedor proveedorSeleccionado = clienteSeleccionado.getProveedores().get(idxProveedorEnCliente);

                        // 4. Crear contrato
                        util.crearContrato(proveedorSeleccionado, clienteSeleccionado);

                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Error inesperado: " + e.getMessage());
                        e.printStackTrace(); // Para depuración
                    }
                    break;

                case 5:
                    if (clientes.isEmpty()) {
                        System.out.println("Error: No hay clientes registrados");
                        break;
                    }

                    try {
                        int idxCliente = util.seleccionarCliente(clientes);
                        System.out.print("Tipo de proveedor a verificar: ");
                        String tipo = sc.nextLine();

                        boolean tiene = clientes.get(idxCliente).tieneProveedorTipo(tipo);
                        System.out.println(tiene ? "El cliente tiene ese tipo de proveedor" :
                                "El cliente NO tiene ese tipo de proveedor");
                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Error inesperado: " + e.getMessage());
                    }
                    break;

                case 6:
                    if (clientes.isEmpty()) {
                        System.out.println("Error: No hay clientes registrados");
                        break;
                    }

                    try {
                        int idxCliente = util.seleccionarCliente(clientes);
                        clientes.get(idxCliente).listarContratosActivos();
                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Error inesperado: " + e.getMessage());
                    }
                    break;

                default:
                    System.out.println("Opción no válida");
            }
        } while (opc != 7);
    }
}
