package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BinarySearchTreeTest
{
    private BinarySearchTree<Integer> bst;

    @BeforeEach
    void setUp()
    {
        bst = new BinarySearchTree<>();
    }

    @Test
    void testInsertAndSearch()
    {
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);

        assertEquals(10, bst.search(10));
        assertEquals(5, bst.search(5));
        assertEquals(15, bst.search(15));
        assertNull(bst.search(20));  // No existe en el árbol
    }

    @Test
    void testIsEmpty()
    {
        assertTrue(bst.isEmpty());
        bst.insert(1);
        assertFalse(bst.isEmpty());
    }

    @Test
    void testInsertDuplicate()
    {
        bst.insert(10);
        bst.insert(10);  // No debería afectar el árbol
        assertEquals(10, bst.search(10));
    }

    @Test
    void testSearchEmptyTree()
    {
        assertNull(bst.search(10));
    }
}