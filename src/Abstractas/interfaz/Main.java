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
                case 1: {
                    proveedores.add(util.crearProveedor());
                } break;

                case 2: {
                    clientes.add(util.crearCliente());
                } break;

                case 3: { // Asociar proveedor a cliente
                    if (clientes.isEmpty() || proveedores.isEmpty()) {
                        System.out.println("Debe haber al menos un cliente y un proveedor registrados.");
                    } else {
                        util.listarClientes(clientes);
                        System.out.print("Seleccione cliente: ");
                        int ic = sc.nextInt();
                        sc.nextLine();

                        util.listarProveedores(proveedores);
                        System.out.print("Seleccione proveedor: ");
                        int ip = sc.nextInt();
                        sc.nextLine();

                        if (ic >= 1 && ic <= clientes.size() && ip >= 1 && ip <= proveedores.size()) {
                            clientes.get(ic - 1).contratarProveedor(proveedores.get(ip - 1));
                            System.out.println("Proveedor asociado al cliente correctamente.");
                        } else {
                            System.out.println("Índice inválido.");
                        }
                    }
                } break;

                case 4: { // Crear contrato con proveedor asociado a un cliente
                    if (clientes.isEmpty()) {
                        System.out.println("No hay clientes registrados.");
                    } else {
                        util.listarClientes(clientes);
                        System.out.print("Seleccione cliente: ");
                        int ic = sc.nextInt();
                        sc.nextLine();

                        if (ic >= 1 && ic <= clientes.size()) {
                            ClienteEmpresa cliente = clientes.get(ic - 1);

                            if (cliente.getProveedores().isEmpty()) {
                                System.out.println("El cliente seleccionado no tiene proveedores asociados. Use la opción 3 primero.");
                            } else {
                                // Mostrar solo los proveedores asociados a ese cliente
                                System.out.println("\n--- Proveedores asociados al cliente " + cliente.getNombre() + " ---");
                                ArrayList<Proveedor> asociados = cliente.getProveedores();
                                for (int i = 0; i < asociados.size(); i++) {
                                    System.out.println((i+1) + ". " + asociados.get(i).getNombre() + " (" + asociados.get(i).getTipoProveedor() + ")");
                                }

                                System.out.print("Seleccione proveedor asociado: ");
                                int ip = sc.nextInt();
                                sc.nextLine();

                                if (ip >= 1 && ip <= asociados.size()) {
                                    Proveedor proveedor = asociados.get(ip - 1);
                                    util.crearContrato(proveedor, cliente); // contrato entre cliente y proveedor asociado
                                } else {
                                    System.out.println("Índice inválido.");
                                }
                            }
                        } else {
                            System.out.println("Índice inválido.");
                        }
                    }
                } break;



                case 5: {
                    if (clientes.isEmpty()) {
                        System.out.println("No hay clientes registrados.");
                    } else {
                        util.listarClientes(clientes);
                        System.out.print("Seleccione cliente: ");
                        int ic2 = sc.nextInt();
                        sc.nextLine();
                        if (ic2 >= 1 && ic2 <= clientes.size()) {
                            System.out.print("Tipo a buscar: ");
                            String tipo = sc.nextLine();
                            if (clientes.get(ic2 - 1).tieneProveedorTipo(tipo)) {
                                System.out.println("El cliente tiene contratado un proveedor de tipo: " + tipo);
                            } else {
                                System.out.println("El cliente NO tiene contratado un proveedor de tipo: " + tipo);
                            }
                        } else {
                            System.out.println("Índice inválido.");
                        }
                    }
                } break;

                case 6: {
                    if (clientes.isEmpty()) {
                        System.out.println("No hay clientes registrados.");
                    } else {
                        util.listarClientes(clientes);
                        System.out.print("Seleccione cliente: ");
                        int ic3 = sc.nextInt();
                        sc.nextLine();
                        if (ic3 >= 1 && ic3 <= clientes.size()) {
                            clientes.get(ic3 - 1).listarContratosActivos();
                        } else {
                            System.out.println("Índice inválido.");
                        }
                    }
                } break;

                case 7: {
                    System.out.println("Gracias por usar el programa");
                } break;

                default:
                    System.out.println("Opción no válida");
            }
        } while (opc != 7);
    }
}
