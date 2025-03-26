package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String filePath = "/Users/danielaruano/Desktop/HDT7/productos.csv";
        BinarySearchTree<Product> bst = CSVLoader.loadProducts(filePath);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("\n🔍 Ingrese SKU a buscar (o 'exit' para salir): ");
            String sku = scanner.nextLine().trim(); // Limpiar espacios extra

            if (sku.equalsIgnoreCase("exit")) break;

            System.out.println("📌 Buscando SKU: " + sku);

            Product found = bst.search(new Product(sku, 0, 0, "", ""));

            if (found != null) {
                System.out.println("\n✅ Producto encontrado:");
                System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
                System.out.println("📦 Nombre: " + found.getProductName());
                System.out.println("🏷️  SKU: " + found.getSku());
                System.out.println("🛒 Precio Actual: $" + found.getPriceCurrent());
                System.out.println("💰 Precio Retail: $" + found.getPriceRetail());
                System.out.println("📂 Categoría: " + found.getCategory());
                System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            } else {
                System.out.println("❌ Producto no encontrado. Intente con otro SKU.");
            }
        }
        scanner.close();
        System.out.println("👋 Programa finalizado.");
    }
}
