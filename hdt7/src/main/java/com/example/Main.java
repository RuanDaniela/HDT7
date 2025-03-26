package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String filePath = "/Users/danielaruano/Desktop/HDT7/productos.csv";
        BinarySearchTree<Product> bst = CSVLoader.loadProducts(filePath);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Ingrese SKU a buscar (o 'exit' para salir): ");
            String sku = scanner.nextLine().trim(); // Quitar espacios extra

            if (sku.equalsIgnoreCase("exit")) break;

            Product found = bst.search(new Product(sku, 0, 0, "", ""));

            if (found != null) {
                System.out.println("Producto encontrado: " + found);
            } else {
                System.out.println("Producto no encontrado.");
            }
        }
        scanner.close();
    }
}
