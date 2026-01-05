package Abstractas.Util;

import java.util.ArrayList;
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
        int op = sc.nextInt(); sc.nextLine();

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("País: ");
        String pais = sc.nextLine();

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
        return new ClienteEmpresa(nombre);
    }

    public void crearContrato(Proveedor p, ClienteEmpresa c) {
        System.out.print("Precio: ");
        double precio = sc.nextDouble();

        System.out.print("Duración (meses): ");
        int meses = sc.nextInt();
        sc.nextLine();

        Contrato contrato = new Contrato(precio, meses, c);
        p.agregarContrato(contrato);

        System.out.println("Contrato creado entre el proveedor " + p.getNombre() +
                " y el cliente " + c.getNombre());
    }





    public void listarProveedores(ArrayList<Proveedor> proveedores) {
        System.out.println("\n--- Proveedores registrados ---");
        for (int i = 0; i < proveedores.size(); i++) {
            System.out.println((i+1) + ". " + proveedores.get(i).getNombre() + " (" + proveedores.get(i).getTipoProveedor() + ")");
        }
    }

    public void listarClientes(ArrayList<ClienteEmpresa> clientes) {
        System.out.println("\n--- Clientes registrados ---");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i+1) + ". " + clientes.get(i).getNombre());
        }
    }

    public  void menu(){
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
