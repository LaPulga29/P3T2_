package Abstractas.Util;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import Abstractas.Negocio.*;

public class Utilitario {
    private Scanner sc = new Scanner(System.in);

    public Proveedor crearProveedor() {
        System.out.println("-----TIPOS DE PROVEEDORES-----");
        System.out.println("1. Cloud");
        System.out.println("2. SaaS");
        System.out.println("3. Pasarela de Pago");
        System.out.println("Seleccione tipo de proveedor:");

        int op = 0;
        while (true) {
            try {
                String input = sc.nextLine();
                op = Integer.parseInt(input);

                if (op < 1 || op > 3) {
                    throw new IllegalArgumentException("Error: Debe seleccionar una opción entre 1 y 3");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido (1, 2 o 3)");
                System.out.print("Seleccione tipo de proveedor: ");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.print("Seleccione tipo de proveedor: ");
            }
        }

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        while (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("Error: El nombre no puede estar vacío");
            System.out.print("Nombre: ");
            nombre = sc.nextLine();
        }

        System.out.print("País: ");
        String pais = sc.nextLine();

        while (pais == null || pais.trim().isEmpty()) {
            System.out.println("Error: El país no puede estar vacío");
            System.out.print("País: ");
            pais = sc.nextLine();
        }

        switch (op) {
            case 1: return new ProveedorCloud(nombre, pais);
            case 2: return new ProveedorSaaS(nombre, pais);
            case 3: return new ProveedorPasarelasPago(nombre, pais);
            default: return null;
        }
    }

    public ClienteEmpresa crearCliente() {
        System.out.print("Nombre del cliente: ");
        String nombre = sc.nextLine();

        while (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("Error: El nombre no puede estar vacío");
            System.out.print("Nombre del cliente: ");
            nombre = sc.nextLine();
        }

        return new ClienteEmpresa(nombre);
    }

    public void crearContrato(Proveedor p, ClienteEmpresa c) {
        double precio = 0;
        int meses = 0;

        // Validar precio
        while (true) {
            System.out.print("Precio: ");
            try {
                String input = sc.nextLine();
                precio = Double.parseDouble(input);

                if (precio <= 0) {
                    throw new IllegalArgumentException("Error: El precio debe ser mayor a 0");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido para el precio");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // Validar duración
        while (true) {
            System.out.print("Duración (meses): ");
            try {
                String input = sc.nextLine();
                meses = Integer.parseInt(input);

                if (meses <= 0) {
                    throw new IllegalArgumentException("Error: La duración debe ser mayor a 0");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido para la duración");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        Contrato contrato = new Contrato(precio, meses, c);
        p.agregarContrato(contrato);
        System.out.println("Contrato creado entre el proveedor " + p.getNombre() +
                " y el cliente " + c.getNombre());
    }

    public void listarProveedores(ArrayList<Proveedor> proveedores) {
        System.out.println("\n--- Proveedores registrados ---");
        if (proveedores.isEmpty()) {
            System.out.println("No hay proveedores registrados");
            return;
        }

        for (int i = 0; i < proveedores.size(); i++) {
            System.out.println((i+1) + ". " + proveedores.get(i).getNombre() +
                    " (" + proveedores.get(i).getTipoProveedor() + ")");
        }
    }

    public void listarClientes(ArrayList<ClienteEmpresa> clientes) {
        System.out.println("\n--- Clientes registrados ---");
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados");
            return;
        }

        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i+1) + ". " + clientes.get(i).getNombre());
        }
    }

    public int seleccionarProveedor(ArrayList<Proveedor> proveedores) {
        if (proveedores.isEmpty()) {
            throw new IllegalStateException("Error: No hay proveedores registrados");
        }

        listarProveedores(proveedores);
        System.out.print("Seleccione un proveedor (número): ");

        while (true) {
            try {
                String input = sc.nextLine();
                int seleccion = Integer.parseInt(input);

                if (seleccion < 1 || seleccion > proveedores.size()) {
                    throw new IllegalArgumentException("Error: Debe seleccionar un número entre 1 y " + proveedores.size());
                }
                return seleccion - 1; // Retornar índice base 0
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido");
                System.out.print("Seleccione un proveedor (número): ");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.print("Seleccione un proveedor (número): ");
            }
        }
    }

    public int seleccionarCliente(ArrayList<ClienteEmpresa> clientes) {
        if (clientes.isEmpty()) {
            throw new IllegalStateException("Error: No hay clientes registrados");
        }

        listarClientes(clientes);
        System.out.print("Seleccione un cliente (número): ");

        while (true) {
            try {
                String input = sc.nextLine();
                int seleccion = Integer.parseInt(input);

                if (seleccion < 1 || seleccion > clientes.size()) {
                    throw new IllegalArgumentException("Error: Debe seleccionar un número entre 1 y " + clientes.size());
                }
                return seleccion - 1; // Retornar índice base 0
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido");
                System.out.print("Seleccione un cliente (número): ");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.print("Seleccione un cliente (número): ");
            }
        }
    }

    public int seleccionarProveedorCliente(ClienteEmpresa cliente) {
        ArrayList<Proveedor> proveedoresCliente = cliente.getProveedores();

        if (proveedoresCliente.isEmpty()) {
            throw new IllegalStateException("Error: Este cliente no tiene proveedores asociados");
        }

        System.out.println("\n--- Proveedores de " + cliente.getNombre() + " ---");
        for (int i = 0; i < proveedoresCliente.size(); i++) {
            System.out.println((i+1) + ". " + proveedoresCliente.get(i).getNombre() +
                    " (" + proveedoresCliente.get(i).getTipoProveedor() + ")");
        }

        System.out.print("Seleccione un proveedor (número): ");

        while (true) {
            try {
                String input = sc.nextLine();
                int seleccion = Integer.parseInt(input);

                if (seleccion < 1 || seleccion > proveedoresCliente.size()) {
                    throw new IllegalArgumentException("Error: Debe seleccionar un número entre 1 y " + proveedoresCliente.size());
                }
                return seleccion - 1; // Retornar índice base 0
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido");
                System.out.print("Seleccione un proveedor (número): ");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.print("Seleccione un proveedor (número): ");
            }
        }
    }

    public void menu() {
        System.out.println("\n--------MENU SISTEMA---------");
        System.out.println("1. Crear proveedor");
        System.out.println("2. Crear cliente");
        System.out.println("3. Asociar proveedor a cliente");
        System.out.println("4. Crear contratos entre proveedor y cliente");
        System.out.println("5. Verificar tipo de proveedor en cliente");
        System.out.println("6. Listar contratos activos de un cliente");
        System.out.println("7. Salir");
        System.out.print("Seleccione una opción: ");
    }
}