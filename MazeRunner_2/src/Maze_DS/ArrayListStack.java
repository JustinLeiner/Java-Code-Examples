/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maze_DS;

import DataStructures.EmptyCollectionException;
import DataStructures.StackADT;
import java.util.ArrayList;

/**
 *
 * @author Justin Leiner
 * @param <T>
 */
public class ArrayListStack<T> implements StackADT<T> {

    private ArrayList<T> fakeStack = new ArrayList<>();

    /**
     * Default Constructor.
     *
     */
    public ArrayListStack() {
        fakeStack = new ArrayList<T>();
    }

    /**
     * Method to add a new entry to the ArrayList.
     *
     * @param element
     */
    @Override
    public void push(T element) {
        fakeStack.add(element);
    }

    /**
     * Method to remove an entry from the ArrayList.
     *
     * @return element from the top of the stack (end of ArrayList)
     * @throws EmptyCollectionException throws exception if the ArrayList is
     * empty
     */
    @Override
    public T pop() throws EmptyCollectionException {
        if (fakeStack.isEmpty()) {
            throw new EmptyCollectionException("stack");
        } else {
            T element = fakeStack.get(fakeStack.size() - 1);
            fakeStack.remove(fakeStack.size() - 1);
            return element;
        }
    }

    /**
     * Method to see an entry from the end of the ArrayList.
     *
     * @return element from the top of the stack (end of ArrayList)
     * @throws EmptyCollectionException throws exception if the ArrayList is
     * empty
     */
    @Override
    public T peek() throws EmptyCollectionException {
        if (fakeStack.isEmpty()) {
            throw new EmptyCollectionException("stack");
        } else {
            T element = fakeStack.get(fakeStack.size() - 1);
            return element;
        }
    }

    /**
     * Method to check if the ArrayList is empty.
     *
     * @return returns true if the ArrayList if empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        if (fakeStack.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * Method to check the ArrayList size.
     *
     * @return returns the size of ArrayList
     */
    @Override
    public int size() {
        return fakeStack.size();
    }

    /**
     * Method that returns a String.
     *
     * @return Returns a textual representation of the ArrayList
     */
    @Override
    public String toString() {
        return "ArrayListStack{" + "fakeStack=" + fakeStack + '}';
    }

}
