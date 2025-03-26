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

            // Verifica si el archivo tiene al menos una línea (posible encabezado)
            if ((nextLine = reader.readNext()) != null) {
                
                while ((nextLine = reader.readNext()) != null && nextLine.length > 0) {
                    if (nextLine.length < 5) {
                        System.err.println("Error: Fila con datos incompletos. Contenido: " + String.join(", ", nextLine));
                        continue;
                    }
                    
                    try {
                        Product product = new Product(
                            nextLine[0], 
                            Double.parseDouble(nextLine[1]),
                            Double.parseDouble(nextLine[2]), 
                            nextLine[3], 
                            nextLine[4]
                        );
                        bst.insert(product);
                    } catch (NumberFormatException e) {
                        System.err.println("Error de formato en números: " + String.join(", ", nextLine));
                    }
                }
            } else {
                System.err.println("Error: El archivo CSV está vacío o no tiene encabezado.");
            }
        } catch (IOException | CsvValidationException e) {  // Captura CsvValidationException aquí
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        }
        
        return bst;
    }
}
