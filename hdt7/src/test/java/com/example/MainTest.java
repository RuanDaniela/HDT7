package com.example;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MainTest {
    private BinarySearchTree<Product> bst;

    @BeforeEach
    void setUp() {
        bst = new BinarySearchTree<>();
        bst.insert(new Product("123ABC", 100.0, 80.0, "Laptop", "Electronics"));
        bst.insert(new Product("456DEF", 200.0, 150.0, "Phone", "Electronics"));
    }

    @Test
    void testSearchProductFound() {
        String simulatedInput = "123ABC\nexit\n";
        InputStream originalSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        Scanner scanner = new Scanner(System.in);
        Main.searchProduct(bst, scanner);
        scanner.close();

        System.setIn(originalSystemIn); // Restaurar entrada estándar
    }

    @Test
    void testSearchProductNotFound() {
        String simulatedInput = "999XYZ\nexit\n";
        InputStream originalSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        Scanner scanner = new Scanner(System.in);
        Main.searchProduct(bst, scanner);
        scanner.close();

        System.setIn(originalSystemIn); // Restaurar entrada estándar
    }
}
