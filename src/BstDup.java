import java.util.Arrays;

/**
 *  A Main class to demo BstDup
 *
 * @author      Brandon Dombrowsky
 * @version     2021-11-08
 */
public class BstDup<E extends Comparable<E>> implements BstDupInterface<E> {
    //-----------------------------------------------------------------------------------
    //      INSTANCE DATA
    //-----------------------------------------------------------------------------------
    /**     The root node for BstDup                                           */
    private BstNode<E> root;

    /**     A variable to keep track of total number of nodes                  */
    private int size;
    //-----------------------------------------------------------------------------------
    //      CONSTRUCTOR
    //-----------------------------------------------------------------------------------
    /**     Constructor for BstDup; no parameters permitted per interface      */
    public BstDup() {
        root = null;
        size = 0;
    }

    /**
     * Returns the number of nodes created
     *
     * @return      number of nodes
     */
    public int getSize() {
        return size;
    }
    //-----------------------------------------------------------------------------------
    //      INTERFACE IMPLEMENTATIONS
    //-----------------------------------------------------------------------------------
    /**     {@inheritDoc}                                                      */
    @Override
    public void add(E data) {
        // uses the "x = change(x) concept
        // adoption side // send off on journey side @ root
        root = add(data, root);
    }
    /**
     * Adds data to the tree, incrementing match count as necessary (if data already exists), or adding
     * new data to the tree (if data does not already exist)
     *
     * @param data          the data to add to the tree
     * @param startNode     a node to allow us to begin a new journey, either left or right; the start of recursive call
     * @return              new value of startNode
     */
    private BstNode<E> add(E data, BstNode<E> startNode) {
        // we never talk about root on the private side
        BstNode<E> newNode = new BstNode<>(data);
        if (startNode == null) {
            startNode = newNode;
            size++;
        } else if (data.compareTo(startNode.data) <= 0) {
            if (data.compareTo(startNode.data) == 0) {
                startNode.count++;
            } else {
                // adoption side // send off on journey side
                startNode.left = add(data, startNode.left);
            }
        } else  {
                // adoption side // send off on journey side
                startNode.right = add(data, startNode.right);
        }
        return startNode;
    }

    /**     {@inheritDoc}                                                      */
    @Override
    public void delete(E data) {
        // uses the "x = change(x) concept
        root = delete(data, root);
    }

    /**
     * Find minimum value to use in the deletion process to swap value with successor
     *
     * @param startNode     a node to allow us to begin a new journey, either left or right; the start of recursive call
     * @return              the minimum value used to swap the successor; starts right in the recursive call
     */
    public E minValue(BstNode<E> startNode) {
        E minv = startNode.data;
        while (startNode.left != null) {
            minv = startNode.left.data;
            startNode = startNode.left;
        }
        return minv;
    }
    /**
     * Removes data from the tree, decrementing match count as necessary (if count is above 0),
     * or deleting the data entirely if match count reaches 0
     *
     * @param data          the data to delete from the tree
     * @param startNode     a node to allow us to begin a new journey, either left or right; the start of recursive call
     * @return              new value of startNode
     */
    private BstNode<E> delete(E data, BstNode<E> startNode) {
        if (startNode == null) {
            return startNode;
        }
        if (data.compareTo(startNode.data) < 0) {
            startNode.left = delete(data, startNode.left);
        } else if (data.compareTo(startNode.data) > 0) {
            startNode.right = delete(data, startNode.right);
        } else {
            if (startNode.count > 1) {
                startNode.count--;
            } else {
                size--;
                // node with none or one child
                if (startNode.left == null) {
                    return startNode.right;
                } else if (startNode.right == null) {
                    return startNode.left;
                } else {
                    // node with 2 children; swap data from successor
                    startNode.data = minValue(startNode.right);
                    // delete successor
                    // adoption side // send off on journey side
                    startNode.right = delete(data, startNode.right);
                    }
                }
        }
        return startNode;
    }

    /**     {@inheritDoc}                                                      */
    @Override
    public void deleteAll(E data) {
        // uses the "x = change(x) concept
        root = deleteAll(data, root);
    }

    /**
     * Removes data from the tree, including all matches
     *
     * @param data          the data to delete from the tree
     * @param startNode     a node to allow us to begin a new journey, either left or right; the start of recursive call
     * @return              new value of startNode
     */
    private BstNode<E> deleteAll(E data, BstNode<E> startNode) {
        if (startNode != null) {
            while (getMatchCount(data) > 0) {
                startNode = delete(data, startNode);
            }
        }
        return startNode;
    }

    /**     {@inheritDoc}                                                      */
    @Override
    public int getMatchCount(E data) {
        // no x = change(x)
        return getMatchCount(data, root);
    }

    /**
     * Retrieves the match count for the provided data
     *
     * @param data          the data to search for
     * @param startNode     a node to allow us to begin a new journey, either left or right; the start of recursive call
     * @return              the match count, or -1 if the data is not found in the tree
     */
    private int getMatchCount(E data, BstNode<E> startNode) {
        if (startNode == null) {
            return -1;
        } else if (data.compareTo(startNode.data) == 0) {
            return startNode.count;
        } else if (data.compareTo(startNode.data) <= 0) {
            return getMatchCount(data, startNode.left);
        } else {
            return getMatchCount(data, startNode.right);
        }
    }
    //-----------------------------------------------------------------------------------
    //      PRIVATE INNER CLASSES
    //-----------------------------------------------------------------------------------
    /**
     * Binary search tree Node class
     *
     * @param <E>       generic type
     */
    private static class BstNode<E> {
        /**     Generic data field                                 */
        public E data;

        /**     Generic nodes to point to, left and right          */
        public BstNode<E> left, right;

        /**     Total number of duplicates                         */
        public int count;

        /**
         * Generic binary search tree constructor
         *
         * @param nodeValue     the value coming in assigned to data
         */
        public BstNode(E nodeValue) {
            this.data = nodeValue;
            left = right = null;
            count = 1;
        }
    }
}