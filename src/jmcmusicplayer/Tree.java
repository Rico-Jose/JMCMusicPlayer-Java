package jmcmusicplayer;

// Binary Tree
// Outer class
public class Tree {
    
    //Inner class
    private class Node {
        private String data;
        private Node left;
        private Node right;
        private Node(String data) {
            this.data = data;
        }
    }
    
    private Node root;
    
    public Tree() {}
    
    // To add an item. Also calls the Recursive method for adding
    public void add(String data) {
        Node newNode = new Node(data);
        if (root == null)
            root = newNode;
        else
            root = addRecursive(root, newNode);
    }
    
    private Node addRecursive(Node current, Node newNode) {
        if (current == null) {
            current = newNode;
            return current;
        } else if (newNode.data.compareTo(current.data) < 0) {
            current.left = addRecursive(current.left, newNode);
            current = balanceTree(current);
        } else if (newNode.data.compareTo(current.data) > 0) {
            current.right = addRecursive(current.right, newNode);
            current = balanceTree(current);
        }
        return current;
    }
    
    // <editor-fold defaultstate="collapsed" desc="To balance the tree">
    private Node balanceTree(Node current) {
        int bFactor = getBalanceFactor(current);
        if (bFactor > 1) {
            if (getBalanceFactor(current.left) > 0)
                current = rotateLL(current);
            else
                current = rotateLR(current);
        } else if (bFactor < -1) {
            if (getBalanceFactor(current.right) > 0)
                current = rotateRL(current);
            else
                current = rotateRR(current);
        }
        return current;
    }
    
    private int getMax(int l, int r) {
        return l > r ? l : r;
    }
    
    private int getHeight(Node current) {
        int h = 0;
        if (current != null) {
            int l = getHeight(current.left);
            int r = getHeight(current.right);
            int m = getMax(l, r);
            h = m + 1;
        }
        return h;
    }
    
    private int getBalanceFactor(Node current) {
        int l = getHeight(current.left);
        int r = getHeight(current.right);
        int bFactor = l - r;
        return bFactor;
    }
    
    private Node rotateRR(Node parent) {
        Node pivot = parent.right;
        parent.right = pivot.left;
        pivot.left = parent;
        return pivot;
    }
    
    private Node rotateLL(Node parent) {
        Node pivot = parent.left;
        parent.left = pivot.right;
        pivot.right = parent;
        return pivot;
    }
    
    private Node rotateLR(Node parent) {
        Node pivot = parent.left;
        parent.left = rotateRR(pivot);
        return rotateLL(parent);
    }
    
    private Node rotateRL(Node parent) {
        Node pivot = parent.right;
        parent.right = rotateLL(pivot);
        return rotateRR(parent);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Has searching algorithm">
    // Binary Search
    public String find(String song) {
        if (root != null) {
            if (findRecursive(song, root).data.equals(song))
                return song;
            else
                return "not found";
        } else {
            return "empty";
        }
    }
    
    private Node findRecursive(String target, Node current) {
        if (current != null) {
            if (target.compareTo(current.data) < 0) {
                if (target.equals(current.data))
                    return current;
                else
                    return findRecursive(target, current.left);
            } else {
                if (target.equals(current.data))
                    return current;
                else
                    return findRecursive(target, current.right);
            }
        }
        return root;
    }
    // </editor-fold>
}