/*
Given the root to a binary tree, implement serialize(root), which serializes the tree into a string, and deserialize(s), which deserializes the string back into the tree.
For example, given the following Node class
class Node:
        def __init__(self, val, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

        The following test should pass:
        node = Node('root', Node('left', Node('left.left')), Node('right'))
        assert deserialize(serialize(node)).left.left.val == 'left.left'
*/
package DailyCodingProblem;

public class DailyCodingProblem_3 {
    static class Node {
        String val;
        Node left;
        Node right;

        public Node() {
            
        }

        public Node(String val) {
            this.val = val;
            left = null;
            right = null;
        }

        public Node(String val, Node left) {
            this.val = val;
            this.left = left;
            right = null;
        }

        public Node(String val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public String serialize(Node root) {
            if(root == null) {
                return "null";
            } else {
                return "[" + root.val + ", " + serialize(root.left) + ", " + serialize(root.right) + "]";
            }
        }

        public Node deserialize(String serialized) {
            //Input: [root, [left, [left.left, null, null], null], [right, null, null]]
            //if(left = "null") -> return null
            //new Node(val, deserialize(left), deserialize(right))
            //[val, l, r]

            //Get val
            int valueEnd = serialized.indexOf(',');
            String value = serialized.substring(1, valueEnd);
            
            Node left, right;
            //Index of opening bracket [ or n 
            int leftStart = valueEnd+2;
            int leftEnd;
            //Get left, count brackets, check null

            if(serialized.charAt(leftStart) == 'n') {
                //null
                leftEnd = valueEnd + 6;
                left = null;
            } else {
                //node 
                leftEnd = leftStart + treeEndPos(serialized.substring(leftStart));
                left = deserialize(serialized.substring(leftStart, leftEnd+1));
            }

            int rightStart = leftEnd+2;
            //Index of opening bracket [ or n 
            int rightEnd;
            //Get left, count brackets, check null
            if(serialized.charAt(rightStart) == 'n') {
                //null
                right = null;
            } else {
                //node 
                rightEnd = rightStart + treeEndPos(serialized.substring(rightStart));
                right = deserialize(serialized.substring(rightStart, rightEnd+1));
            }

            return new Node(value, left, right);
        }

        private int treeEndPos(String tree) {
            int count = 1;
            int pos = 1;
            while (count != 0){
                if (tree.charAt(pos) == '[') {
                    count++;
                } else if (tree.charAt(pos) == ']') {
                    count--;
                }
                pos++;
            }
            return pos;
        }
    }

    public static void main(String[] args) {
        DailyCodingProblem_3.Node tree = new DailyCodingProblem_3.Node("root",
                new DailyCodingProblem_3.Node("left", new DailyCodingProblem_3.Node("left.left")),
                new DailyCodingProblem_3.Node("right"));

        //System.out.println(tree.right.val);
        System.out.println(tree.serialize(tree));
        System.out.println(tree.deserialize(tree.serialize(tree)).left.left.val);
        System.out.println(tree.deserialize(tree.serialize(tree)).right.val);
    }
}
