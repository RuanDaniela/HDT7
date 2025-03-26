package com.example;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

public class CSVLoader {
    public static BinarySearchTree<Product> loadProducts(String filePath) {
        BinarySearchTree<Product> bst = new BinarySearchTree<>();

        try {
            // Crear un parser con el delimitador ';'
            CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
            // Crear el CSVReader con el parser
            CSVReader reader = new CSVReaderBuilder(new FileReader(filePath)).withCSVParser(parser).build();

            String[] nextLine;
            reader.readNext(); // Ignorar encabezado

            while ((nextLine = reader.readNext()) != null) {
                // 🔍 Imprimir la línea para ver su formato
                System.out.println("📄 Línea del CSV: " + Arrays.toString(nextLine));

                // Validar que la línea tenga los datos necesarios (al menos 5 columnas)
                if (nextLine.length < 5) {
                    System.out.println("⚠ Fila ignorada por tener menos de 5 columnas.");
                    continue;
                }

                try {
                    String category = nextLine[0].trim();
                    String sku = nextLine[1].trim();
                    String priceRetailStr = nextLine[2].trim();
                    String priceCurrentStr = nextLine[3].trim();
                    String productName = nextLine[4].trim();

                    // 🛠 Manejar precios vacíos o inválidos
                    double priceRetail = priceRetailStr.isEmpty() ? 0.0 : Double.parseDouble(priceRetailStr);
                    double priceCurrent = priceCurrentStr.isEmpty() ? 0.0 : Double.parseDouble(priceCurrentStr);

                    // Verificar que el SKU no esté vacío
                    if (sku.isEmpty()) {
                        System.out.println("⚠ Fila ignorada por SKU vacío.");
                        continue;
                    }

                    Product product = new Product(sku, priceRetail, priceCurrent, productName, category);
                    bst.insert(product);

                    // ✅ Confirmación de inserción
                    System.out.println("✅ Insertado en BST: " + product);
                } catch (NumberFormatException e) {
                    System.err.println("🚨 Error al convertir precios en línea: " + Arrays.toString(nextLine));
                }
            }
        } catch (IOException | CsvValidationException e) {
            System.err.println("❌ Error al leer el archivo CSV: " + e.getMessage());
        }

        return bst;
    }
}
