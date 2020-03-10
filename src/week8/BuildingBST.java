package week8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Roman Soldatov BS19-02
 */
public class BuildingBST {
    public static void main(String[] args) {

        // Input
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        RedBlackTree tree = new RedBlackTree();
        //BSTree tree = new BSTree();
        for (int i = 0; i < n; i++) {
            tree.add(scanner.nextInt());
        }

        // List of nodes. Sort them by indices.
        ArrayList<Node> Nodes = tree.getNodes();
        Nodes.sort(Comparator.comparingInt(o -> o.index));

        // Output
        System.out.println(n);
        for (int i = 0; i < n; i++) {

            Node x = Nodes.get(i);

            // Print value
            System.out.print(x.value + " ");

            // Print left child's index
            if (x.leftNode.value == null) {
                System.out.print("-1 ");
            } else {
                System.out.print((x.leftNode.index + 1) + " ");
            }

            // Print right child's index
            if (x.rightNode.value == null) {
                System.out.println("-1");
            } else {
                System.out.println(x.rightNode.index + 1);
            }
        }
        // Print root's index
        System.out.println(tree.getRoot().index + 1);
    }
}

class RedBlackTree {

    private Node root;
    static Node nullNode;

    public RedBlackTree() {
        root = new Node();
        nullNode = new Node();
        nullNode.color = NodeColor.BLACK;
        root.parentNode = nullNode;
        root.rightNode = nullNode;
        root.leftNode = nullNode;
    }

    public void add(int value) {
        Node Node = root;
        Node temp = nullNode;
        Node newNode = new Node(value, NodeColor.RED);

        while (Node != null && Node != nullNode && !Node.isFree()) {
            temp = Node;
            if (newNode.value < Node.value) {
                Node = Node.leftNode;
            } else {
                Node = Node.rightNode;
            }
        }

        newNode.parentNode = temp;

        if (temp == nullNode) {
            root.value = newNode.value;
        } else {
            if (newNode.value < temp.value) {
                temp.setLeftNode(newNode);
            } else {
                temp.setRightNode(newNode);
            }
        }

        newNode.setLeftNode(nullNode);
        newNode.setRightNode(nullNode);

        checkBalance(newNode);
    }

    public ArrayList<Node> getNodes() {
        ArrayList<Node> list = new ArrayList<>();
        getNodes(root, list);
        return list;
    }

    private void getNodes(Node Node, ArrayList<Node> list) {
        if (Node.value != null) {
            getNodes(Node.leftNode, list);
            list.add(Node);
            getNodes(Node.rightNode, list);
        }
    }

    public Node getRoot() {
        return root;
    }

    private void checkBalance(Node Node) {
        Node temp;

        while (!Node.isParentFree() && Node.parentNode.isRed()) {
            if (Node.parentNode == Node.getGrandfather().leftNode) {
                temp = Node.getGrandfather().rightNode;
                if (temp.isRed()) {
                    temp.color = NodeColor.BLACK;
                    Node.parentNode.color = NodeColor.BLACK;
                    Node.getGrandfather().color = NodeColor.RED;
                    Node = Node.getGrandfather();
                } else {
                    if (Node == Node.parentNode.rightNode) {
                        Node = Node.parentNode;
                        leftRotate(this, Node);
                    }
                    Node.parentNode.color = NodeColor.BLACK;
                    Node.getGrandfather().color = NodeColor.RED;
                    rightRotate(this, Node.getGrandfather());
                }
            } else {
                temp = Node.getGrandfather().leftNode;
                if (temp.isRed()) {
                    temp.color = NodeColor.BLACK;
                    Node.parentNode.color = NodeColor.BLACK;
                    Node.getGrandfather().color = NodeColor.RED;
                    Node = Node.getGrandfather();
                } else {
                    if (Node == Node.parentNode.leftNode) {
                        Node = Node.parentNode;
                        rightRotate(this, Node);
                    }
                    Node.parentNode.color = NodeColor.BLACK;
                    Node.getGrandfather().color = NodeColor.RED;
                    leftRotate(this, Node.getGrandfather());
                }
            }
        }

        root.color = NodeColor.BLACK;
    }

    private static void leftRotate(RedBlackTree tree, Node Node) {
        Node NodeParent = Node.parentNode;
        Node NodeRight = Node.rightNode;

        if (NodeParent != nullNode) {
            if (NodeParent.leftNode == Node) {
                NodeParent.setLeftNode(NodeRight);
            } else {
                NodeParent.setRightNode(NodeRight);
            }
        } else {
            tree.root = NodeRight;
            tree.root.parentNode = nullNode;
        }

        Node.setRightNode(NodeRight.leftNode);
        NodeRight.setLeftNode(Node);
    }

    private static void rightRotate(RedBlackTree tree, Node Node) {
        Node NodeParent = Node.parentNode;
        Node NodeLeft = Node.leftNode;

        if (NodeParent != nullNode) {
            if (NodeParent.leftNode == Node) {
                NodeParent.setLeftNode(NodeLeft);
            } else {
                NodeParent.setRightNode(NodeLeft);
            }
        } else {
            tree.root = NodeLeft;
            tree.root.parentNode = tree.nullNode;
        }

        Node.setLeftNode(NodeLeft.rightNode);
        NodeLeft.setRightNode(Node);
    }

}

class Node {
    private static int id = 0;
    int index;
    Integer value;
    NodeColor color;
    Node parentNode;
    Node leftNode;
    Node rightNode;

    public Node() {
        color = NodeColor.NONE;
    }

    public Node(int value) {
        this.value = value;
        index = id++;
    }

    public Node(Integer value, NodeColor color) {
        this.value = value;
        this.color = color;
        parentNode = RedBlackTree.nullNode;
        leftNode = RedBlackTree.nullNode;
        rightNode = RedBlackTree.nullNode;
        index = id++;
    }

    public boolean isFree() {
        return value == null || value.equals(RedBlackTree.nullNode.value);
    }

    public boolean isParentFree() {
        return parentNode == null || parentNode == RedBlackTree.nullNode;
    }

    public void setLeftNode(Node Node) {
        leftNode = Node;
        if (leftNode != null && leftNode != RedBlackTree.nullNode) {
            leftNode.parentNode = this;
        }
    }

    public void setRightNode(Node Node) {
        rightNode = Node;
        if (rightNode != null && rightNode != RedBlackTree.nullNode) {
            rightNode.parentNode = this;
        }
    }

    public boolean isRed() {
        return color == NodeColor.RED;
    }

    public Node getGrandfather() {
        if (parentNode != null && parentNode != RedBlackTree.nullNode) {
            return parentNode.parentNode;
        }
        return null;
    }
}

enum NodeColor {
    RED, BLACK, NONE
}

class BSTree {
    private Node root;

    public void add(int value) {
        if (root == null) {
            root = new Node(value);
        } else {
            add(value, root);
        }
    }

    public ArrayList<Node> getNodes() {
        ArrayList<Node> list = new ArrayList<>();
        getNodes(root, list);
        return list;
    }

    public Node getRoot() {
        return root;
    }

    private void getNodes(Node Node, ArrayList<Node> list) {
        if (Node != null) {
            getNodes(Node.leftNode, list);
            list.add(Node);
            getNodes(Node.rightNode, list);
        }
    }

    private void add(int value, Node parent) {
        if (value < parent.value) {
            if (parent.leftNode == null) {
                parent.leftNode = new Node(value);
            } else {
                add(value, parent.leftNode);
            }
        } else {
            if (parent.rightNode == null) {
                parent.rightNode = new Node(value);
            } else {
                add(value, parent.rightNode);
            }
        }
    }
}