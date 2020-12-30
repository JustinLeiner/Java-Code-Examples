/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maze_DS;

import DataStructures.EmptyCollectionException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author justinleiner
 */
public class ArrayListStackTest {

    /**
     * Creates a new test case.
     */
    public ArrayListStackTest() {
    }

    /**
     * Test of push method, of class ArrayListStack.
     */
    @Test
    public void testPush() {
        // Informs the user which method is being tested
        System.out.println("testing push method");

        // Declares the ArrayListStack that will be used to test this method
        ArrayListStack <String> testStack = new ArrayListStack <String> ();

        // Ensures stack is empty
        assertTrue(testStack.isEmpty());

        // Ensures pushing elements onto the stack increases its size
        testStack.push("Pizza");
        assertEquals(testStack.size(), 1);

        /**
         * Ensures elements are pushed onto the top of the stack and ensures
         * elements are in the correct order on the stack
         */
        testStack.push("Chocolate");
        try {
            assertEquals(testStack.peek(), "Chocolate");
        } catch (EmptyCollectionException ex) {
            assertEquals("EmptyCollectionException", ex.getMessage());
        }
        testStack.push("Fries");
        try {
            assertEquals(testStack.peek(), "Fries");
        } catch (EmptyCollectionException ex) {
            assertEquals("EmptyCollectionException", ex.getMessage());
        }

    }

    /**
     * Test of pop method, of class ArrayListStack.
     *
     * @throws EmptyCollectionException occurs if the ArrayListStack is popped
     * and is empty
     */
    @Test
    public void testPop() throws Exception {
        // Informs the user which method is being tested
        System.out.println("testing pop method");

        // Declares the ArrayListStack that will be used to test this method
        ArrayListStack <String> testStack = new ArrayListStack <String> ();

        // Ensures the stack size decrements by 1 after popping from the stack
        testStack.push("Cheez-its");
        testStack.pop();
        assertEquals(testStack.size(), 0);

        // Ensures popping returns the element at the top of the stack
        testStack.push("Goldfish");
        assertEquals(testStack.pop(), "Goldfish");

        // Ensures popping mutates the stack by removing the top element
        testStack.push("Saltines");
        testStack.push("Pretzels");
        assertEquals(testStack.pop(), "Pretzels");

        // Ensures popping from an empty stack throws an 
        // EmptyCollectionException
        // Empties stack
        testStack.pop();
        try {
            testStack.pop();
            fail("*** Error in testPop - shouldn't get here");
        } catch (EmptyCollectionException e) {
            assertEquals("The stack is empty.", e.getMessage());
        }
    }

    /**
     * Test of peek method, of class ArrayListStack.
     *     
     * @throws EmptyCollectionException occurs if the ArrayListStack is peeked
     * and is empty
     */
    @Test
    public void testPeek() throws Exception {
        // Informs user which method is being tested
        System.out.println("testing peek method");

        // Declares the ArrayListStack that will be used to test this method
        ArrayListStack <String> testStack = new ArrayListStack <String> ();

        // Ensures peek does not change the state of the stack
        testStack.push("Laffy Taffy");
        testStack.push("Jolly Ranchers");
        testStack.push("Starburst");
        int size = testStack.size();
        System.out.println(testStack.toString());
        testStack.peek();
        assertEquals(testStack.size(), 3);
        assertEquals(testStack.pop(), "Starburst");
        assertEquals(testStack.pop(), "Jolly Ranchers");
        assertEquals(testStack.pop(), "Laffy Taffy");

        // Ensures peek returns the element at the top of the stack
        testStack.push("Gummy bears");
        assertEquals(testStack.peek(), "Gummy bears");

        // Ensures an exception is thrown when the stack is empty
        // Empties stack
        testStack.pop();
        try {
            testStack.peek();
        } catch (EmptyCollectionException e) {
            System.out.println(e.getMessage());
            assertEquals("The stack is empty.", e.getMessage());
        }
    }

    /**
     * Test of isEmpty method, of class ArrayListStack.
     */
    @Test
    public void testIsEmpty() {
        // Informs user which method is being tested
        System.out.println("testing the isEmpty method");

        // Declares the ArrayListStack that will be used to test this method
        ArrayListStack testStack = new ArrayListStack();

        // Ensures the ArrayListStack is empty
        assertTrue(testStack.isEmpty());
    }

    /**
     * Test of size method, of class ArrayListStack.
     */
    @Test
    public void testSize() {
        // Informs user which method is being tested
        System.out.println("testing the size method");

        // Declares the ArrayListStack that will be used to test this method
        ArrayListStack testStack = new ArrayListStack();

        // Ensures the size is correct
        assertEquals(testStack.size(), 0);
        testStack.push("Test element");
        assertEquals(testStack.size(), 1);
    }

}
