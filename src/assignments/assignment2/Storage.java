package assignments.assignment2;

import java.util.ArrayList;

// Uncomment it to use junit tests.
/*
import org.junit.Test;
import static org.junit.Assert.*;
 */

/**
 * @author Roman Soldatov BS19-02.
 * 2.3 Storage.
 * Implementing the AVL tree.
 * <p>
 * Maybe your IDE does not import this library automatically. So, then this class won't compile because of errors.
 * So, just in case I commented it out.
 * Well, you can uncomment the code inside this class and import junit library to check tests.
 */
public class Storage {

    // Uncomment it to use junit tests.
    /*

    // Test add(K key, V value) method
    @Test
    public void testAddMethodSet1() {
        AVLTree<Integer, String> tree = set1();

        String correctResult = "[[1, d, 1], [2, h, 0], [3, b, 2], [4, i, 0], [5, e, 1], [6, a, 3], [7, j, 0], [8, f, 1], [9, c, 2], [10, g, 1], [11, k, 0]]";
        assertEquals(tree.getNodesInorder().toString(), correctResult);
    }

    @Test
    public void testAddMethodSet2() {
        AVLTree<Integer, String> tree = set2();

        String correctResult = "[[1, a, 0], [2, b, 1], [3, c, 0], [4, d, 3], [5, e, 0], [6, f, 1], [7, g, 0], [8, h, 2], [9, i, 0], [10, j, 1], [11, k, 0]]";
        assertEquals(tree.getNodesInorder().toString(), correctResult);
    }

    @Test
    public void testAddMethodSet3() {
        AVLTree<Integer, String> tree = set3();

        String correctResult = "[[3, f, 0], [5, d, 1], [7, e, 2], [8, i, 0], [10, c, 3], [15, j, 0], [20, h, 1], [25, b, 2], [30, g, 0], [50, a, 1]]";
        assertEquals(tree.getNodesInorder().toString(), correctResult);
    }

    // Test delete(K key) method.
    @Test
    public void testDeleteLeftLeafSet1() {
        AVLTree<Integer, String> tree = set1();
        tree.delete(7);

        String correctResult = "[[1, d, 1], [2, h, 0], [3, b, 2], [4, i, 0], [5, e, 1], [6, a, 3], [8, f, 0], [9, c, 2], [10, g, 1], [11, k, 0]]";
        assertEquals(tree.getNodesInorder().toString(), correctResult);
    }

    @Test
    public void testDeleteRightLeafSet1() {
        AVLTree<Integer, String> tree = set1();
        tree.delete(2);

        String correctResult = "[[1, d, 0], [3, b, 2], [4, i, 0], [5, e, 1], [6, a, 3], [7, j, 0], [8, f, 1], [9, c, 2], [10, g, 1], [11, k, 0]]";
        assertEquals(tree.getNodesInorder().toString(), correctResult);
    }

    @Test
    public void testDeleteLeftChildSet1() {
        AVLTree<Integer, String> tree = set1();
        tree.delete(5);

        String correctResult = "[[1, d, 1], [2, h, 0], [3, b, 2], [4, i, 0], [6, a, 3], [7, j, 0], [8, f, 1], [9, c, 2], [10, g, 1], [11, k, 0]]";
        assertEquals(tree.getNodesInorder().toString(), correctResult);
    }

    @Test
    public void testDeleteRightChildSet1() {
        AVLTree<Integer, String> tree = set1();
        tree.delete(10);

        String correctResult = "[[1, d, 1], [2, h, 0], [3, b, 2], [4, i, 0], [5, e, 1], [6, a, 3], [7, j, 0], [8, f, 1], [9, c, 2], [11, k, 0]]";
        assertEquals(tree.getNodesInorder().toString(), correctResult);
    }

    @Test
    public void testDeleteTwoChildren1Set1() {
        AVLTree<Integer, String> tree = set1();
        tree.delete(3);

        String correctResult = "[[1, d, 0], [2, b, 2], [4, i, 0], [5, e, 1], [6, a, 3], [7, j, 0], [8, f, 1], [9, c, 2], [10, g, 1], [11, k, 0]]";
        assertEquals(tree.getNodesInorder().toString(), correctResult);
    }

    @Test
    public void testDeleteTwoChildren2Set1() {
        AVLTree<Integer, String> tree = set1();
        tree.delete(9);

        String correctResult = "[[1, d, 1], [2, h, 0], [3, b, 2], [4, i, 0], [5, e, 1], [6, a, 3], [7, j, 0], [8, c, 2], [10, g, 1], [11, k, 0]]";
        assertEquals(tree.getNodesInorder().toString(), correctResult);
    }

    @Test
    public void testDeleteRootSet1() {
        AVLTree<Integer, String> tree = set1();
        tree.delete(9);

        String correctResult = "[[1, d, 1], [2, h, 0], [3, b, 2], [4, i, 0], [5, e, 1], [6, a, 3], [7, j, 0], [8, c, 2], [10, g, 1], [11, k, 0]]";
        assertEquals(tree.getNodesInorder().toString(), correctResult);
    }

    @Test
    public void testDeleteNonExistNodeSet1() {
        AVLTree<Integer, String> tree = set1();
        tree.delete(18);

        String correctResult = "[[1, d, 1], [2, h, 0], [3, b, 2], [4, i, 0], [5, e, 1], [6, a, 3], [7, j, 0], [8, f, 1], [9, c, 2], [10, g, 1], [11, k, 0]]";
        assertEquals(tree.getNodesInorder().toString(), correctResult);
    }

    @Test
    public void testDeleteLeftLeftCaseSet3() {
        AVLTree<Integer, String> tree = set3();
        tree.delete(5);

        String correctResult = "[[3, f, 0], [7, e, 1], [8, i, 0], [10, c, 3], [15, j, 0], [20, h, 1], [25, b, 2], [30, g, 0], [50, a, 1]]";
        assertEquals(tree.getNodesInorder().toString(), correctResult);
    }

    @Test
    public void testDeleteLeftRightCaseSet3() {
        AVLTree<Integer, String> tree = set3();
        tree.delete(8);

        String correctResult = "[[3, f, 0], [5, d, 1], [7, e, 0], [10, c, 3], [15, j, 0], [20, h, 1], [25, b, 2], [30, g, 0], [50, a, 1]]";
        assertEquals(tree.getNodesInorder().toString(), correctResult);
    }

    @Test
    public void testDeleteRightRightCaseSet3() {
        AVLTree<Integer, String> tree = set3();
        tree.delete(50);

        String correctResult = "[[3, f, 0], [5, d, 1], [7, e, 2], [8, i, 0], [10, c, 3], [15, j, 0], [20, h, 1], [25, b, 2], [30, g, 0]]";
        assertEquals(tree.getNodesInorder().toString(), correctResult);
    }

    @Test
    public void testDeleteRightLeftCaseSet3() {
        AVLTree<Integer, String> tree = set3();
        tree.delete(20);

        String correctResult = "[[3, f, 0], [5, d, 1], [7, e, 2], [8, i, 0], [10, c, 3], [15, j, 0], [25, b, 2], [30, g, 0], [50, a, 1]]";
        assertEquals(tree.getNodesInorder().toString(), correctResult);
    }

     */

    /**
     * Fill the tree.
     * O(1)
     *
     * @return the filled tree.
     */
    private AVLTree<Integer, String> set1() {
        AVLTree<Integer, String> tree = new AVLTree<>();
        tree.add(6, "a");
        tree.add(3, "b");
        tree.add(9, "c");
        tree.add(1, "d");
        tree.add(5, "e");
        tree.add(8, "f");
        tree.add(10, "g");
        tree.add(2, "h");
        tree.add(4, "i");
        tree.add(7, "j");
        tree.add(11, "k");
        return tree;
    }

    /**
     * Fill the tree.
     * O(1)
     *
     * @return the filled tree.
     */
    private AVLTree<Integer, String> set2() {
        AVLTree<Integer, String> tree = new AVLTree<>();
        tree.add(1, "a");
        tree.add(2, "b");
        tree.add(3, "c");
        tree.add(4, "d");
        tree.add(5, "e");
        tree.add(6, "f");
        tree.add(7, "g");
        tree.add(8, "h");
        tree.add(9, "i");
        tree.add(10, "j");
        tree.add(11, "k");
        return tree;
    }

    /**
     * Fill the tree.
     * O(1)
     *
     * @return the filled tree.
     */
    private AVLTree<Integer, String> set3() {
        AVLTree<Integer, String> tree = new AVLTree<>();
        tree.add(50, "a");
        tree.add(25, "b");
        tree.add(10, "c");
        tree.add(5, "d");
        tree.add(7, "e");
        tree.add(3, "f");
        tree.add(30, "g");
        tree.add(20, "h");
        tree.add(8, "i");
        tree.add(15, "j");
        return tree;
    }
}

/**
 * Class with AVL tree implementation.
 *
 * @param <K> key by which nodes will be sorted.
 * @param <V> value of a node.
 */
class AVLTree<K extends Comparable<K>, V> {
    private Node root;

    /**
     * Insertion of a new node by its key and value.
     * It compares by its key.
     * If we insert the node with the same key, then it goes to the right subtree.
     * The insertion is the same as an insertion in BST.
     * However, we do the trinode restructuring after insertion.
     * As we insert the node into balanced tree and the rebalancing takes the constant time,
     * then the method add(K key, V value) takes O(log(n)) time complexity, where n - count of all nodes in a tree.
     *
     * @param key   - key by which the node will be compared.
     * @param value - value to store in a node.
     */
    public void add(K key, V value) {
        // If the tree is empty.
        if (root == null) {
            root = new Node(key, value, null);
        } // Reassign the reference to the node after insertion and rebalancing.
        else {
            root = add(key, value, root);
        }
    }

    /**
     * Deletion of a node by its key.
     * If the node with such key doesn't exist then don't change the tree.
     * The deletion is the same as aa deletion in BST.
     * However, we do the trinode restructuring after deletion.
     * As we search the node to delete in a balanced tree and the rebalancing takes the constant time,
     * then the method delete(K key) takes O(log(n)) time complexity, where n - count of all nodes in a tree.
     *
     * @param key - key which the node to delete has.
     * @return the value of the node which was deleted. If such node has not been in a tree, then return null.
     */
    public V delete(K key) {
        return delete(key, root).value;
    }

    /**
     * Find the node by it's key.
     * O(log(n)) in a worst case.
     *
     * @param key - key of this node.
     * @return the node if it's found, null - otherwise.
     */
    private Node getNodeByKey(K key) {
        return getNodeByKey(key, root);
    }

    /**
     * Return the nodes of a tree in a preorder traversal.
     * O(n) to visit all nodes.
     *
     * @return ArrayList of nodes.
     */
    public ArrayList<Node> getNodesPreorder() {
        ArrayList<Node> list = new ArrayList<>();
        traversalNodes(root, list, TraversalOrder.PREORDER);
        return list;
    }

    /**
     * Return the nodes of a tree in a inorder traversal.
     * O(n) to visit all nodes.
     *
     * @return ArrayList of nodes.
     */
    public ArrayList<Node> getNodesInorder() {
        ArrayList<Node> list = new ArrayList<>();
        traversalNodes(root, list, TraversalOrder.INORDER);
        return list;
    }

    /**
     * Return the nodes of a tree in a postorder traversal.
     * O(n) to visit all nodes.
     *
     * @return ArrayList of nodes.
     */
    public ArrayList<Node> getNodesPostorder() {
        ArrayList<Node> list = new ArrayList<>();
        traversalNodes(root, list, TraversalOrder.POSTORDER);
        return list;
    }

    /**
     * Insert a node inside the current subtree.
     * O(log(n))
     *
     * @param key     - key by which the node will be compared.
     * @param value   - value to store inside the node.
     * @param subTree - the start node of the subtree where the node will be inserted.
     * @return the new head-node of this subtree after the rebalancing.
     */
    private Node add(K key, V value, Node subTree) {
        // If the key is less than the node's key.
        if (key.compareTo(subTree.key) < 0) {
            // Insert a new node as a new left subtree.
            if (subTree.leftNode == null) {
                subTree.leftNode = new Node(key, value, subTree);
            } else {
                // Go to the left subtree.
                subTree.leftNode = add(key, value, subTree.leftNode);
            }
        } // If the key is bigger or equal than the node's key.
        else {
            // Insert a new node as a new right subtree.
            if (subTree.rightNode == null) {
                subTree.rightNode = new Node(key, value, subTree);
            } else {
                // Go to the right subtree.
                subTree.rightNode = add(key, value, subTree.rightNode);
            }
        }

        // Reassign the reference to the new root of this subtree.
        subTree = restoreBalance(subTree);
        return subTree;
    }

    /**
     * Delete a node inside the current subtree.
     * O(log(n))
     *
     * @param key     - key by which the node will be compared.
     * @param subTree - the start node of the subtree where the node will be deleted.
     * @return the new head-node of this subtree after the rebalancing.
     */
    private Node delete(K key, Node subTree) {
        // If such node with this key doesn't exist in a subtree.
        if (subTree == null) {
            return null;
        }

        // If the key is less than the subtree's key.
        if (key.compareTo(subTree.key) < 0) {
            // Delete and reassign the head-node in a left subtree.
            subTree.leftNode = delete(key, subTree.leftNode);
        } // If the key is bigger than the subtree's key.
        else if (key.compareTo(subTree.key) > 0) {
            // Delete and reassign the head-node in a right subtree.
            subTree.rightNode = delete(key, subTree.rightNode);
        } // If we found the node with this key.
        else {
            // If the node has only one child or it's a leaf.
            if (subTree.leftNode == null || subTree.rightNode == null) {
                if (subTree.leftNode != null) {
                    subTree = subTree.leftNode;
                } else {
                    subTree = subTree.rightNode;
                }
            } // If the node has two children.
            else {
                // Find the predecessor.
                Node predecessor = getPredecessor(subTree);
                // Swap it with the node.
                subTree.key = predecessor.key;
                // Delete the node.
                subTree.leftNode = delete(subTree.key, subTree.leftNode);

                // Alternative way.
                /*
                // Find the successor.
                Node successor = getSuccessor(subTree);
                // Swap it with the node.
                subTree.key = successor.key;
                // Delete the node.
                subTree.rightNode = delete(subTree.key, subTree.rightNode);
                 */
            }
        }

        // Do the rebalancing after the deletion.
        if (subTree != null) {
            subTree = restoreBalance(subTree);
        }

        // Reassign the reference to the new root of this subtree.
        return subTree;
    }

    /**
     * Return the node which is followed after the 'subTree' node in a inorder traversal.
     * O(log(n)) as the tree is balanced. Therefore, O(h) = O(log(n)).
     *
     * @param subTree - the node which succsessor is required to find.
     * @return - the successor of the 'subTree' node.
     */
    private Node getSuccessor(Node subTree) {
        if (subTree == null) {
            return null;
        }

        Node x = subTree;

        // Find the minimum child-node in a right subtree.
        if (x.rightNode != null) {
            return getMinimumNode(x.rightNode);
        }

        // If the node doesn't have the right child,
        // then the successor is a node such as it's left child is a subtree in which our node is placed.
        Node y = x.parentNode;
        while (y != null && x.equals(y.rightNode)) {
            x = y;
            y = y.parentNode;
        }
        return y;
    }

    /**
     * Return the node which is followed before the 'subTree' node in a inorder traversal.
     * O(log(n)) as the tree is balanced. Therefore, O(h) = O(log(n)).
     *
     * @param subTree - the node which predecessor is required to find.
     * @return - the predecessor of the 'subTree' node.
     */
    private Node getPredecessor(Node subTree) {
        if (subTree == null) {
            return null;
        }

        Node x = subTree;

        // Find the maximum child-node in a right subtree.
        if (x.leftNode != null) {
            return getMaximumNode(x.leftNode);
        }

        // If the node doesn't have the left child,
        // then the predecessor is a node such as it's right child is a subtree in which our node is placed.
        Node y = x.parentNode;
        while (y != null && x.equals(y.leftNode)) {
            x = y;
            y = y.parentNode;
        }
        return y;
    }

    /**
     * Return the node which is minimum comparing with other nodes of this subtree.
     * O(log(n)) in a worst case.
     *
     * @param subTree - subtree in which the minimum node is required to find.
     * @return the left most child of this subtree.
     */
    private Node getMinimumNode(Node subTree) {
        if (subTree == null) {
            return null;
        }

        Node x = subTree;
        while (x.leftNode != null) {
            x = x.leftNode;
        }
        return x;
    }

    /**
     * Return the node which is maximum comparing with other nodes of this subtree.
     * O(log(n)) in a worst case.
     *
     * @param subTree - subtree in which the maximum node is required to find.
     * @return the right most child of this subtree.
     */
    private Node getMaximumNode(Node subTree) {
        if (subTree == null) {
            return null;
        }

        Node x = subTree;
        while (x.rightNode != null) {
            x = x.rightNode;
        }
        return x;
    }

    /**
     * Traversal of all nodes in a specific order.
     * Fill the ArrayList of nodes which have already been visited.
     * O(n) as we visit all nodes.
     *
     * @param node  - the start node to travel.
     * @param list  - ArrayList to write the nodes.
     * @param order - specific rule by which the traversal will be done.
     */
    private void traversalNodes(Node node, ArrayList<Node> list, TraversalOrder order) {
        if (node != null) {
            switch (order) {
                // Visit the node, Preorder Left, Preorder Right.
                case PREORDER:
                    list.add(node);
                    traversalNodes(node.leftNode, list, order);
                    traversalNodes(node.rightNode, list, order);
                    break;
                // Inorder Left, Visit the node, Inorder Right.
                case INORDER:
                    traversalNodes(node.leftNode, list, order);
                    list.add(node);
                    traversalNodes(node.rightNode, list, order);
                    break;
                // Postorder Left, Postorder Right, Visit the node.
                case POSTORDER:
                    traversalNodes(node.leftNode, list, order);
                    traversalNodes(node.rightNode, list, order);
                    list.add(node);
                    break;
            }
        }
    }

    /**
     * Return the height of a node.
     * O(1)
     *
     * @param node - node which height is required to know.
     * @return node's height if it exists, -1 - otherwise.
     */
    private int getHeight(Node node) {
        if (node == null) {
            return -1;
        }
        return node.height;
    }

    /**
     * Update the height of the node according to it's right and left children heights.
     * O(1)
     *
     * @param node - node which height is required to recalculate.
     */
    private void updateHeight(Node node) {
        node.height = 1 + Math.max(getHeight(node.leftNode), getHeight(node.rightNode));
    }

    /**
     * Calculate the difference between the right-child height and left-child height.
     * O(1)
     *
     * @param parentNode - node which balance between two children nodes is required to find.
     * @return the balance of the 'parentNode'.
     */
    private int getBalanceBetweenNodes(Node parentNode) {
        if (parentNode == null) {
            return 0;
        }
        return getHeight(parentNode.rightNode) - getHeight(parentNode.leftNode);
    }

    /*
     *              (rootNode)
     *              /         \
     *      (newRootNode)     (subtree3)
     *     /            \
     * (subtree1) (subtree2)
     *
     * Becomes to...
     *
     *        (newRootNode)
     *         /       \
     * (subtree1)     (rootNode)
     *               /         \
     *          (subtree2) (subtree3)
     */

    /**
     * The right rotation.
     * O(1)
     *
     * @param rootNode - the root of a subtree which nodes are unbalanced.
     * @return the reference to the new root of this subtree.
     */
    private Node rightRotate(Node rootNode) {
        Node newRootNode = rootNode.leftNode;
        rootNode.leftNode = newRootNode.rightNode;
        newRootNode.rightNode = rootNode;

        newRootNode.parentNode = rootNode.parentNode;
        rootNode.parentNode = newRootNode;

        updateHeight(rootNode);
        updateHeight(newRootNode);

        return newRootNode;
    }

    /*
     *        (rootNode)
     *         /       \
     * (subtree1)     (newRootNode)
     *               /         \
     *          (subtree2) (subtree3)
     *
     *
     *
     * Becomes to...
     *              (newRootNode)
     *              /            \
     *      (rootNode)     (subtree3)
     *     /         \
     * (subtree1) (subtree2)
     *
     *
     */

    /**
     * The left rotation.
     * O(1)
     *
     * @param rootNode - the root of a subtree which nodes are unbalanced.
     * @return the reference to the new root of this subtree.
     */
    private Node leftRotate(Node rootNode) {
        Node newRootNode = rootNode.rightNode;
        rootNode.rightNode = newRootNode.leftNode;
        newRootNode.leftNode = rootNode;

        newRootNode.parentNode = rootNode.parentNode;
        rootNode.parentNode = newRootNode;

        updateHeight(rootNode);
        updateHeight(newRootNode);

        return newRootNode;
    }

    /**
     * Check if the balance (difference between right children's height and left children's height)
     * of the subtree is correct (the difference is -1, 0 or 1)
     * and do rebalancing if it's not.
     * O(1)
     *
     * @param subTree - the subtree which balance is required to restore
     * @return the reference to the new root of this subtree.
     */
    private Node restoreBalance(Node subTree) {
        // Know the balance between subtree's children.
        updateHeight(subTree);
        int balance = getBalanceBetweenNodes(subTree);

        // Do the rebalancing if the difference of heights is more than 1.
        if (balance < -1) {
            // left-right case.
            if (getHeight(subTree.leftNode.leftNode) <= getHeight(subTree.leftNode.rightNode)) {
                subTree.leftNode = leftRotate(subTree.leftNode);
            }
            // left-left case or the continuation of the left-right case (if the condition below was true)
            subTree = rightRotate(subTree);
        } else if (balance > 1) {
            // right-left case.
            if (getHeight(subTree.rightNode.rightNode) <= getHeight(subTree.rightNode.leftNode)) {
                subTree.rightNode = rightRotate(subTree.rightNode);
            }
            // right-right case or the continuation of the right-left case (if the condition below was true)
            subTree = leftRotate(subTree);
        }

        // Reassign the reference to the new root of this subtree.
        return subTree;
    }

    /**
     * Find the node by it's key.
     * O(log(n)) in a worst case.
     *
     * @param key     - key of this node.
     * @param subTree - subtree in which the node is required to find.
     * @return the node if it's found, null - otherwise.
     */
    private Node getNodeByKey(K key, Node subTree) {
        if (subTree == null) {
            return null;
        }
        if (subTree.key.equals(key)) {
            return subTree;
        }
        if (key.compareTo(subTree.key) < 0) {
            if (subTree.leftNode == null) {
                return null;
            } else {
                return getNodeByKey(key, subTree.leftNode);
            }
        } else {
            if (subTree.rightNode == null) {
                return null;
            } else {
                return getNodeByKey(key, subTree.rightNode);
            }
        }
    }

    /**
     * Inner class to store nodes in a tree.
     */
    class Node {
        Node parentNode; // a node by which the current node is connected.
        Node leftNode; // a left child of the current node.
        Node rightNode; // a right child of the current node.

        K key; // a comparable key.
        V value; // a value to store
        int height; // a current height. It says how many heirs this node has.

        Node(K key, V value, Node parentNode) {
            this.key = key;
            this.value = value;
            this.parentNode = parentNode;
        }

        @Override
        public String toString() {
            return "[" + key.toString() + ", " + value.toString() + ", " + height + "]";
        }
    }

    /**
     * Enumeration of cases how to get around all the nodes
     */
    private enum TraversalOrder {
        PREORDER, INORDER, POSTORDER;
    }
}