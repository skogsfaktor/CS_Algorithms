/*Given the root to a binary tree, implement serialize(root), which serializes the tree into a string, and deserialize(s), which deserializes the string back into the tree.
For example, given the following Node class
class Node:
        def __init__(self, val, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

        The following test should pass:
        node = Node('root', Node('left', Node('left.left')), Node('right'))
        assert deserialize(serialize(node)).left.left.val == 'left.left'*/

package DailyCodingProblem;

public class DailyCodingProblem_3 {
    static class Node {
        String val;
        Node left;
        Node right;

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
            if(left == null && right == null) {
                return "null";
            } else {
                return val + serialize(left) + serialize(right);
            }
        }
    }

    public static void main(String[] args) {
        DailyCodingProblem_3.Node tree = new DailyCodingProblem_3.Node("root",
                new DailyCodingProblem_3.Node("left", new DailyCodingProblem_3.Node("left.left")),
                new DailyCodingProblem_3.Node("right"));

        //System.out.println(tree.right.val);
        System.out.println(tree.serialize(tree));
    }
}
