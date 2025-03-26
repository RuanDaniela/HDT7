package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    void testProductCreation() {
        Product product = new Product("123ABC", 100.0, 80.0, "Laptop", "Electronics");

        assertEquals("123ABC", product.getSku());
        assertEquals(100.0, product.getPriceRetail());
        assertEquals(80.0, product.getPriceCurrent());
        assertEquals("Laptop", product.getProductName());
        assertEquals("Electronics", product.getCategory());
    }

    @Test
    void testCompareTo() {
        Product p1 = new Product("123ABC", 100.0, 80.0, "Laptop", "Electronics");
        Product p2 = new Product("456DEF", 200.0, 150.0, "Phone", "Electronics");
        Product p3 = new Product("123ABC", 120.0, 90.0, "Tablet", "Gadgets");

        assertTrue(p1.compareTo(p2) < 0);  // "123ABC" < "456DEF"
        assertTrue(p2.compareTo(p1) > 0);  // "456DEF" > "123ABC"
        assertEquals(0, p1.compareTo(p3)); // Mismo SKU, debe ser igual
    }

    @Test
    void testToString() {
        Product product = new Product("123ABC", 100.0, 80.0, "Laptop", "Electronics");
        String expected = "SKU: 123ABC, Nombre: Laptop, Precio Actual: 80.0, Precio Retail: 100.0, Categoría: Electronics";
        assertEquals(expected, product.toString());
    }
}
