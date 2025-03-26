package com.example;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.FileReader;
import java.io.StringReader;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

class CSVLoaderTest {
    private BinarySearchTree<Product> bst;

    @BeforeEach
    void setUp() {
        bst = new BinarySearchTree<>();
    }

    @Test
    void testLoadProductsValidCSV() throws Exception {
        String csvData = "Name,Price,Weight,Category,Brand\n" + 
                         "Laptop,1500.0,2.5,Electronics,Apple\n" + 
                         "Phone,800.0,0.5,Electronics,Samsung\n";

        CSVReader mockReader = new CSVReader(new StringReader(csvData));
        
        try (MockedStatic<CSVReader> mockedStatic = mockStatic(CSVReader.class)) {
            mockedStatic.when(() -> new CSVReader(any(FileReader.class))).thenReturn(mockReader);
            bst = CSVLoader.loadProducts("fake_path.csv");
        }

        assertNotNull(bst);
        assertFalse(bst.isEmpty());
        assertEquals("Laptop", bst.search(new Product("Laptop", 1500.0, 2.5, "Electronics", "Apple")).getName());
        assertEquals("Phone", bst.search(new Product("Phone", 800.0, 0.5, "Electronics", "Samsung")).getName());
    }

    @Test
    void testLoadProductsInvalidData() throws Exception {
        String csvData = "Name,Price,Weight,Category,Brand\n" + 
                         "Laptop,abc,2.5,Electronics,Apple\n" + 
                         "Phone,800.0,xyz,Electronics,Samsung\n";  

        CSVReader mockReader = new CSVReader(new StringReader(csvData));
        
        try (MockedStatic<CSVReader> mockedStatic = mockStatic(CSVReader.class)) {
            mockedStatic.when(() -> new CSVReader(any(FileReader.class))).thenReturn(mockReader);
            bst = CSVLoader.loadProducts("fake_path.csv");
        }

        assertTrue(bst.isEmpty());  // Todos los datos son inválidos
    }

    @Test
    void testLoadProductsEmptyFile() throws Exception {
        String csvData = "";

        CSVReader mockReader = new CSVReader(new StringReader(csvData));

        try (MockedStatic<CSVReader> mockedStatic = mockStatic(CSVReader.class)) {
            mockedStatic.when(() -> new CSVReader(any(FileReader.class))).thenReturn(mockReader);
            bst = CSVLoader.loadProducts("fake_path.csv");
        }

        assertTrue(bst.isEmpty()); // Debe ser vacío porque el CSV no tiene datos
    }
}
