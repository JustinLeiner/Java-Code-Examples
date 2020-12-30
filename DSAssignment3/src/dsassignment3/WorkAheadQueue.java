package dsassignment3;

import java.util.ArrayList;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author justinleiner
 * @param <T>
 */
public class WorkAheadQueue<T> implements WorkAheadQueueADT<T> {

    // Variable declaration
    private LinearNode<T> front;
    private LinearNode<T> back;
    private int numNodes;
    public ArrayList<LinearNode<T>> frontThreeNodes;
    public ArrayList<T> frontThreeElements;

    /**
     * Default Constructor.
     */
    public WorkAheadQueue() {
        // Initializes all variables
        this.numNodes = 0;
        this.frontThreeNodes = new ArrayList<LinearNode<T>>();
        this.frontThreeElements = new ArrayList<T>();
        this.front = new LinearNode<>();
        this.back = new LinearNode<>();
    }

    /**
     * Removes and returns the element that is at index x.
     *
     * @param x the position in the queue that is dequeued and returned
     * @return returns and removes the element at index x
     * @throws EmptyCollectionException if Queue is empty
     * @throws InvalidArgumentException if Invalid x is passed
     */
    @Override
    public T dequeue(int x) throws EmptyCollectionException, InvalidArgumentException {
        // Checks if the queue is empty
        if (isEmpty()) {
            throw new EmptyCollectionException("Queue is Empty");
            // Checks if x is an invalid arguement
        } else if (x < 0 || x >= numNodes) {
            throw new InvalidArgumentException("Invalid Arguement Exception");
        } else {
            // Sets front
            LinearNode<T> temp = front;
            // Iterates through and gets elements up to x
            for (int i = 0; i < x; i++) {
                temp = temp.getNext();
            }
            front = front.getNext();
            // Decrements count
            numNodes--;
            // Resets pointers
            helper();
            // Returns the elements up to x
            return temp.getElement();
        }

    }

    /**
     * Returns without removing the element that is at index x.
     *
     * @param x the position in the queue that is returned
     * @return returns the element at index x
     * @throws EmptyCollectionException
     * @throws InvalidArgumentException
     */
    @Override
    public T first(int x) throws EmptyCollectionException, InvalidArgumentException {
        // Checks if the queue is empty
        if (isEmpty()) {
            throw new EmptyCollectionException("Queue is Empty");
        // Checks if x is an invalid argument
        } else if (x > frontThreeElements.size() - 1) {
            throw new InvalidArgumentException("Invalid Arguement");
        } else if (x > 3 || x < 0) {
            throw new InvalidArgumentException("Invalid Arguement");
        } else {
            // Returns the element at position x
            return frontThreeElements.get(x);
        }
    }

    /**
     * Returns an ArrayList of the first three nodes in the queue.
     *
     * @return An ArrayList of the front three nodes
     * @throws EmptyCollectionException
     */
    @Override
    public ArrayList<LinearNode<T>> firstThreeNodes() throws EmptyCollectionException {
        // Checks if the queue is empty
        if (this.numNodes == 0) {
            throw new EmptyCollectionException("queue");
        }
        int count = 0;
        // Sets count
        if (numNodes > 3) {
            count = 3;
        } else {
            count = numNodes;
        }
        // Clears ArrayList
        frontThreeNodes.clear();
        // Sets front
        LinearNode<T> temp = front;
        // Populates front three nodes
        for (int i = 0; i < count; i++) {
            frontThreeNodes.add(i, temp);
            temp = temp.getNext();
        }
        // Returns an ArrayList of frontThreeNodes
        return frontThreeNodes;

    }

    /**
     * Returns an ArrayList of the first three elements in the queue.
     *
     * @return An ArrayList of the front three elements
     * @throws EmptyCollectionException
     */
    @Override
    public ArrayList<T> firstThreeElements() throws EmptyCollectionException {
        // Checks if the queue is empty
        if (this.numNodes == 0) {
            throw new EmptyCollectionException("queue");
        }
        // Sets count
        int count = 0;
        if (numNodes > 3) {
            count = 3;
        } else {
            count = numNodes;
        }
        // Clears ArrayList
        frontThreeElements.clear();
        // Sets front
        LinearNode<T> temp = front;
        // Populates frontThreeElements
        for (int i = 0; i < count; i++) {
            frontThreeElements.add(i, temp.getElement());
            temp = temp.getNext();
        }
        // Returns an ArrayList of frontThreeElements
        return frontThreeElements;
    }

    /**
     * Adds the specified element to the back of the queue.
     *
     * @param element T object that is added to the queue
     */
    @Override
    public void enqueue(T element) {
        // Sets the element
        LinearNode<T> temp = new LinearNode<>(element);
        // Checks if the queue is empty
        if (isEmpty()) {
           // Adjusts pointers
            front = temp;
            back = temp;
        // If not, enqueues the element to the back
        } else {
            // Adjusts pointer
            back.setNext(temp);
            back = temp;
        }
        // Increments numnodes
        numNodes++;
        // Resets pointers
        helper();
    }

    /**
     * Removes and returns the element that is at the front of the queue.
     *
     * @return An element at the front of the queue
     * @throws EmptyCollectionException
     */
    @Override
    public T dequeue() throws EmptyCollectionException {
        // Checks if the queue is empty
        if (isEmpty()) {
            throw new EmptyCollectionException("Queue is Empty");
        }
        // Sets temp
        LinearNode<T> temp = new LinearNode<>();
        // Adjusts pointers
        temp = front;
        front = front.getNext();
        temp.setNext(null);
        // Decrements numNodes
        numNodes--;
        // Resets pointers
        helper();
        // Returns the element
        return temp.getElement();
    }

    /**
     * Returns without removing the element that is at the front of the queue.
     *
     * @return An element at the front of the queue
     * @throws EmptyCollectionException
     */
    @Override
    public T first() throws EmptyCollectionException {
        // Checks if the queue is empty
        if (isEmpty()) {
            throw new EmptyCollectionException("Queue is empty");
        }
        // Returns the first element in the queue
        return front.getElement();
    }

    /**
     * Returns true if the queue contains no elements.
     *
     * @return true if the queue contains no elements, false otherwise
     */
    @Override
    public boolean isEmpty() {
        // Returns true if nunNodes = 0, false otherwise
        return this.numNodes == 0;
    }

    /**
     * Returns the number of elements in the queue.
     *
     * @return The number of elements in the queue
     */
    @Override
    public int size() {
        // Returns size which is held by numNodes
        return this.numNodes;
    }

    /**
     * Returns a textual representation of the queue.
     *
     * @return a textual representation of the queue.
     */
    @Override
    public String toString() {
       // Sets result and front 
        String result = "";
        LinearNode<T> pointer = front;

        // Iterates through and prints the elements until pointer is null 
        while (pointer != null) {
            result = result + (pointer.getElement().toString());
            pointer = pointer.getNext();
        }
        // Returns a textual representation of the queue
        return result;
    }

    /**
     * Clears the queues.
     *
     */
    public void helper() {
        // Clears ArrayLists
        frontThreeElements.clear();
        frontThreeNodes.clear();
        
        // Ensures the ArrayLists were cleared
        try {
            frontThreeElements = firstThreeElements();
        } catch (EmptyCollectionException ex) {
            System.out.println("Empty");
        }
        try {
            frontThreeNodes = firstThreeNodes();
        } catch (EmptyCollectionException ex) {
            System.out.println("Empty");
        }

    }

}
