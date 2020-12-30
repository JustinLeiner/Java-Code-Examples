package Shopping;

import DataStructures.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * @version Fall 2020
 * @author Justin Leiner
 */
public class ShoppingListArrayListTest {

    //private ShoppingListArrayList instance;
    /**
     * Initialize instance and entries.
     */
    @Before
    public void setupTestCases() {
        //instance = new ShoppingListArrayList();
    }

    /**
     * Test of add method, of class ShoppingArray.
     */
    @Test
    public void testAdd() {
        //Create grocery objects and a shopping list instance
        Grocery item1 = new Grocery("Harry Potter", "book", 3,
                15.5f, 2);
        Grocery item2 = new Grocery(item1);
        item2.setQuantity(3);

        ShoppingListArrayList instance = new ShoppingListArrayList();

        // Tests the add method
        // Ensures list size increases by 1 and proper placement of item into 
        // arraylist
        instance.add(item1);
        assertTrue(1 == instance.size());
        try {
            assertTrue(item1.compareTo(instance.find(0)) == 0);
        } catch (EmptyCollectionException ex) {
            System.out.println("Shopping list is empty");
        }

        // Tests the combine feature of the add method
        // Tests the specifc case of adding duplicate items
        // Ensures item quantity is doubled and doesnt increase
        // the size of the arrayList
        int oldQuantity = item1.getQuantity();
        instance.add(item1);
        System.out.println(instance.size());
        assertTrue(instance.size() == 1);
        assertEquals(oldQuantity * 2, item1.getQuantity());

        // Test adding a null entry reference to the shpping list
        instance.add(null);
        assertTrue(1 == instance.size());

        // Test creating and adding a new grocery object to the list
        // Ensures the shopping list size increments and that the item
        // can be found in the list
        Grocery item3 = new Grocery("Laugh in the Rains", "book", 3,
                35.5f, 1);
        instance.add(item3);
        System.out.println("Test..." + instance.size());
        assertTrue(2 == instance.size());
        try {
            assertTrue(item3.compareTo(instance.find(1)) == 0);
            assertTrue(1 == item3.getQuantity());
            assertTrue(1 == instance.find(1).getQuantity());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Test of remove method, of class ShoppingArrayList.
     */
    @Test
    public void testRemove() {
        //Create grocery objects
        Grocery item1 = new Grocery("Harry Potter", "book", 3,
                15.5f, 2);
        Grocery item2 = new Grocery("Hunger Game", "book", 3,
                10.5f, 3);

        // Construct a shopping list
        ShoppingListArrayList instance = new ShoppingListArrayList();
        instance.add(item1);
        instance.add(item2);
        assertTrue(2 == instance.size());

        // Tests the remove method
        // Ensures the item is properly removed and decremented
        Boolean isRemoved = false;
        int oldSize = instance.size();
        assertTrue(instance.remove(item1));
        assertEquals(oldSize - 1, instance.size());
        assertFalse(instance.contains(item1));

        // Tests the remove method for a non-existing entry
        // Ensures that no modifications were made to the shopping list
        oldSize = instance.size();
        assertFalse(instance.remove(item1));
        assertEquals(oldSize, instance.size());

        // Construct a case that the shopping list becomes empty
        isRemoved = instance.remove(item2);
        assertTrue(isRemoved);
        assertTrue(0 == instance.size());

        // Test the remove method when the shopping list is empty
        isRemoved = instance.remove(item2);
        assertFalse(isRemoved);
        assertTrue(0 == instance.size());
    }

    /**
     * Test of find method, of class ShoppingArrayList.
     */
    @Test
    public void testFind() {
        //Create grocery objects
        Grocery item1 = new Grocery("Harry Potter", "book", 3,
                15.5f, 2);
        Grocery item2 = new Grocery("Hunger Game", "book", 3,
                10.5f, 3);

        // Construct a shopping list
        ShoppingListArrayList instance = new ShoppingListArrayList();

        // Test the case for attempting to find an item
        // when the shopping list is empty
        // An EmptyCollectionException instance is thrown in the case if empty
        try {
            instance.find(0);
        } catch (EmptyCollectionException e) {
            System.out.println("Shopping List is empty");
        }

        // Add item1 into the shopping list
        instance.add(item1);
        instance.add(item2);
        assertTrue(2 == instance.size());

        // Test the case of finding a grocery object which index is larger 
        // than the shopping list size
        // An IndexOutOfBoundsException instance is thrown in the case if empty
        try {
            Grocery item = instance.find(5);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Illegal index");
        } catch (EmptyCollectionException e) {
            System.out.println("Shopping list is empty");
        }

        // Test the case of finding a grocery object which index is negative
        try {
            Grocery item = instance.find(-5);
            assertTrue(0 == 1);
        } catch (IndexOutOfBoundsException ex) {
            assertTrue(1 == 1);
        } catch (Exception ex) {
            assertTrue(0 == 1);
        }

        // Test the case of finding a grocery object which index is negative
        try {
            Grocery item = instance.find(0);
            assertTrue(item1.compareTo(item) == 0);
        } catch (IndexOutOfBoundsException ex) {
            assertTrue(0 == 1);
        } catch (EmptyCollectionException ex) {
            assertTrue(0 == 1);
        } catch (Exception ex) {
            assertTrue(0 == 1);
        }
    }

    /**
     * Test of indexOf method, of class ShoppingArrayList.
     */
    @Test
    public void testIndexOf() {
        //Create grocery objects
        Grocery item1 = new Grocery("Harry Potter", "book", 3,
                15.5f, 2);
        Grocery item2 = new Grocery("Hunger Game", "book", 3,
                10.5f, 3);

        // Construct an empty shopping list
        ShoppingListArrayList instance = new ShoppingListArrayList();

        // Check the indexOf method when the shopping list is empty
        try {
            int index = instance.indexOf(item1);
        } catch (ElementNotFoundException ex) {
            assertTrue(1 == 1);
        } catch (Exception ex) {
            assertTrue(0 == 1);
        }

        // Add grocery items into the shopping list
        instance.add(item1);
        instance.add(item2);

        // Check the indexOf method when the grocery item appears in the list
        try {
            int index = instance.indexOf(item2);
            assertTrue(1 == 1);
            assertTrue(index == 1);
            index = instance.indexOf(item1);
            assertTrue(1 == 1);
            assertTrue(index == 0);
        } catch (ElementNotFoundException ex) {
            assertTrue(0 == 1);
        } catch (Exception ex) {
            assertTrue(0 == 1);
        }

        // Check the indexOf method when the grocery item does not appear in the
        // list
        try {
            Grocery item3 = new Grocery("Aladin", "book", 3,
                    15.5f, 2);
            int index = instance.indexOf(item3);
        } catch (ElementNotFoundException ex) {
            assertTrue(1 == 1);
        } catch (Exception ex) {
            assertTrue(0 == 1);
        }

        // Check the indexOf method when the grocery item is null
        try {
            Grocery obj = null;
            int index = instance.indexOf(obj);
        } catch (ElementNotFoundException ex) {
            assertTrue(1 == 1);
        } catch (Exception ex) {
            ex.printStackTrace();
            assertTrue(0 == 1);
        }
    }

    /**
     * Test of contains method, of class ShoppingArrayList.
     */
    @Test
    public void testContains() {
        //Create grocery objects
        Grocery item1 = new Grocery("Harry Potter", "book", 3,
                15.5f, 2);
        Grocery item2 = new Grocery("Hunger Game", "book", 3,
                10.5f, 3);

        // Construct a shopping list
        ShoppingListArrayList instance = new ShoppingListArrayList();

        // Check the contains method when the shopping list is empty
        boolean isTrue = instance.contains(item1);
        assertFalse(isTrue);

        // Add grocery items into the shopping list
        instance.add(item1);
        instance.add(item2);

        // Check the contains method when the grocery item appears in the list
        isTrue = instance.contains(item2);
        assertTrue(isTrue);

        // Check the contains method when the grocery item does not appear in 
        // the list
        Grocery item3 = new Grocery("Aladin", "book", 3,
                15.5f, 2);
        isTrue = instance.contains(item3);
        assertFalse(isTrue);

        // Check the contains method when the grocery item is null
        Grocery obj = null;
        isTrue = instance.contains(obj);
        assertFalse(isTrue);
    }

    /**
     * Test of size method, of class ShoppingArrayList.
     */
    @Test
    public void testSize() {
        Grocery entry1 = new Grocery("Mayo", "Dressing / Mayo", 1, 2.99f, 1);
        ShoppingListArrayList instance = new ShoppingListArrayList();

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
        ShoppingListArrayList instance = new ShoppingListArrayList();

        // Test empty
        assertTrue(instance.isEmpty());

        instance.add(entry1);

        // Test not empty
        assertFalse(instance.isEmpty());
    }
}
