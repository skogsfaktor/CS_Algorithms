package dataStructures;

public class OrderedBinaryTree {
    Node root;

    public OrderedBinaryTree() {
        root = new Node();
    }

    public void add(int value) {
        add(root, value);
    }

    public void add(Node node, int value) {
        if(node.value == 0) {
            node.value = value;
        } else if(value < node.value) {
            add(node.left, value);
        } else {
            add(node.right, value);
        }
    }

    public String toString() {
        return root.toString();
    }
}

class Node {
    Node left;
    Node right;
    int value;

    public Node() {
        int value = 0;
        left = new Node();
        right = new Node();
    }

    public Node(int value) {
        this.value = value;
        left = new Node();
        right = new Node();
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        return "(" + Integer.toString(value) + left.toString() + right.toString() + ")";
    }
}
