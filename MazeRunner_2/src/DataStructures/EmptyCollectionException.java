package DataStructures;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * An Empty Collection Exception class.
 * Prints out what type of collection is empty
 * For use in ITCS 2214 Data Structures & Algorithms
 * UNC Charlotte, 2016
 * @author clatulip
 */
public class EmptyCollectionException extends Exception {

    /** 
     * Outputs a message notifying the user that the collection is empty.
     * 
     * @param collection 
     */
    public EmptyCollectionException(String collection) {
        super("The " + collection + " is empty.");
    }
    
}
