package Shopping;

import DataStructures.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * @version Fall 2019
 * @author ITCS 2214
 */
public class ShoppingListArrayTest {

    private ShoppingListArray instance;

    /**
     * Initialize instance and entries.
     */
    @Before
    public void setupTestCases() {
        instance = new ShoppingListArray();
    }

    /**
     * Test of add method, of class ShoppingArray.
     */
    @Test
    public void testAdd() {
        Grocery entry1 = new Grocery("Mayo", "Dressing / Mayo", 1, 2.99f, 1);
        System.out.println(entry1.toString());
        // Initial state
        assertEquals(0, instance.size());
        assertFalse(instance.contains(entry1));

        instance.add(entry1);

        // Test general case (size)
        assertEquals(1, instance.size());

        // Test general case (content)
        assertTrue(instance.contains(entry1));

        // Test combined quantity case
        // Test that matching ignores letter case
//        int initialQuantity = entry1.getQuantity();
        int diff = 1;
//        int newQuantity = initialQuantity + diff;
        Grocery duplicate = new Grocery(entry1.getName().toLowerCase(),
                entry1.getCategory());
        duplicate.setQuantity(diff);
        instance.add(duplicate);
        System.out.println(instance.toString());
        // and ? do we test anything here?
    }

    /**
     * Test of add method expanding, of class ShoppingArray.
     */
    @Test
    public void testAddExpands() {
        Grocery entry1 = new Grocery("Mayo", "Dressing / Mayo", 1, 2.99f, 1);
        Grocery entry2 = new Grocery("Green Tea", "Tea", 6, 1.99f, 2);
        Grocery entry3 = new Grocery("Lucky Charms", "Cereal", 7, 3.99f, 1);
        Grocery entry4 = new Grocery("Admiral Ackbar Cereal", "Cereal", 7, 4.99f,
                1);
        Grocery entry5 = new Grocery("Tide Pods", "Laundry", 9, 1.99f, 4);
        Grocery entry6 = new Grocery("Spam", "Can Meat", 1, 2.99f, 3);

        // Initial state
        assertEquals(0, instance.size());
        assertFalse(instance.contains(entry1));

        instance.add(entry1);
        instance.add(entry2);
        instance.add(entry3);
        instance.add(entry4);
        instance.add(entry5);
        instance.add(entry6);

        // Test expanded capacity (size)
        assertEquals(6, instance.size());

        // Test expanded capacity (content)
        assertTrue(instance.contains(entry6));
    }

    /**
     * Test of remove method, of class ShoppingArray.
     */
    @Test
    public void testRemove() {
        Grocery entry1 = new Grocery("Mayo", "Dressing / Mayo", 1, 2.99f, 1);
        Grocery entry2 = new Grocery("Green Tea", "Tea", 6, 1.99f, 2);
        Grocery entry3 = new Grocery("Lucky Charms", "Cereal", 7, 3.99f, 1);

        // Test element not found case
        instance.remove(entry1);

        instance.add(entry1);
        assertEquals(1, instance.size());
        assertTrue(instance.contains(entry1));

        instance.remove(entry1);

        // Test general case (size)
        assertEquals(0, instance.size());

        // Test general case (content)
        assertFalse(instance.contains(entry1));

        instance.add(entry1);
        instance.add(entry2);
        instance.add(entry3);

        // Test remove shifts elements
        // Before shift
        try {
            assertTrue(instance.find(0).equals(entry1));
            assertTrue(instance.find(1).equals(entry2));
            assertTrue(instance.find(2).equals(entry3));
        } catch (EmptyCollectionException e) {
            fail("Unexpected ECE - testRemove");
        }

        assertTrue(instance.remove(entry1));

        // After shift
        try {
            assertTrue(instance.find(0).equals(entry2));
            assertTrue(instance.find(1).equals(entry3));
        } catch (EmptyCollectionException e) {
            fail("Unexpected ECE - testRemove");
        }

        // Collection bounds changed
        try {
            instance.find(2);
            fail();
        } catch (IndexOutOfBoundsException | EmptyCollectionException e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
    }

    /**
     * Test of find method, of class ShoppingArray.
     */
    @Test
    public void testFind() {
        Grocery entry1 = new Grocery("Mayo", "Dressing / Mayo", 1, 2.99f, 1);

        // Test empty collection exception
        try {
            instance.find(0);
            fail("Should throw ECE - testFind");
        } catch (EmptyCollectionException | IndexOutOfBoundsException e) {
            assertTrue(e instanceof EmptyCollectionException);
        }

        instance.add(entry1);

        // Test element not found exception
        try {
            instance.find(1);
            fail("Should throw IOOBE - testFind");
        } catch (EmptyCollectionException | IndexOutOfBoundsException e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }

        // Test element exists case
        assertTrue(instance.contains(entry1));
        try {
            assertEquals(entry1, instance.find(0));

        } catch (EmptyCollectionException | IndexOutOfBoundsException e) {
            fail("Should not throw exception - testFind");
        }
    }

    /**
     * Test of indexOf method, of class ShoppingArray.
     */
    @Test
    public void testIndexOf() {
        Grocery entry1 = new Grocery("Mayo", "Dressing / Mayo", 1, 2.99f, 1);

        // Test element not found case
        boolean elementNotFound = false;
        try {
            instance.indexOf(entry1);
            fail("Should throw ENFE - testIndexOf");
        } catch (ElementNotFoundException e) {
            elementNotFound = true;
        }
        assertTrue(elementNotFound);

        instance.add(entry1);

        // Test element found case
        try {
            assertEquals(0, instance.indexOf(entry1));

        } catch (ElementNotFoundException e) {
            fail("Should not throw exception - testIndexOf");
        }
    }

    /**
     * Test of contains method, of class ShoppingArray.
     */
    @Test
    public void testContains() {
        Grocery entry1 = new Grocery("Mayo", "Dressing / Mayo", 1, 2.99f, 1);

        // Test element does not exist case
        assertFalse(instance.contains(entry1));

        instance.add(entry1);

        // Test element exists case
        assertTrue(instance.contains(entry1));
    }

    /**
     * Test of size method, of class ShoppingArrayList.
     */
    @Test
    public void testSize() {
        Grocery entry1 = new Grocery("Mayo", "Dressing / Mayo", 1, 2.99f, 1);

        assertEquals(0, instance.size());

        instance.add(entry1);

        // Test increment
        assertEquals(1, instance.size());

        assertTrue(instance.remove(entry1));

        // Test decrement
        assertEquals(0, instance.size());
    }

    /**
     * Test of isEmpty method, of class ShoppingArrayList.
     */
    @Test
    public void testIsEmpty() {
        Grocery entry1 = new Grocery("Mayo", "Dressing / Mayo", 1, 2.99f, 1);

        // Test empty
        assertTrue(instance.isEmpty());

        instance.add(entry1);

        // Test not empty
        assertFalse(instance.isEmpty());
    }

}
