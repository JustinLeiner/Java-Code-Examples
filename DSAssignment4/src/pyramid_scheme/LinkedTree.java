/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyramid_scheme;

import ADTs.TreeADT;
import DataStructures.MultiTreeNode;
import Exceptions.ElementNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author Justin Leiner
 * @param <T>
 * @version 12/7/2020
 */
public class LinkedTree<T> implements TreeADT<T> {

    /**
     * The root of the tree.
     */
    protected MultiTreeNode<T> root;

    /**
     * Normal constructor - element
     *
     * @param startElem
     */
    public LinkedTree(T startElem) {
        root = new MultiTreeNode<>(startElem);
    }

    /**
     * Normal constructor - node
     *
     * @param startNode
     */
    public LinkedTree(MultiTreeNode<T> startNode) {
        root = startNode;
    }

    /**
     * Returns the element stored at the root
     *
     * @return root element
     */
    @Override
    public T getRootElement() {
        // Returns root element
        return root.getElement();
    }

    /**
     * Adds a node containing the child element to another node that is already
     * in the tree containing the parent element. This particular overload of
     * the addChild method is responsible for locating the parent node, then
     * calling the other overload to complete the task.
     *
     * @param parent An element contained by a particular node in the tree,
     * which will ultimately be given a child node
     * @param child The element to be packaged into a node and added to the tree
     * @throws ElementNotFoundException
     */
    public void addChild(T parent, T child) throws ElementNotFoundException {
        // Calls second AddChild method as long as parent exists (not null)
        if (findNode(parent) != null) {
            addChild(findNode(parent), child);
        }
    }

    /**
     * Adds a node containing the child element to another node that is already
     * in the tree containing the parent element. This particular overload of
     * the addChild method is responsible for creating a node for child and
     * adding it to the children of parentNode.
     *
     * @param parentNode the node receiving a child node
     * @param child the element to be packaged and added as a child node
     */
    protected void addChild(MultiTreeNode<T> parentNode, T child) {
        // Initializes a new MultiTreeNode so a child can be added
        MultiTreeNode<T> add = new MultiTreeNode<>(child);
        parentNode.addChild(add);
    }

    /**
     * Finds the node that contains a target element. Calls the recursive
     * overload to search for target.
     *
     * @param target the element being searched for
     * @return the MultiTreeNode containing the target, or null if not found
     */
    public MultiTreeNode<T> findNode(T target) {
        return findNode(root, target);
    }

    /**
     * Finds the node that contains a target element. This checks the current
     * node, and if it is the target, returns it. Otherwise, it recursively
     * checks each of the current node's children, to see if they can find it.
     * If none of this node's children can find it either, then null is
     * returned.
     *
     * @param node the node currently being examined
     * @param target the element being searched for
     * @return the MultiTreeNode containing the target, or null if not found
     */
    private MultiTreeNode<T> findNode(MultiTreeNode<T> node, T target) {
        //If this node is the one holding the target...
        if (node.getElement().equals(target)) {
            return node; //...Return this node, so it is passed upwards.
        } else { //Otherwise...
            MultiTreeNode<T> temp;
            //For each child of this node...
            for (MultiTreeNode<T> child : node.getChildren()) {
                //...Call this method, seeing if the child can find our target.
                temp = findNode(child, target);
                //If it isn't null, then the child has found our target, and...
                if (temp != null) {
                    return temp; //...The target is passed upwards.
                }
            }
            //If none of the children found the target, return null.
            return null; //This signifies that no target was found.
        }
    }

    /**
     * Tries to find a node that contains the target element, and indicates true
     * if it can, false if it cannot.
     *
     * @param target the element being searched for.
     * @return a boolean representing whether or not the tree contains a node
     * with the target element.
     */
    @Override
    public boolean contains(T target) {
        // Enters an if and calls findNode. Returns true if the target is found,
        // false otherwise
        if (findNode(target) == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns the size of the tree, measured by how many nodes are in it. Calls
     * the recursive method countDown to determine this.
     *
     * @return the size of the tree.
     */
    @Override
    public int size() {
        // Checks if root is null, returns 0 if null, otherwise calls countDown
        if (root == null) {
            return 0;
        }
        return countDown(root);
    }

    /**
     * Recursively counts the current node, its children, and so on. Used by
     * size() to determine how many nodes are in the tree.
     *
     * @param node the node currently being examined.
     * @return the amount of nodes from the starting node down.
     */
    private int countDown(MultiTreeNode<T> node) {
        // Initializes int count
        int count = 0;

        // Counts the number of children
        // If no children, count is 1
        if (node.getNumChildren() == 0) {
            count = 1;
            return count;
        } else {
            // Adds the number of children to count
             count += 1 + node.getNumChildren();
             // Since a node can have multiple children, it is necessary to use
             // a loop to iteratre through all childrean and collect the amount
             // of nodes calling countDown recursively
            for (int i = 0; i < node.getNumChildren(); i++) {  
              count += countDown(node.getChildren().get(i)) - 1;
            }
            // Returns count
            return count;
        }
    }

    /**
     *
     * @return A series of lines demonstrating elements on each layer.
     */
    @Override
    public String toString() {
        String print = "Linked Tree: \nLayer 1 (Root): " + root.getElement();
        ArrayList<MultiTreeNode<T>> layer = root.getChildren();
        ArrayList<MultiTreeNode<T>> nextLayer;
        int layerNum = 2;
        while (!layer.isEmpty()) {
            print += "\nLayer " + layerNum + ":";
            nextLayer = new ArrayList<>();
            for (MultiTreeNode<T> node : layer) {
                nextLayer.addAll(node.getChildren());
                print += " " + node;
            }
            layer = nextLayer;
            layerNum++;
        }
        return print;
    }
}
