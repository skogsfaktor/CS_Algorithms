/*
A unival tree (which stands for "universal value") is a tree where all nodes under it have the same value.

Given the root to a binary tree, count the number of unival subtrees.

For example, the following tree has 5 unival subtrees:

   0
  / \
 1   0
    / \
   1   0
  / \
  1  1
*/

package DailyCodingProblem;

public class DailyCodingProblem_8 {
    public static void main(String[] args) {
        Node tree = new Node(0, new Node(1), new Node(0, new Node(1, new Node(1), new Node(1)), new Node(0)));
        System.out.println(unival(tree, tree.val));
        System.out.println(univalCh(tree)); 
    }

    //NON_WORKING
    public static int unival(Node tree, int val) {
        if (tree == null) {
            return 0;
        }
        if (tree.left == null && tree.right == null) {
            return 1;
        }

        //no unival here 
        if(tree.val != val) {
            //tree.val istället för val
            //Reset counter 
            return unival(tree.left, tree.val) + unival(tree.right, tree.val);
        } else { //still unival, keep counting 
            return unival(tree.left, val) + unival(tree.right, val);
        }

        /*
        count_leaves(nil,0).
        count_leaves(t(_,nil,nil),1).
        count_leaves(t(_,L,nil),N) :- L = t(_,_,_), count_leaves(L,N).
        count_leaves(t(_,nil,R),N) :- R = t(_,_,_), count_leaves(R,N).
        count_leaves(t(_,L,R),N) :- L = t(_,_,_), R = t(_,_,_),
        count_leaves(L,NL), count_leaves(R,NR), N is NL + NR.
        */
}

    public static int univalCh(Node tree) {
        return univalboy(tree)[1];
    }

    public static int[] univalboy(Node tree) {
        if (tree == null) {
            return new int[] {0, 0};
        }
        if (tree.left == null && tree.right == null) {
            return new int[] {1, 1};
        }

        int[] univalLeft = univalboy(tree.left);
        int[] univalRight = univalboy(tree.right);

        int count = univalLeft[1] + univalRight[1];

        if (univalLeft[0] == 1 && univalRight[0] == 1 &&
            tree.left.val == tree.val && tree.right.val == tree.val) {
            return new int[] {1, count + 1};
        } else {
            return new int[] {0, count};
        }
    }
}

class Node {
    int val;
    Node left;
    Node right;

    public Node(int val) {
        this.val = val;
        left = null;
        right = null;
    }

    public Node(int val, Node left) {
        this.val = val;
        this.left = left;
        right = null;
    }

    public Node(int val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}