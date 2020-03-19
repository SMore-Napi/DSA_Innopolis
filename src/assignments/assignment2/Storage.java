package assignments.assignment2;

import java.util.ArrayList;

/**
 * @author Roman Soldatov BS19-02.
 * 2.3 Storage.
 * Implementing the AVL tree.
 * todo Annotate every operation on the data structure with asymptotic analysis of its time complexity.
 */
public class Storage {

    public static void main(String[] args) {

        AVLTree tree = new AVLTree();

        /*
        tree.insert(6, "a");
        tree.insert(3, "b");
        tree.insert(9, "c");
        tree.insert(1, "d");
        tree.insert(5, "e");
        tree.insert(8, "f");
        tree.insert(10, "g");
        tree.insert(2, "h");
        tree.insert(4, "i");
        tree.insert(7, "j");
        tree.insert(11, "k");

         */

        /*
        tree.insert(1, "a");
        tree.insert(2, "b");
        tree.insert(3, "c");
        tree.insert(4, "d");
        tree.insert(5, "e");
        tree.insert(6, "f");
        tree.insert(7, "g");
        tree.insert(8, "h");
        tree.insert(9, "i");
        tree.insert(10, "j");
        tree.insert(11, "k");
         */

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


        // ArrayList<AVLTree.Node> nodes = tree.getNodesPreorder();
        ArrayList<AVLTree.Node> nodes = tree.getNodesInorder();
        // ArrayList<AVLTree.Node> nodes = tree.getNodesPostorder();

        for (AVLTree.Node node : nodes) {
            System.out.print(node.key + " ");
        }
        System.out.println();

        nodes = tree.getNodesInorder();
        for (AVLTree.Node node : nodes) {
            System.out.print(node.height + " ");
        }
        System.out.println();

    }
}

class AVLTree {
    private Node root;

    private int getHeight(Node node) {
        if (node == null) {
            return -1;
        }
        return node.height;
    }

    private void updateHeight(Node node) {
        node.height = 1 + Math.max(getHeight(node.leftNode), getHeight(node.rightNode));
    }

    private int getBalanceBetweenChilds(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.rightNode) - getHeight(node.leftNode);
    }

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

    private Node restoreBalance(Node node) {
        updateHeight(node);
        int balance = getBalanceBetweenChilds(node);

        if (balance < -1) {
            if (getHeight(node.leftNode.leftNode) <= getHeight(node.leftNode.rightNode)) {
                node.leftNode = leftRotate(node.leftNode);
            }
            node = rightRotate(node);
        } else if (balance > 1) {
            if (getHeight(node.rightNode.rightNode) <= getHeight(node.rightNode.leftNode)) {
                node.rightNode = rightRotate(node.rightNode);
            }
            node = leftRotate(node);
        }

        return node;
    }

    public void add(Integer key, String value) {
        if (root == null) {
            root = new Node(key, value, null);
        } else {
            root = add(key, value, root);
        }
    }

    private Node add(Integer key, String value, Node parent) {
        if (key < parent.key) {
            if (parent.leftNode == null) {
                parent.leftNode = new Node(key, value, parent);
            } else {
                parent.leftNode = add(key, value, parent.leftNode);
            }
        } else {
            if (parent.rightNode == null) {
                parent.rightNode = new Node(key, value, parent);
            } else {
                parent.rightNode = add(key, value, parent.rightNode);
            }
        }

        parent = restoreBalance(parent);
        return parent;
    }

    public String delete(Integer key) {
        // Find the node to delete.
        Node nodeToDelete = getNodeByKey(key, root);

        // In case such node does not exist.
        if (nodeToDelete == null) {
            return null;
        }

        return delete(nodeToDelete);
    }

    private String delete(Node nodeToDelete) {

        String valueToReturn = nodeToDelete.value;

        // In case this node is a leaf
        if (nodeToDelete.isLeaf()) {
            Node parentNode = nodeToDelete.parentNode;
            if (parentNode.rightNode != null && parentNode.rightNode.equals(nodeToDelete)) {
                parentNode.rightNode = null;
            } else {
                parentNode.leftNode = null;
            }
            return valueToReturn;
        }

        // In case this node has only one right child.
        if (nodeToDelete.leftNode == null && nodeToDelete.rightNode != null) {
            Node parentNode = nodeToDelete.parentNode;
            if (parentNode.rightNode != null && parentNode.rightNode.equals(nodeToDelete)) {
                parentNode.rightNode = nodeToDelete.rightNode;
            } else {
                parentNode.leftNode = nodeToDelete.rightNode;
            }
            return valueToReturn;
        }

        // In case this node has only one left child.
        if (nodeToDelete.leftNode != null && nodeToDelete.rightNode == null) {
            Node parentNode = nodeToDelete.parentNode;
            if (parentNode.rightNode != null && parentNode.rightNode.equals(nodeToDelete)) {
                parentNode.rightNode = nodeToDelete.leftNode;
            } else {
                parentNode.leftNode = nodeToDelete.leftNode;
            }
            return valueToReturn;
        }

        // In case this node has two children.
        Node predecessor = getPredecessor(nodeToDelete);
        nodeToDelete.key = predecessor.key;
        nodeToDelete.value = predecessor.value;
        delete(predecessor);

        /*
        Node successor = getSuccessor(nodeToDelete);
        nodeToDelete.key = successor.key;
        nodeToDelete.value = successor.value;
        delete(successor);
         */

        return valueToReturn;
    }

    private Node getNodeByKey(Integer key, Node subTree) {
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

    private Node getSuccessor(Node subTree) {
        if (subTree == null) {
            return null;
        }
        Node x = subTree;
        if (x.rightNode != null) {
            return getMinimumNode(x.rightNode);
        }

        Node y = x.parentNode;
        while (y != null && x.equals(y.rightNode)) {
            x = y;
            y = y.parentNode;
        }
        return y;
    }

    private Node getPredecessor(Node subTree) {
        if (subTree == null) {
            return null;
        }
        Node x = subTree;
        if (x.leftNode != null) {
            return getMaximumNode(x.leftNode);
        }

        Node y = x.parentNode;
        while (y != null && x.equals(y.leftNode)) {
            x = y;
            y = y.parentNode;
        }
        return y;
    }

    public ArrayList<Node> getNodesPreorder() {
        ArrayList<Node> list = new ArrayList<>();
        traversalNodes(root, list, TraversalOrder.PREORDER);
        return list;
    }

    public ArrayList<Node> getNodesInorder() {
        ArrayList<Node> list = new ArrayList<>();
        traversalNodes(root, list, TraversalOrder.INORDER);
        return list;
    }

    public ArrayList<Node> getNodesPostorder() {
        ArrayList<Node> list = new ArrayList<>();
        traversalNodes(root, list, TraversalOrder.POSTORDER);
        return list;
    }

    private void traversalNodes(Node node, ArrayList<Node> list, TraversalOrder order) {
        if (node != null) {
            switch (order) {
                case PREORDER:
                    list.add(node);
                    traversalNodes(node.leftNode, list, order);
                    traversalNodes(node.rightNode, list, order);
                    break;
                case INORDER:
                    traversalNodes(node.leftNode, list, order);
                    list.add(node);
                    traversalNodes(node.rightNode, list, order);
                    break;
                case POSTORDER:
                    traversalNodes(node.leftNode, list, order);
                    traversalNodes(node.rightNode, list, order);
                    list.add(node);
                    break;
            }
        }
    }

    public Node getRoot() {
        return root;
    }

    class Node {
        Node parentNode;
        Node leftNode;
        Node rightNode;

        Integer key;
        String value;
        int height;

        Node(Integer key, String value, Node parentNode) {
            this.key = key;
            this.value = value;
            this.parentNode = parentNode;
        }

        public boolean isLeaf() {
            return (leftNode == null && rightNode == null);
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Node)) {
                return false;
            }
            return key.equals(((Node) obj).key);
        }
    }

    private enum TraversalOrder {
        PREORDER, INORDER, POSTORDER;
    }
}