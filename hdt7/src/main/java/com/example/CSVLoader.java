package com.example;

import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class CSVLoader {
    public static BinarySearchTree<Product> loadProducts(String filePath) {
        BinarySearchTree<Product> bst = new BinarySearchTree<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] nextLine;

            reader.readNext(); // Ignorar la primera línea (encabezado)

            while ((nextLine = reader.readNext()) != null) {
                if (nextLine.length < 19 || nextLine[7].isEmpty() || nextLine[10].isEmpty()) {
                    continue; // Ignorar filas sin SKU o sin precios
                }

                try {
                    String sku = nextLine[7].trim(); // Columna SKU, eliminar espacios
                    double priceRetail = Double.parseDouble(nextLine[10]);
                    double priceCurrent = Double.parseDouble(nextLine[11]);
                    String productName = nextLine[18]; // Nombre del producto
                    String category = nextLine[0]; // Categoría

                    Product product = new Product(sku, priceRetail, priceCurrent, productName, category);
                    bst.insert(product);
                } catch (NumberFormatException e) {
                    // Ignorar errores de conversión sin imprimir nada
                }
            }
        } catch (IOException | CsvValidationException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        }

        return bst;
    }
}
