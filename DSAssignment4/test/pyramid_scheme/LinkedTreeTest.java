package pyramid_scheme;

import DataStructures.MultiTreeNode;
import Exceptions.ElementNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Solution Test file for LinkedTree
 *
 * @author Justin Leiner
 * @version 12/7/2020
 */
public class LinkedTreeTest {

    private LinkedTree<String> instance;
    private MultiTreeNode<String> root;
    private String s01;
    private String s02;
    private String s03;
    private String s04;
    private String s05;
    private String s06 = "Elem 6";
    private String s07 = "Elem 7";

    /**
     * Sets up the later tests.
     */
    @Before
    public void setUp() {
        s01 = "Elem 1";
        s02 = "Elem 2";
        s03 = "Elem 3";
        s04 = "Elem 4";
        s05 = "Elem 5";
        root = new MultiTreeNode<>(s01);
        instance = new LinkedTree<>(root);
    }

    /**
     * Test of getRootElement method, of class LinkedTree.
     */
    @Test
    public void testGetRootElement() {
        System.out.println("testGetRootElement");
        // Asserts the root element is s01 
        assertEquals(root.getElement(), s01);
        // Modifies root element and sees if its adjusted accordingly
        root.setElement(s05);
        assertEquals(root.getElement(), s05);
    }

    /**
     * Test of addChild method, of class LinkedTree.
     */
    @Test
    public void testAddChild() {
        System.out.println("testAddChild");
        // Ensures elements are properly added
        try {
            instance.addChild(s01, s04);
            assertTrue(instance.contains(s04));
        } catch (ElementNotFoundException ex) {
            System.out.println("ElementNotFoundException");
        }
        // Ensures size increments after an element is added
        int oldSize = instance.size();
        try {
            instance.addChild(s04, s03);
            assertEquals(instance.size(), oldSize + 1);
        } catch (ElementNotFoundException ex) {
            System.out.println("ElementNotFoundException");
        }
        // Ensures a child cannot be added using an illegitamte parent
        try{
            instance.addChild(s06, s07);
            assertFalse(instance.contains(s07));
        } catch (ElementNotFoundException ex) {
            System.out.println("ElementNotFoundException");
        }
        
    }

    /**
     * Test of findNode method, of class LinkedTree.
     */
    @Test
    public void testFindNode() {
        System.out.println("testFindNode");
        try {
            //Can find root
            assertEquals(s01, instance.findNode(s01).getElement());
            instance.addChild(s01, s02);
            //System.out.println("Successfully added " + s02 + " to " + s01);
            //Can find a child node
            assertEquals(s02, instance.findNode(s02).getElement());
            instance.addChild(s01, s03); //Add several deep
            instance.addChild(s02, s04);
            instance.addChild(s04, s05);
            //Can find a child node deep within tree
            assertEquals(s05, instance.findNode(s05).getElement());
            //Trying to find things not in the tree returns null
            assertEquals(null, instance.findNode("Unreal element"));
        } catch (Exception ex) {
            fail("Unexpected Exception");
        }
    }

    /**
     * Test of contains method, of class LinkedTree.
     */
    @Test
    public void testContains() {
        System.out.println("testContains");
        // Ensures elements can be found
        assertTrue(instance.contains(s01));
        // Ensures a newly added element can be found 
        try {
            instance.addChild(s01, s02);
            assertTrue(instance.contains(s02));
        } catch (ElementNotFoundException ex) {
            System.out.println("ElementNotFoundException");
        }
        // Ensures an element not in the tree cant be found
        assertFalse(instance.contains("Elem 7"));
    }

    /**
     * Test of size method, of class LinkedTree.
     */
    @Test
    public void testSize() {
        System.out.println("testSize");
        // Validates the size of the tree
        assertEquals(instance.size(), 1);
        // Ensures size is incremented after an element is added
        try {
            instance.addChild(s01, s02);
            assertEquals(instance.size(), 2);
        } catch (ElementNotFoundException ex) {
            System.out.println("ElementNotFoundException");
        }
        
    }

}
